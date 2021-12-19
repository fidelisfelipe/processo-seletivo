package br.com.analise.migracao.solucao.input;

import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.request.ConfiguracaoIntegracao;
import br.com.analise.migracao.solucao.request.RequestContext;

public class PedidosImportacaoIn {

	private PedidoDto pedidoDto;

	public void setConfiguracao(ConfiguracaoIntegracao configuracao) {
		// TODO Auto-generated method stub
		
	}

	public void setRequestContext(RequestContext rc) {
		// TODO Auto-generated method stub
		
	}

	PedidoDto getPedidoDto() {
		return pedidoDto;
	}

	public void setPedidoDto(PedidoDto pedidoDto) {
		this.pedidoDto = pedidoDto;
	}

}
