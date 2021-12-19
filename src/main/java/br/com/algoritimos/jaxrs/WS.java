package br.com.algoritimos.jaxrs;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * JAX-WS 2.0 confia fortemente no uso de anota��es como especificado na A Metadata Facility for the Java Programming Language  (JSR 175) e
 * Web Services Metadata for the Java Platform (JSR 181), assim como anota��es adicionais definidas por especifica��es do JAX-WS 2.0.
 * 
 *  @WebService e @WebMethod. Um tipo valido de implementa��o endpoint deve incluir uma anota��o @WebService.
 *  � anota��o marca a classe como servi�o web.
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
	  * O valor de propriedade do nome na anota��o @WebService identifica o Web Service Description Language (WSDL) portType (neste caso, 
	  * "WS"). O serviceName ("WSService") � um servi�o WSDL. 
	  * O TargetNamespace especifica o namespace XML utilizado para o WSDL. 
	  * Todas as propriedades s�o opcionais. 
	  * Para detalhes dos valores padr�es destas propriedades, veja a se��o 4.1 das especifica��es Web Services Metadata for the Java Platform, 
	  * JSR 181 em http://www.jcp.org/en/jsr/detail?id=181.
	  * 
	  * � anota��o @WebMethod exp�e um m�todo como m�todo de web service. 
	  * O valor de propriedade do operationName na anota��o do tipo WS identifica uma opera��o WSDL (neste caso, adiciona), 
	  * e a a��o do valor de propriedade ("urn:Add") especifica um namespace XML para o WSDL dos elementos gerados a partir da opera��o do web service. 
	  * Ambas opera��es s�o opcionais. 
	  * Se voc� n�o os especifica, o valor da opera��o do WSDL muda para methodname, e o valor da a��o muda para targetNamespace do servi�o.
	  * 
	  * Para concluir um arquivo WSDL (WSService.wsdl) � criado no diret�rio build/generated, 
	  * junto com um arquivo schema (WSService_schema1.xsd), que define o diagrama para WSService.wsdl
	  * 
	  * Os componentes da tecnologia JavaBean (JavaBeans) ajudam a oficializar os m�todos de invoca��o, 
	  * respostas e exce��es especificas de servi�o. 
	  * Estas classes s�o utilizadas durante a execu��o do servi�o web em uma aplica��o no servidor.
	  * As classes de JavaBean s�o geradas no diret�rio /build/classes/service/endpoint/jaxws
	  * 
	  * As classes s�o:
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
	 * Em seguida voc� precisa empacotar e instalar o servi�o.
	 * Para fazer isso, voc� precisa especificar os detalhes sobre o servi�o na instala��o dos descritores(deployment descriptors).
	 * Os servi�os web podem ser empacotados como servlet ou como uma se��o stateless bean.
	 * Servi�os web empacotados como servlets s�o agrupados em formato de Arquivos Web (WAR).
	 * Aki o servi�o � empacotado como um servlet.
	 * 
	 * Para a estrutura do arquivo war, voc� pode dar uma olhada em pkg-war no arquivo build.xml.
	 */
	private void empacotarWar() {}
}
