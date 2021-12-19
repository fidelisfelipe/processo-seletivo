package br.com.analise.migracao.solucao.input;

import br.com.analise.migracao.solucao.enums.TipoLastDate;
import br.com.analise.migracao.solucao.request.RequestContext;

public class ConfigureIntegracaoIn {

	private TipoLastDate tipo;
	private int process;
	public void setRequestContext(RequestContext rc) {
		// TODO Auto-generated method stub
	}
	public void setTipo(TipoLastDate tipo) {
		this.tipo = tipo;
	}
	
	public TipoLastDate getTipo() {
		return tipo;
	}
	int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}

}
