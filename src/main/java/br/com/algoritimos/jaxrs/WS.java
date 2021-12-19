package br.com.algoritimos.jaxrs;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * JAX-WS 2.0 confia fortemente no uso de anotações como especificado na A Metadata Facility for the Java Programming Language  (JSR 175) e
 * Web Services Metadata for the Java Platform (JSR 181), assim como anotações adicionais definidas por especificações do JAX-WS 2.0.
 * 
 *  @WebService e @WebMethod. Um tipo valido de implementação endpoint deve incluir uma anotação @WebService.
 *  À anotação marca a classe como serviço web.
 * 
 * 
 * @author Fidelis.Guimaraes
 *
 */

@WebService(
	       name="WS",
	       serviceName="WSService",
	       targetNamespace="http://techtip.com/jaxws/sample"
	   )
public class WS {

	 /**
	  * O valor de propriedade do nome na anotação @WebService identifica o Web Service Description Language (WSDL) portType (neste caso, 
	  * "WS"). O serviceName ("WSService") é um serviço WSDL. 
	  * O TargetNamespace especifica o namespace XML utilizado para o WSDL. 
	  * Todas as propriedades são opcionais. 
	  * Para detalhes dos valores padrões destas propriedades, veja a seção 4.1 das especificações Web Services Metadata for the Java Platform, 
	  * JSR 181 em http://www.jcp.org/en/jsr/detail?id=181.
	  * 
	  * À anotação @WebMethod expõe um método como método de web service. 
	  * O valor de propriedade do operationName na anotação do tipo WS identifica uma operação WSDL (neste caso, adiciona), 
	  * e a ação do valor de propriedade ("urn:Add") especifica um namespace XML para o WSDL dos elementos gerados a partir da operação do web service. 
	  * Ambas operações são opcionais. 
	  * Se você não os especifica, o valor da operação do WSDL muda para methodname, e o valor da ação muda para targetNamespace do serviço.
	  * 
	  * Para concluir um arquivo WSDL (WSService.wsdl) é criado no diretório build/generated, 
	  * junto com um arquivo schema (WSService_schema1.xsd), que define o diagrama para WSService.wsdl
	  * 
	  * Os componentes da tecnologia JavaBean (JavaBeans) ajudam a oficializar os métodos de invocação, 
	  * respostas e exceções especificas de serviço. 
	  * Estas classes são utilizadas durante a execução do serviço web em uma aplicação no servidor.
	  * As classes de JavaBean são geradas no diretório /build/classes/service/endpoint/jaxws
	  * 
	  * As classes são:
	  *    Add.java
	  *    Add.class
	  *    AddResponse.java
	  *    AddResponse.class
	 * @param i
	 * @param j
	 * @return
	 */
	@WebMethod(operationName="add", action="urn:Add")
	 public int add(int i, int j) {
		 int k = i +j ;
         System.out.println(i + "+" + j +" = " + k);
         
         return k;
	 }
	
	/**
	 * Empacotando e instalando o arquivo WAR
	 * Em seguida você precisa empacotar e instalar o serviço.
	 * Para fazer isso, você precisa especificar os detalhes sobre o serviço na instalação dos descritores(deployment descriptors).
	 * Os serviços web podem ser empacotados como servlet ou como uma seção stateless bean.
	 * Serviços web empacotados como servlets são agrupados em formato de Arquivos Web (WAR).
	 * Aki o serviço é empacotado como um servlet.
	 * 
	 * Para a estrutura do arquivo war, você pode dar uma olhada em pkg-war no arquivo build.xml.
	 */
	private void empacotarWar() {}
}
