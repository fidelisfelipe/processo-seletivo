package br.com.algoritimos.sort;

import java.util.List;

public class BubbleSort {

	public static void sort(List<Integer> v) {
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
	}
	
	public static void sort(int [] v) {
		// for utilizado para controlar a quantidade de vezes que o vetor será ordenado.
	    for(int i = 0; i < v.length; i++) {
	      // for utilizado para ordenar o vetor.
	      for(int j = 0; j < v.length -1 - i; j++) {
	        /* Se o valor da posição atual do vetor for maior que o proximo valor,
	          então troca os valores de lugar no vetor. */
	        if(v[j] > v[j + 1]) {
	          int aux1 = v[j];
	          v[j] = v[j + 1];
	          v[j + 1] = aux1;
	        }
	      }
	    }
	}

}
