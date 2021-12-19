package br.com.analise.migracao.solucao.proxy;

import java.util.ArrayList;
import java.util.List;

import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.input.ConfigureIntegracaoIn;
import br.com.analise.migracao.solucao.input.PedidosImportacaoIn;
import br.com.analise.migracao.solucao.input.UtentesImportacaoIn;
import br.com.analise.migracao.solucao.output.PedidosImportacaoOut;
import br.com.analise.migracao.solucao.output.UtentesImportacaoOut;

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

	public List<PedidoDto> getLista() {
		return lista;
	}

	void setLista(List<PedidoDto> lista) {
		this.lista = lista;
	}

}
