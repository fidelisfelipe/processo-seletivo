package br.com.analise.migracao.solucao.dto;

import java.util.Date;

public class PedidoDto {

	private String codUtente;
	private String codTipoPedido;
	private String autorPedido;
	private String codPds;
	private Date creationDate;
	private Long codPedido;
	private String estadoDoPedido;
	private String hospitalDestino;
	private Boolean boolPrestador;
	private String entidadeRequerente;
	public String getCodUtente() {
		return codUtente;
	}
	void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}
	public String getCodTipoPedido() {
		return codTipoPedido;
	}
	void setCodTipoPedido(String codTipoPedido) {
		this.codTipoPedido = codTipoPedido;
	}
	public String getAutorPedido() {
		return autorPedido;
	}
	void setAutorPedido(String autorPedido) {
		this.autorPedido = autorPedido;
	}
	public String getCodPds() {
		return codPds;
	}
	void setCodPds(String codPds) {
		this.codPds = codPds;
	}
	public Long getCodPedido() {
		return codPedido;
	}
	void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getEstadoDoPedido() {
		return estadoDoPedido;
	}
	void setEstadoDoPedido(String estadoDoPedido) {
		this.estadoDoPedido = estadoDoPedido;
	}
	public String getHospitalDestino() {
		return hospitalDestino;
	}
	void setHospitalDestino(String hospitalDestino) {
		this.hospitalDestino = hospitalDestino;
	}
	public Boolean getBoolPrestador() {
		return boolPrestador;
	}
	void setBoolPrestador(Boolean boolPrestador) {
		this.boolPrestador = boolPrestador;
	}
	public String getEntidadeRequerente() {
		return entidadeRequerente;
	}
	void setEntidadeRequerente(String entidadeRequerente) {
		this.entidadeRequerente = entidadeRequerente;
	}

}
