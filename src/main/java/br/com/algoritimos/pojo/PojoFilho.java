package br.com.algoritimos.pojo;

import javax.xml.bind.annotation.XmlType;

/**
 * A anotação @XmlType indica que essa classe na verdade mapeia um tipo de informação específico (XML schema).
 * @author Fidelis.Guimaraes
 *
 */
@XmlType
public class PojoFilho {
	private Integer id;

	public PojoFilho() {
		//necessário para o marshall
	}
	
	public PojoFilho(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
