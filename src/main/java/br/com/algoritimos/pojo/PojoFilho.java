package br.com.algoritimos.pojo;

import javax.xml.bind.annotation.XmlType;

/**
 * A anota��o @XmlType indica que essa classe na verdade mapeia um tipo de informa��o espec�fico (XML schema).
 * @author Fidelis.Guimaraes
 *
 */
@XmlType
public class PojoFilho {
	private Integer id;

	public PojoFilho() {
		//necess�rio para o marshall
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
