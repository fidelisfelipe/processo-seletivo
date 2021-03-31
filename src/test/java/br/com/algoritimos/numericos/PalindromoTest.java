package br.com.algoritimos.numericos;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class PalindromoTest {

	@Test
	public void testPalindromosGerados() {
		Palindromo p = new Palindromo();
		Collection<Integer> result = p.gerarPalindromosPorIntervalo(0l,104001l);
		
		Set<Integer> desordenado = new HashSet<Integer>();
		List<Integer> ordenado = new ArrayList<Integer>();
		
		for (Integer object : result) {
			StringBuilder sb = new StringBuilder();
			sb.append(object);
			assertTrue("o numero: "+object+" deveria ser um palindromo, mas não é.", object.toString().equals(sb.reverse().toString()) && isPalindromo(object));
			
			ordenado.add(object);
			desordenado.add(object);
		}
		
	}
	
	private boolean isPalindromo(Integer orig)
	{
	    long reversed = 0, n = orig;

	    while (n > 0)
	    {
	        reversed = reversed * 10 + n % 10;
	        n /= 10;
	    }

	    return orig == reversed;
	}
	
}
