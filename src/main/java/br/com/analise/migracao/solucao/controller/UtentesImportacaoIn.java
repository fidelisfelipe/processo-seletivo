package br.com.analise.migracao.solucao.controller;

import br.com.analise.migracao.solucao.dto.UtenteDto;
import br.com.analise.migracao.solucao.request.RequestContext;

public class UtentesImportacaoIn {

	private UtenteDto utenteDto;

	public void setRequestContext(RequestContext rc) {
		// TODO Auto-generated method stub
		
	}

	UtenteDto getUtenteDto() {
		return utenteDto;
	}

	public void setUtenteDto(UtenteDto utenteDto) {
		this.utenteDto = utenteDto;
	}

}
