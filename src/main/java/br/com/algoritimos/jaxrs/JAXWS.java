package br.com.algoritimos.jaxrs;

/**
 * 
 * JRS 224: https://www.jcp.org/en/jsr/detail?id=224
 * 
 * Java API para Servi�os Web XML (JAX-WS) 2.0, JSR 224, � uma parte importante da plataforma Java EE 5. 
 * Uma continua��o liberada de Java API para XML-based RPC 1.1 (JAX-RPC), 
 * JAX-WS simplifica a tarefa de desenvolver servi�os web utilizando tecnologia Java. 
 * Encaminha alguns dos resultados em JAX-RCP 1.1 providenciando suporte para protocolos m�ltiplos assim como SOAP 1.1, SOAP 1.2, XML e 
 * suprindo uma facilidade de manuten��o para protocolos adicionais junto com HTTP. 
 * JAX-WS utiliza JAXB 2.0 para liga��o de dados e suportes habituais para controlar servi�os gerados em interfaces endpoint. 
 * Com seu apoio para anota��es, JAX-WS simplifica o desenvolvimento de servi�os web e diminui o tamanho do tempo de execu��o dos arquivos Jar.
 * 
 * Em Java 11, JAX-WS foi removido do JDK
 * 
 * @author Fidelis.Guimaraes
 *
 */
public class JAXWS {

	/**
	 * 
	 * 
	 * <plugin>
	 * 		<groupId>org.codehaus.mojo</groupId>
	 * 		<artifactId>jaxws-maven-plugin</artifactId>
	 * 		<executions>
	        	<execution>
		            <phase>generate-sources</phase>
		            <goals>
		                <goal>wsimport</goal>
		            </goals>
		            <configuration>
		                <extension>true</extension>
		                <packageName>br.com.algoritimos.jaxws.ws</packageName>
		                <wsdlFiles>
		                    <wsdlFile>${basedir}/src/main/resources/wsdl/service.wsdl</wsdlFile>
		                </wsdlFiles>
		                <wsdlLocation>/wsdl/service.wsdl</wsdlLocation>
		            </configuration>
        		</execution>
    		</executions>
		</plugin>
	 * 
	 */
	public void gerarWebServiceComMaven() {}
	
}
