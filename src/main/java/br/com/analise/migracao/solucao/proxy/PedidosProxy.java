package br.com.analise.migracao.solucao.proxy;

import br.com.analise.migracao.solucao.exceptions.NextItemNegocioException;
import br.com.analise.migracao.solucao.input.ConsultarPedidoIn;
import br.com.analise.migracao.solucao.output.ConsultarPedidoOut;

public class PedidosProxy {

	private ConsultarPedidoOut criaPedido(ConsultarPedidoIn inPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConsultarPedidoOut atualizaPedido(ConsultarPedidoIn inPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	public void salvarOuAtualizar(ConsultarPedidoIn inPedido, boolean isInsert) throws NextItemNegocioException {
		try {
		
			if(isInsert) {
				criaPedido(inPedido);
			}else {
				atualizaPedido(inPedido);
			}
		
		}catch (Exception e) {
			throw new NextItemNegocioException();
		}
		
	}

}
