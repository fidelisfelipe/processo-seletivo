package br.com.algoritimos.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJOs - Plain Old Java Objects
 * 
 * S�o objetos Java que seguem um projeto simplificado, sem defini��es r�gidas de estrutura
 * 
 * @XmlRootElement indica que o valor da classe ser� representado como um elemento XML principal.
 *
 */
@XmlRootElement
public class Pojo {
	
	private Integer id;
	private PojoFilho filho;
	
	public Pojo() {
		// necess�rio para marshall
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
