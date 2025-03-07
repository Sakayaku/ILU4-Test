package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.IIOException;

public class Test {

	public static void test() {
		try {
			File file = new File(".\\src\\test\\ScriptTest.txt");
			FileReader fileReader = new FileReader(file.getPath());
			BufferedReader reader=new BufferedReader(fileReader);
			String line = reader.readLine();
			
			while (line!=null) {
				List<String> listOracle = Arrays.asList(line.split("="));
				System.out.println("Test avec " + listOracle.get(0));
				System.out.println("Le résultat devrait être " + listOracle.get(1));
				line=reader.readLine();
				List <String> listTest= Arrays.asList(listOracle.get(0).split(" "));
				System.out.println("E est "+listTest.get(0));
				System.out.println("V est "+listTest.get(1));
				System.out.println("Le graphe est "+listTest.get(2));
				List<String> listGraph= Arrays.asList(listTest.get(2).split("_"));
				System.out.println("Le premier est "+listGraph.get(1));
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String args[]) {
		test();
	}
}
