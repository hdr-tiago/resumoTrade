package br.com.resumotrade.dominio.mercado;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MercadoService {

	@Autowired
	private MercadoRepositorio repositorio;
	
	@Autowired
	private FabricaDeMercados fabrica;

	public MercadoId novaIdentidade() {
		return repositorio.novaIdentidade();
	}

	public Mercado buscarMercadoPorId(MercadoId mercadoId) {
		return repositorio.buscarMercadoPorId(mercadoId);
	}

	public void salvar(Mercado mercado) {
		repositorio.salvar(mercado);
	}

	public List<MercadoData> buscarMercadosPorEsporte(Esporte esporte) {
		
		List<MercadoData> result = new ArrayList<>();
		for(Mercado mercados : repositorio.buscarMercadosPorEsporte(esporte)){
			result.add(contruir(mercados));
		}
		return result;
	}

	public Mercado novoMercado(MercadoComando comando) {
		Mercado mercado = fabrica.novoMercado(comando);
		repositorio.salvar(mercado);
		return mercado;
	}
	
	private MercadoData contruir(Mercado mercado) {
		return new MercadoData(mercado.mercadoId().id(), 
							   mercado.id(), 
							   mercado.esporte().toString(), 
							   mercado.descricao());
	}

	public void remover(MercadoId mercadoId) {
		repositorio.remover(mercadoId);
	}

	public void excluirMercado(MercadoId mercadoId) {
		repositorio.remover(mercadoId);
	}

}
