package br.com.algoritimos.sort;

public class MergeSort {
	   public static void sort(int[] a) {
	    //Chama o método que vai executar o algoritmo do Merge Sort.
	    mergeSort(a, a.length);
	  }
	  
	  /**
	   * Método que ordena um vetor de elementos inteiros, utilizando o algoritmo
	   * do Merge Sort.
	   * 
	   * @param tamanho - Tamanho do vetor.
	   * @param vetor  - Vetor de números inteiros.
	   */
	  private static void mergeSort(int[] a, int n) {
		  if (n < 2) {
		        return;
		    }
		    int mid = n / 2;
		    int[] l = new int[mid];
		    int[] r = new int[n - mid];

		    for (int i = 0; i < mid; i++) {
		        l[i] = a[i];
		    }
		    for (int i = mid; i < n; i++) {
		        r[i - mid] = a[i];
		    }
		    mergeSort(l, mid);
		    mergeSort(r, n - mid);

		    merge(a, l, r, mid, n - mid);
	   
	  }
	  
	  public static void merge(
			  int[] a, int[] l, int[] r, int left, int right) {
			 
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i] <= r[j]) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}
	 
}
