package br.com.algoritimos.ordenacao;

import java.util.Collections;
import java.util.List;

public class Ordenacao {
	
	public void sort(List<Integer> v) {
		long inicio = System.currentTimeMillis();
		Collections.sort(v);
		long fim = System.currentTimeMillis() - inicio;
	    System.out.println("executado sort nativo em: "+fim+" ms");
	}

	public void bolha(List<Integer> v) {
		long inicio = System.currentTimeMillis();
		// for utilizado para controlar a quantidade de vezes que o vetor será ordenado.
	    for(int i = 0; i < v.size() -1; i++) {
	      // for utilizado para ordenar o vetor.
	      for(int j = 0; j < v.size() -1 - i; j++) {
	        /* Se o valor da posição atual do vetor for maior que o proximo valor,
	          então troca os valores de lugar no vetor. */
	        if(v.get(j) > v.get(j + 1)) {
	          int aux1 = v.get(j);
	          v.set(j, v.get(j + 1));
	          v.set(j + 1, aux1);
	        }
	      }
	    }
	    long fim = System.currentTimeMillis() - inicio;
	    System.out.println("executado bolha em: "+fim+" ms");
	}
	
}
