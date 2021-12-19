package br.com.algoritimos.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJOs - Plain Old Java Objects
 * 
 * São objetos Java que seguem um projeto simplificado, sem definições rígidas de estrutura
 * 
 * @XmlRootElement indica que o valor da classe será representado como um elemento XML principal.
 *
 */
@XmlRootElement
public class Pojo {
	
	private Integer id;
	private PojoFilho filho;
	
	public Pojo() {
		// necessário para marshall
	}
	
	public Pojo(Integer id, PojoFilho filho) {
		super();
		this.id = id;
		this.filho = filho;
	}

	public PojoFilho getFilho() {
		return filho;
	}

	public void setFilho(PojoFilho filho) {
		this.filho = filho;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
