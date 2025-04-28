package test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.IIOException;

import main.BellmanFord;
import main.BellmanFord.Edge;
import main.BellmanFord_mutants;
import main.Couleur;

public class Test {
	
	private static int nbPass=0;
	private static int nbFail=0;
	public static int CAPACITE=1028653052;
	
	public void test(File file) {
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
				List<String> listOraclePartie= Arrays.asList(listOracle.get(1).split("_"));
				List<String> listOracleCyCleNegatif = Arrays.asList("0");
				
				
				try {
					System.out.println(listTest);
					if (listTest.size()!=4) {
						throw new EntriesNumberException("Nombre d'entrées "+listTest.size());
					}
					int v=Integer.parseInt(listTest.get(0));
					System.out.println("Nombre de sommet : "+v);
					int e=Integer.parseInt(listTest.get(1));
					System.out.println("Nombre d'arcs : "+e);
					int d=Integer.parseInt(listTest.get(2));
					System.out.println("Sommet de départ : "+d);
					List<String> listGraph= Arrays.asList(listTest.get(3).split("_"));
					if (listGraph.size()!=e) {
						throw new GraphException("Nombre d'arc attendu ("+e+") != nombre d'arcs donné ("+listGraph.size()+")");
					}
					BellmanFord graph = new BellmanFord(v, e); // A modifier ici pour l'appels aux mutants
					for (int i=0;i<e;i++) {
						List<String> actuelTest=Arrays.asList(listGraph.get(i).split(","));
						graph.edge[i].source=Integer.parseInt(actuelTest.get(0));
						graph.edge[i].destination=Integer.parseInt(actuelTest.get(1));
						graph.edge[i].weight=Integer.parseInt(actuelTest.get(2));
					}
					System.out.println("Graphe : "+listGraph);
					int[] dist=graph.BellmanFordAlgo(graph, d);
					if (listOraclePartie.size()!=v) {
						if (listOraclePartie.equals(listOracleCyCleNegatif)==false) {
							throw new OracleSizeException("Taille de l'oracle "+listOraclePartie.size());
						}
						
					}
					for (int i=0;i<dist.length;i++) {
						if (Integer.parseInt(listOraclePartie.get(i))!=dist[i]) {
							pass=false;
						}
					}
					if (pass) {
						System.out.println("Oracle :"+listOraclePartie+Couleur.VERT+" --> Pass"+Couleur.RESET);
						nbPass++;
					}else {
						System.out.println(Couleur.ROUGE+"Oracle :"+listOraclePartie+" --> Fail"+Couleur.RESET);
						nbFail++;
					}
					System.out.println("\n");
				}catch(NumberFormatException e1) {
					System.out.println(Couleur.VIOLET+"Le nombre de sommet, d'arcs et le sommet de départ doivent être un nombre !"+" ("+e1.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				}catch(IndexOutOfBoundsException e2) {
					System.out.println(Couleur.VIOLET+"Le sommet de départ et les sommets dans le graphe doivent être strictement inférieur au nombre de sommet indiqué au départ et positifs !"+" ("+e2.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				} catch (EntriesNumberException e3) {
					System.out.println(Couleur.VIOLET+"Le programme attend quatre entrées !"+" ("+e3.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				}catch (NegativeArraySizeException e4) {
					System.out.println(Couleur.VIOLET+"Les valeurs doivent être positives !"+" ("+e4.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				} catch (GraphException e5) {
					System.out.println(Couleur.VIOLET+"Le graphe ne correspond pas au nombre d'arc !"+" ("+e5.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				} catch (OracleSizeException e6) {
					System.out.println(Couleur.VIOLET+"L'oracle doit faire la taille du nombre de sommet !"+" ("+e6.getMessage()+") "+Couleur.RESET);
					nbFail++;
					System.out.println("\n");
				}catch (OutOfMemoryError e6) {
						System.out.println(Couleur.VIOLET+"Dépassement des capacités !"+" ("+e6.getMessage()+") "+Couleur.RESET);
						nbFail++;
						System.out.println("\n");
				}
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String args[]) {
		Test test= new Test();
		File fileQuestion1 = new File(".\\src\\test\\JeuxDeTestEtOracle.txt");
		System.out.println(Couleur.BLEU+"-------------------- Résultats Jeux de test et oracles --------------------\n"+Couleur.RESET);
		test.test(fileQuestion1);
		File fileQuestion2 = new File(".\\src\\test\\InstructionsQuestion2.txt");
		System.out.println(Couleur.BLEU+"-------------------- Résultat Instructions pour graphe particulier --------------------\n"+Couleur.RESET);
		test.test(fileQuestion2);
		File fileQuestion4 = new File(".\\src\\test\\JeuxDeTestPartitionsEtCategories.txt");
		System.out.println(Couleur.BLEU+"-------------------- Résultats Jeux de test méthode partitions et catégories --------------------\n"+Couleur.RESET);
		test.test(fileQuestion4);
		File fileQuestion5 = new File(".\\src\\test\\JeuxDeTestLimites.txt");
		System.out.println(Couleur.BLEU+"-------------------- Résultats Jeux de test méthode limites --------------------\n"+Couleur.RESET);
		test.test(fileQuestion5);
		System.out.println(Couleur.VERT+""+nbPass+Couleur.RESET+" pass et "+Couleur.ROUGE+nbFail+Couleur.RESET+" fail sur "+(nbPass+nbFail)+" test(s)");
	}
}
