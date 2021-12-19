package br.com.analise.migracao.problema.pedidos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestContext extends HttpServletRequestWrapper{

	public RequestContext(HttpServletRequest request) {
		super(request);
	}

}
