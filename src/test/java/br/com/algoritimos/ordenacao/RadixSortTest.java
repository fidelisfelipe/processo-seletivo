package br.com.algoritimos.ordenacao;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.RadixSort;

public class RadixSortTest {

	@Test
	public void test() {
	    int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7};
	    RadixSort.sort(numbers, numbers.length);
	    int[] numbersSorted = {7, 37, 68, 123, 134, 221, 387, 468, 769};
	    assertArrayEquals(numbersSorted, numbers); 
	}
	
}
