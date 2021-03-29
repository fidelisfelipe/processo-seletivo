package br.com.algoritimos.numericos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;


public class Palindromo {
	public Collection<Integer> gerarPalindromosPorIntervalo(Long inicioIntervalo, Long fimIntervalo) {
		List<Integer> palindromoList = new ArrayList<Integer>(0);
		
		try {
			Long num1 = inicioIntervalo;
			Long num2 = fimIntervalo;
			if(num1 < 0 || num2 < 0) {
			        throw new IllegalArgumentException("Voce deve informar 2 numeros inteiros positivos!");
			} else if (num1 > num2) {
				throw new IllegalArgumentException("O primeiro numero deve ser menor que o segundo numero!");
			} else {
			    System.out.println("Os palindromos do intervalo entre "+num1+" e "+num2+ " : ");
				for(Long i = num1; i <= num2; i++) {
				        String reverso = new StringBuilder( i + "").reverse().toString();
					if(reverso.equals(i + "")) {
					        palindromoList.add(i.intValue());
				        }
			        }
			}
		} catch (InputMismatchException e) {
		        System.out.println("Os numeros devem ser inteiros, com no maximo 64 bits!");
		}
		return palindromoList;
	}
}