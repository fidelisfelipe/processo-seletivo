package br.com.algoritimos.ordenacao;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.BubbleSort;

public class BubbleSortTest {

	@Test
	public void test() {
		
		
		int[] listAdHoc 		= new int[] {30, 35, 10, 21, 29,  5, 12, 15};
		int[] listExpected 		= new int[] { 5, 10, 12, 15, 21, 29, 30, 35};
		
		BubbleSort.sort(listAdHoc);
		
		assertArrayEquals(listExpected, listAdHoc);
	}
	
}
