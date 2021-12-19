package br.com.algoritimos.maven;

/**
 * @author Fidelis.Guimaraes
 *
 */
public class Maven {

	/**
	 * Em seu cerne, o Maven é uma ferramenta de gerenciamento e automação de construção (build) de projetos. 
	 * Entretanto, por fornecer diversas funcionalidades adicionais através do uso de plugins e estimular o emprego de melhores práticas de organização, 
	 * desenvolvimento e manutenção de projetos, é muito mais do que apenas uma ferramenta auxiliar.
	 * Um desenvolvedor que seja alocado em um projeto Java EE que utilize o Maven corretamente não terá que saber de 
	 * imediato quais dependências (bibliotecas) o projeto necessita para compilar e executar, 
	 * não precisará descobrir onde obtê-las e nem irá se preocupar em como realizar a construção do pacote do aplicativo. 
	 * Com um comando simples, como mvn install, na raiz do código-fonte do projeto, instruirão Maven a gerar o código extra necessário 
	 * (cliente de um web service, por exemplo), validar e compilar o projeto, testá-lo através de seus testes unitários e gerar o pacote com o código compilado. 
	 * Outras etapas poderiam incluir auditoria de qualidade de código, documentação, geração de estatísticas, entre diversas possibilidades.
	 * Outra característica do Maven é estimular a adoção de boas práticas, porque uma das formas utilizadas por ele para reduzir o esforço de 
	 * configuração do projeto é a utilização do conceito de programação por convenção (do inglês convention over configuration), 
	 * em que a ferramenta assume que o seu usuário fará as coisas da forma como ela preconiza como ideais (estrutura de diretórios padrão, por exemplo), 
	 * e o livra de ter que declarar algo que se repetirá em todo projeto. o incorporar as práticas aceitas pela comunidade Java como as mais 
	 * indicadas para projetos Java EE, o Maven acaba não só disseminando-as para novos desenvolvedores, 
	 * como também as padroniza entre os projetos em que ele é empregado, permitindo que novatos se localizem muito mais rapidamente dentro 
	 * de projetos novos. Obviamente, pode-se definir manualmente o que é assumido como padrão, ao preço do aumento na carga de trabalho para a 
	 * configuração inicial do projeto.
	 */
	public void definicao() {}
	
	/**
	 * Ciclo de Vida de Construção
	 * 
	 * TODO: continuar https://www.devmedia.com.br/introducao-ao-maven/25128#1
	 * 
	 * O ciclo de vida padrão possui mais de 20 estágios, dos quais os mais importantes são:
	 */
	public void buildLifecycle() {
		//mvn clean compile
		//mvn exec:java
		
		/**
		 * validate: valida se todas as informações obrigatórias do projeto estão preenchidas e são válidas;
		 * compile: compila o código-fonte;
		 * test: executa os testes unitários presentes no projeto, desde que o framework de testes utilizado seja compatível com o Maven. 
		 * Por padrão, é usado o jUnit;
		 * package: empacota o código compilado no formato definido pela tag packaging do pom.xml;
		 * integration-test: caso exista, o pacote gerado no estágio anterior é instalado em um ambiente de teste de integração;
		 * verify: executa checagens para verificar se o pacote é válido e se atende aos critérios de qualidade;
		 * install: instala o pacote no repositório local, de modo que outros projetos locais possam utilizá-lo como dependência
		 * deploy: instala o pacote em repositórios externos, efetivamente disponibilizando-o em ambientes remotos.
		 */
	}
	
	/**
	 * Como dissemos anteriormente, se o estágio test for invocado, 
	 * primeiro os estágios validate e compile serão executados.
	 * Rotineiramente, costumamos invocar o estágio install, que engloba todos os outros anteriores e, 
	 * ao final de sua execução, temos o código fonte todo compilado, 
	 * pronto para execução e disponibilizado como dependência para outros projetos locais
	 */
	
}
