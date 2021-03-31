package br.com.algoritimos.ordenacao;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.QuickSort;

public class QuickSortTest {
	
	@Test
	public void test() {
		int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7};
		QuickSort.sort(numbers, 0, numbers.length - 1);
	    int[] numbersSorted = {7, 37, 68, 123, 134, 221, 387, 468, 769};
	    assertArrayEquals(numbersSorted, numbers); 
	}
	 
}
