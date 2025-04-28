package main;

public enum Couleur {

	VERT("\u001B[32m"),
	ROUGE("\u001B[31m"),
	VIOLET("\033[95m"),
	BLEU("\u001B[34m"),
	RESET("\u001B[0m");
	
	private String couleur="";

	Couleur(String couleur) {
		this.couleur=couleur;
	}
	
	public String toString() {
		return couleur;
	}
	
}
