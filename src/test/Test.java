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

	public static void test(File file) {
		try {
			FileReader fileReader = new FileReader(file.getPath());
			BufferedReader reader=new BufferedReader(fileReader);
			String line = reader.readLine();
			
			while (line!=null) {
				boolean pass=true;
				List<String> list = Arrays.asList(line.split("="));
				line=reader.readLine();
				
				List <String> listTest= Arrays.asList(list.get(0).split(" "));
				List <String> listOracle= Arrays.asList(list.get(1).split(" "));
				
				int v=Integer.parseInt(listTest.get(0));
				int e=Integer.parseInt(listTest.get(1));
				BellmanFord graph = new BellmanFord(v, e);
				List<String> listGraph= Arrays.asList(listTest.get(2).split("_"));
				for (int i=0;i<e;i++) {
					List<String> actuelTest=Arrays.asList(listGraph.get(i).split(","));
					graph.edge[i].source=Integer.parseInt(actuelTest.get(0));
					graph.edge[i].destination=Integer.parseInt(actuelTest.get(1));
					graph.edge[i].weight=Integer.parseInt(actuelTest.get(2));
				}
				int[] dist=graph.BellmanFordAlgo(graph, 0);
				
				List<String> listOraclePartie= Arrays.asList(listOracle.get(1).split("_"));
				for (int i=0;i<dist.length;i++) {
					if (Integer.parseInt(listOraclePartie.get(i))!=dist[i]) {
						System.out.println("i= "+i+" dist[i]= "+dist[i]);
						pass=false;
					}
				}
				System.out.println("Graphe : "+listGraph+" Oracle : "+listOraclePartie+" Résultat : "+pass);
				
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String args[]) {
		File fileQuestion1 = new File(".\\src\\test\\Question1.txt");
		test(fileQuestion1);
		File fileQuestion2 = new File(".\\src\\test\\Question2.txt");
		test(fileQuestion2);
		File fileQuestion4 = new File(".\\src\\test\\Question4.txt");
		test(fileQuestion4);
	}
}
