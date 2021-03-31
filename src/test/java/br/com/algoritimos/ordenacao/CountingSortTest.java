package br.com.algoritimos.ordenacao;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.CountingSort;

public class CountingSortTest {

	@Test
	public void test() {
		char[] listAdHoc 	= new char[] {30,35,10,21,29,05,12,15};
		char[] listExpected 	= new char[] {05,10,12,15,21,29,30,35};
		CountingSort.sort(listAdHoc);
		assertArrayEquals(listExpected, listAdHoc);
	}
	
}
