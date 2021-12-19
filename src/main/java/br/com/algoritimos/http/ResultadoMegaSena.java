package br.com.algoritimos.http;

import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Classe que obtem os numeros do ultimo sorteio da mega-sena.
 */
public class ResultadoMegaSena {
	private final static String URL = "http://www1.caixa.gov.br/_newincludes/home_2011/resultado_megasena.asp";

	private final static String MARCA_INICIAL_RETORNO_NAO_UTIL = "<div id='concurso_resultado'>";
	private final static String MARCA_FINAL_RETORNO_NAO_UTIL = "</div>";

	/**
	 * Metodo que se conecta ao site da CEF para obter as dezenas do ultimo sorteio.
	 * 
	 * @return array de Strings, onde cada elemento é uma dezena sorteada.
	 */
	public static String[] obtemUltimoResultado() {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(URL);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String html = httpclient.execute(httpget, responseHandler);
			return obterDezenas(html);
		} catch (Exception e) {
			// Caso haja erro, dispara exce��o.
			throw new RuntimeException("Um erro inesperado ocorreu.", e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * Tratamento da resposta HTML obtida pelo metodo obtemUltimoResultado().
	 * 
	 * @param html resposta HTML obtida
	 * @return array de Strings, onde cada elemento é uma dezena sorteada.
	 */
	private static String[] obterDezenas(String html) {
		Integer parteInicial = html.indexOf(MARCA_INICIAL_RETORNO_NAO_UTIL) + MARCA_INICIAL_RETORNO_NAO_UTIL.length();
		Integer parteFinal = html.indexOf(MARCA_FINAL_RETORNO_NAO_UTIL);
		String extracao = html.substring(parteInicial, parteFinal).replaceAll(" ", "");
		String[] numeros = extracao.split("-");
		return numeros;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(obtemUltimoResultado()));
	}

}
