package br.com.analise.migracao.solucao.controller;

import br.com.analise.migracao.solucao.bus.BusPedido;

public class PedidosImportacaoOut {

	private BusPedido pedido;

	BusPedido getPedido() {
		return pedido;
	}

	void setPedido(BusPedido pedido) {
		this.pedido = pedido;
	}

}
