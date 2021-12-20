package br.com.algoritimos.jaxrs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.algoritimos.pojo.Pojo;
import br.com.algoritimos.pojo.PojoFilho;

/**
 * Java Architecture for XML Binding
 * A JAXB � uma das APIs da plataforma Java EE e fornece suporte � manipula��o de objetos Java e XML. 
 * Sua principal caracter�stica � a capacidade de vincular XML a objetos Java e vice-versa. 
 * E o melhor � que ela j� est� inclu�da na JDK 6
 * 
 * Usando JAXB somos capazes de criar facilmente classes a partir de um schema (XSD). 
 * Isso mesmo. Com ela somos capazes de transformar um arquivo XSD em um conjunto de classes num piscar de olhos. 
 * No momento vamos nos focar nos processos de Marshall e Unmarshall.
 * Marshall � o processo de transformar objetos Java em XML e o Unmarshall faz o caminho inverso, usa os dados de um XML para popular objetos Java.
 * 
 * Um ponto importante � a necessidade de um arquivo com o nome jaxb.index, 
 * contendo o nome das classes que queremos transformar em XML, e deve estar no mesmo pacote das classes (Figura 01).
 * Ele � requerido pelo JAXB para gerar as classes em tempo de execu��o.
 * Essa n�o � a �nica forma de fazer isso, mas � a que atende ao nosso caso espec�fico.
 * 
 * @author Fidelis.Guimaraes
 *
 */
public class JAXB {

	public void testJAXBMarshallAndUnmarshall() throws JAXBException, FileNotFoundException {
		PojoFilho filho = new PojoFilho(2);
		Pojo pojo = new Pojo(1, filho);
		
		//Marshall
		JAXBContext context = JAXBContext.newInstance("br.com.algoritimos.pojo");
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//saida para o console
        m.marshal(pojo, System.out);
        
        File f = new File("src/pojo.xml");
        Marshaller m2 = context.createMarshaller();
        //saida para arquivo
        m2.marshal(pojo, new FileOutputStream(f)); 
        
        
        //Unmarshall
        Unmarshaller um = context.createUnmarshaller();
        Object obj = um.unmarshal(new File("src/pojo.xml"));
      //saida para o console
        m.marshal(obj, System.out);
        
        Pojo pojo2 = (Pojo)obj;
        
        System.out.println("id pai:"+pojo2.getId()+" - id filho:"+pojo2.getFilho().getId());
	}
	
	
}
