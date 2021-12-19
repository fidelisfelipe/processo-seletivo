package br.com.algoritimos.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ejb.Stateful;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;

/**
 * Eventos do Ciclo de Vida
 * 
 * Métodos interceptadores do ciclo de vida podem ser definidos tanto para beans de sessão quanto para beans de mensagens.
 * As anotações @AroundConstruct, @PostConstruct, @PreDestroy, @PostActivate, e @PrePassivate 
 * são utilizados para definir métodos interceptadores para eventos do ciclo de vida dos beans.
 * A anotações @AroundConstruct pode ser definida apenas numa classe interceptadora, 
 * enquanto que todas as outras anotações podem ser definidos em uma classe interceptadora e/ou diretamente numa classe bean.
 * 
 */
public class EventosCicloDeVidaSessionBeans {

	/**
	 * Interceptador @AroundConstruct
	 */
	
	/**
	 * A anotaçção @AroundConstruct designa um método interceptor que recebe uma chamada de retorno quando o construtor da classe de destino � invocado.
	 * Este método interceptor pode ser definido apenas em classes de interceptoras e/ou superclasses de classes de interceptores e não pode ser definido na classe alvo.
	 *
	 */
	@Inherited
	@InterceptorBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
	//Definindo um AroundConstructor.
	public @interface MeuAroundConstruct {
	}
	//Um interceptador vinculado (Interceptor binding)
	@MeuAroundConstruct
	@Interceptor
	public class MeuAroundConstructInterceptador {
	  @AroundConstruct
	  public void validateConstructor(InvocationContext context) {}
	}
	//E finalmente, o interceptador pode ser especificado no bean
	//O método validateConstructor é chamado toda vez que o construtor do bean MeuBean é chamado.
	@MeuAroundConstruct
	@Stateful
	public class MeuBean {}
	
	/**
	 * Interceptador @PostConstruct
	 */
	
	
}
