package br.com.analise.migracao.solucao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.analise.migracao.solucao.dto.PedidoDto;

public class JobProxy {

	private List<PedidoDto> lista = new ArrayList<PedidoDto>();

	public void updateProcess(ConfigureIntegracaoIn confBegin) {
		// TODO Auto-generated method stub
		
	}

	public JobProxy consultaPedidos(PedidosImportacaoIn input) {
		return this;
	}

	public PedidosImportacaoOut consultarPedidoByOracle(PedidosImportacaoIn input) {
		// TODO Auto-generated method stub
		return null;
	}

	public UtentesImportacaoOut consultarUtenteByOracle(UtentesImportacaoIn utenteInput) {
		// TODO Auto-generated method stub
		return null;
	}

	List<PedidoDto> getLista() {
		return lista;
	}

	void setLista(List<PedidoDto> lista) {
		this.lista = lista;
	}

}
