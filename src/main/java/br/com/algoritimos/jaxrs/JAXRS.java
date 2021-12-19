package br.com.algoritimos.jaxrs;

/**
 * A JAX-RS API trata-se de uma especificação, ela define interfaces e anotações fornecidas pelo Java EE, 
 * que podem ser utilizadas na criação de uma API RESTful. 
 * Pode criar a sua aplica��o baseada unicamente nesta especificação, entretanto o usual � fazer uso de uma biblioteca que a implemente.
 * 
 * Entre as bibliotecas existentes, as implementa��es da JAX-RS API mais conhecidas s�o RESTEasy e Jersey. Sendo que a mais utilizada � a Jersey
 * 
 * REST (Representational State Transfer)
 * 
 * O termo REST (Representational State Transfer) teve origem na disserta��o de doutorado de Roy Fielding, publicada em 2000, 
 * e � um estilo de arquitetura de software que define um conjunto de restri��es a serem usadas para a cria��o de web services. 
 * Os web services que est�o em conformidade com o estilo arquitetural REST s�o denominados RESTful web services, 
 * estes fornecem interoperabilidade entre sistemas permitindo que solicitantes acessem e manipulem representa��es 
 * textuais de recursos da Web usando um conjunto uniforme e predefinido de opera��es.
 * 
 * Os princ�pios do estilo REST s�o:

	Cliente-servidor - Ao separar a interface do usu�rio do armazenamento de dados, melhoramos a portabilidade e escalabilidade.

	Sem estado - Cada solicita��o do cliente para o servidor deve conter todas as informa��es necess�rias para entender a solicita��o. O estado da sess�o � mantido no cliente.

	Cacheable - Os dados em uma resposta a uma solicita��o devem ser impl�cita ou explicitamente rotulados como armazen�veis em cache ou n�o.

	Interface uniforme - REST � definido por quatro restri��es de interface: Identifica��o de recursos, manipula��o de recursos atrav�s de representa��es, mensagens auto descritivas e uso de hiperm�dia.

	Sistema em camadas - Uma arquitetura em camadas restringe o comportamento do componente, de forma que cada componente n�o possa "enxergar" al�m da camada imediata com a qual eles est�o interagindo.
	
 * 
 * RFC 7231 - 6. Response Status Codes (https://tools.ietf.org/html/rfc7231#section-6)
 * RFC 7231 - 4. Request Methods (https://tools.ietf.org/html/rfc7231#section-4)
 * RFC 5789 - PATCH Method for HTTP (https://tools.ietf.org/html/rfc5789)
 * RFC 8259 - The JavaScript Object Notation (JSON) Data Interchange Format (https://tools.ietf.org/html/rfc8259)
 * RFC 3339 - Date and Time on the Internet: Timestamps(https://tools.ietf.org/html/rfc3339)
 * RFC 7230 - 2.7.  Uniform Resource Identifiers(https://tools.ietf.org/html/rfc7230#section-2.7)
 * RFC 7231 - 2. Resources (https://tools.ietf.org/html/rfc7231)
 * 
 * Modelo de Maturidade de Richardson
 * 
 * Nível 0: HTTP
 * 
 * Você usa HTTP como forma de comunicação sem qualquer critério para a utilização de verbos, ou de rotas.
 * 
 * Nível 1: HTTP + Recursos
 * 
 * Sua API está exposta (roteada) seguindo o modelo de recursos. Como /users/ para listar todos os usuários e /users/123/ para obter um usuário especifico.
 * 
 * Nível 2: HTTP + Recursos + Verbos
 * 
 * Os verbos HTTP são usados de forma semântica na sua API. GET para leitura, POST para inserir, PUT para substituir um registro, DELETE para excluir...
 * 
 * Nível 3: HTTP + Recursos + Verbos + HATEOAS (significa "Hypertext as the engine of application state") Guia os caminhos e links relacionados a api
 * 
 * A sua API deve retornar uma lista de recursos (rotas) com tudo o que é possível fazer a partir da chamada original.
 * 
 * @author Fidelis.Guimaraes
 *
 */
public class JAXRS {

}
