package br.com.algoritimos.ordenacao;


import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import br.com.algoritimos.sort.BucketSort;

public class BucketSortTest {

	@Test
	public void test() {
		float[] listAdHoc 		= new float[] {0.030f,0.035f,0.010f,0.021f,0.029f,0.005f,0.012f,0.015f};
		float[] listExpected 	= new float[] {0.005f,0.010f,0.012f,0.015f,0.021f,0.029f,0.030f,0.035f};
		BucketSort.sort(listAdHoc, listAdHoc.length);
//		assertArrayEquals(listExpected, listAdHoc, null);
	}

}
