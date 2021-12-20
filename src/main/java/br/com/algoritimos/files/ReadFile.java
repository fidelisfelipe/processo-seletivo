package br.com.algoritimos.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFile {
	private static final String baseDir = "src"+File.separator+"main"+File.separator+"resources"+File.separator;

	public static void main(String[] args) throws IOException {
		Path file = Paths.get(baseDir+"d12.txt");
		Stream<String> streamList = Files.lines(file);
		
		System.out.println("deve ser maior que 0" + (streamList.count() > 0));
		
	}
	
}
