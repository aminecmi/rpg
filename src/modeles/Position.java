package modeles;

/**
 * La position d'un joueur ou d'une tuile. Permet de gérer les coordonnées
 * 
 */
public class Position {
	private int ligne; // La ligne ou la coordonnée X dans un plan
	private int col; // La ligne ou la coordonnée Y dans un plan

	public Position(int ligns, int cols) {
		this.ligne = ligns;
		this.col = cols;
	}

	public int getLigne() {
		return ligne;
	}

	public int getCol() {
		return col;
	}

}
