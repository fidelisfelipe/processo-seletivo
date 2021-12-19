package br.com.analise.migracao.solucao.controller;

import br.com.analise.migracao.solucao.enums.TipoLastDate;
import br.com.analise.migracao.solucao.request.RequestContext;

public class ConfigureIntegracaoIn {

	private TipoLastDate tipo;
	int process;
	void setRequestContext(RequestContext rc) {
		// TODO Auto-generated method stub
	}
	public void setTipo(TipoLastDate tipo) {
		this.tipo = tipo;
	}

}
