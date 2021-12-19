package br.com.analise.migracao.solucao.output;

import br.com.analise.migracao.solucao.bus.BusPedido;

public class PedidosImportacaoOut {

	private BusPedido pedido;

	public BusPedido getPedido() {
		return pedido;
	}

	void setPedido(BusPedido pedido) {
		this.pedido = pedido;
	}

}
