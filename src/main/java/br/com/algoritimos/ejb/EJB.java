package br.com.algoritimos.ejb;

import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * JSR 345 - EJB3.2
 * 
 * {@linkplain http://download.oracle.com/otndocs/jcp/ejb-3_2-fr-spec/index.html}
 * {@linkplain https://www.devmedia.com.br/ejb-introducao-ao-novo-enterprise-javabeans-3-2/30807}
 * {@linkplain https://qastack.com.br/programming/12872683/what-is-an-ejb-and-what-does-it-do}
 * {@linkplain http://www.argonavis.com.br/cursos/java/j530/j530_ejb2.pdf}
 * @author Fidelis.Guimaraes
 *
 */
public class EJB {
	
	/**
	 * Um bean com estado de sessão
	 * 
	 * Um bean com estado de sessão(stateful session bean) tem como caracteristicas manter o estado conversacional para um cliente especifico. 
	 * O estado é armazenado nos valores das variaveis de instancia do bean e nos interceptadores associados.
	 * Podemos definir um simples bean com estado de sessãousando a anota��o 
	 * 
	 * Todos os metodos publicos do bean exemplificados podem ser invocados por um cliente.
	 * 
	 * Este estilo de declaração de bean é chamado de visualizacao sem interface. 
	 * Esse bean é apenas localmente acessível aos clientes empacotados no mesmo arquivo de deploy (como um war)
	 * 
	 * Se um bean precisa ser remotamente acessível, ele deve definir uma interface de negócio separada anotada com @Remote. 
	 */
	@Stateful
	class StatefulSessionBeans{
		
		/**
		 * Um cliente pode remover um bean com estado de sessãoapenas invocando o metodo "remover". 
		 * Chamando este método temos como resultado uma chamado do container ao metodo com a anotação @PreDestroy. 
		 * Remover um bean com estado de sessãosignifica que o estado da instancia espec�fica para aquele cliente nao existe mais.
		 */
		@Remove
		public void remover() {}
		
	}
	
	/**
	 * Um bean sem estado de sessao
	 * 
	 * Um bean sem estado de sessão nao contem qualquer estado conversacional para um cliente especifico.
	 * Todas as instancias de um bean sem estado de sessão sao equivalentes, 
	 * portanto o container pode escolher delegar um metodo invocado por um cliente para qualquer instancia disponivel no pool do container. 
	 * Visto que os beans sem estado de sessão nao possuem qualquer estado, eles nao precisam ser passivados.
	 *
	 */
	@Stateless
	class StatelessSessionBeans{
		
	}
	
	/**
	 * Um bean de sessãoSingleton
	 *
	 * Um bean de sessãoSingleton � um componente que � instanciado uma �nica vez por aplicação e fornece um acesso bastante facilitado ao estado compartilhado. 
	 * Se o container for distribu�do em m�ltiplas JVM, cada aplica��o ter� uma inst�ncia do Singleton para cada JVM. 
	 * Um bean de sessãoSingleton � explicitamente projetado para ser compartilhado e suportar concorr�ncia.
	 *
	 * O Container � respons�vel por quando inicializar uma inst�ncia de um bean de sessãoSingleton. 
	 * Contudo, podemos opcionalmente marcar o bean para inicializar
	 * antes atrav�s da anota��o @Startup
	 * O Container agora inicializar� todos esses Singletons em tempo de inicializa��o, executando o c�digo que estiver anotado com @PostConstruct, 
	 * antes da aplica��o tornar-se dispon�vel e antes que qualquer solicita��o do cliente seja atendida.
	 * 
	 * Podemos especificar uma inicializa��o explicita de beans de sessãoSingleton usando @DependsOn("MinhaSessionBeanPrimaria")
	 * O container assegura que o bean MinhaSessionBeanPrimaria � inicializado antes do bean SingletonSesionBean.
	 * Um bean Singleton suporta os m�todos de callback do ciclo de vida "PostConstruct" e "PreDestroy". 
	 * Al�m disso, o Singleton tamb�m suporta acesso concorrente.
	 * 
	 * Por padr�o, um bean Singleton � marcado para ter concorr�ncia gerenciada pelo container, 
	 * mas opcionalmente pode ser marcado para ter concorr�ncia gerenciada por bean.
	 * 
	 * Concorr�ncia gerenciada por Container � baseada em metadados com bloqueio a n�vel de m�todo onde cada m�todo � associado com um bloqueio do tipo Read (compartilhado) ou Write (exclusivo).
	 * Um bloqueio de leitura (Read) permite chamadas simult�neas do m�todo. Um bloqueio de leitura (Write) 
	 * aguarda o processamento de uma invoca��o completar antes de permitir que a pr�xima invoca��o prossiga.
	 * As anota��es @Lock(LockType.READ) e @Lock(LockType.WRITE) s�o utilizadas para especificar o tipo de bloqueio. 
	 * Mas, por padr�o, um bloqueio Write � associado com cada m�todo do bean.
	 * Essas anota��es podem ser especificadas na classe, um m�todo de neg�cio da classe, ou ambos. 
	 * Um valor especificado no m�todo sobrescreve um valor especificado na classe. 
	 * A concorr�ncia gerenciada por bean requer que o desenvolvedor gerencie a concorr�ncia utilizando primitivas de sincroniza��o da linguagem Java como "synchronized" e "volatile".
	 *
	 */
	@Singleton
	class SingletonSesionBean{
		
	}
	
	void recursos() {
		
	}
}
