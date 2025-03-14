package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.IIOException;

import main.BellmanFord;
import main.BellmanFord.Edge;

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
				System.out.println("Le r�sultat devrait �tre " + listOracle.get(1));
				line=reader.readLine();
				List <String> listTest= Arrays.asList(listOracle.get(0).split(" "));
				int v=Integer.parseInt(listTest.get(0));
				int e=Integer.parseInt(listTest.get(1));
				System.out.println("V est "+v);
				System.out.println("E est "+e);
				BellmanFord graph = new BellmanFord(v, e);
				System.out.println("Le graphe est "+listTest.get(2));
				List<String> listGraph= Arrays.asList(listTest.get(2).split("_"));
				for (int i=0;i<e;i++) {
					List<String> actuel=Arrays.asList(listGraph.get(i+1).split(","));
					graph.edge[i].source=Integer.parseInt(actuel.get(0));
					graph.edge[i].destination=Integer.parseInt(actuel.get(1));
					graph.edge[i].weight=Integer.parseInt(actuel.get(2));
				}
				graph.BellmanFordAlgo(graph, 0);
				/*System.out.println("Le premier est "+listGraph.get(1));
				System.out.println("Le second est "+listGraph.get(2));
				System.out.println("Le troisième est "+listGraph.get(3));
				System.out.println("Le quatrième est "+listGraph.get(4));
				System.out.println("Le cinquième est "+listGraph.get(5));
				System.out.println("Le sixième est "+listGraph.get(6));
				List<String> list1= Arrays.asList(listGraph.get(1).split(","));
				System.out.println("Le premier est "+list1.get(0));
				System.out.println("Le second est "+list1.get(1));
				System.out.println("Le troisième est "+list1.get(2));
				*/
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
