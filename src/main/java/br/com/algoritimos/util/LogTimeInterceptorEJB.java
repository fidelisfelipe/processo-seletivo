package br.com.algoritimos.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
/**
 * Using in EJB : @Interceptors(LogTimeInterceptorEJB.class)
 * @author Fidelis.Guimaraes
 *
 */
public class LogTimeInterceptorEJB {
	
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {
		
		long tempoInicial = System.currentTimeMillis();
		
		Object resultado = contexto.proceed();
		
		long tempoTotal = System.currentTimeMillis() - tempoInicial;
		
		System.out.println("Tempo de execucao do metodo: "
						+ contexto.getTarget().getClass() + "." + contexto.getMethod().getName() + ": " + tempoTotal + " ms");

		return resultado;
	}

}