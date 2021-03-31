package br.com.algoritimos.ordenacao;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.MergeSort;

public class MergeSortTest {
	
	@Test
	public void test() {
		int[] listAdHoc 	= new int[] {30,35,10,21,29,05,12,15};
		int[] listExpected 	= new int[] {05,10,12,15,21,29,30,35};
		
		MergeSort.sort(listAdHoc);
		
		assertArrayEquals(listExpected, listAdHoc);
	}

}
