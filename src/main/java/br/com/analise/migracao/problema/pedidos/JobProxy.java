package br.com.analise.migracao.problema.pedidos;

import java.util.ArrayList;
import java.util.List;

public class JobProxy {

	List<PedidoDto> lista = new ArrayList<PedidoDto>();

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

}
