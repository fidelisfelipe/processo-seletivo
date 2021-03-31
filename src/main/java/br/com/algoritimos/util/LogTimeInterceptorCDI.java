package br.com.algoritimos.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *  JSR-299 (Java Specification Request)
 * @author Fidelis.Guimaraes
 *
 */

@LogTimeAnottation
@Interceptor
public class LogTimeInterceptorCDI {

	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {

		long tempoInicial = System.currentTimeMillis();

		Object resultado = contexto.proceed();

		long tempoTotal = System.currentTimeMillis() - tempoInicial;

		System.out.println("Tempo de execucao do metodo: " + contexto.getTarget().getClass() + "."
				+ contexto.getMethod().getName() + ": " + tempoTotal + " ms");

		return resultado;
	}

	@PostConstruct
	public void ativa() {
		System.out.println("Iniciando chamada do método...");
	}

	@PreDestroy
	public void desativa() {
		System.out.println("Finalizando chamada do método...");
	}

}