package modeles;

import java.util.ArrayList;

/**
 * Classe représentant le terrain de jeu. Le terrain est un tableau de tuiles.
 * Est responsable de vérifier si une position donnée est une position possible
 * d'une tuile du terrain, de fournir une tuile vide du terrain, de positionner
 * un joueur sur une tuile du terrain.
 * 
 */
public class Terrain {
	private int lignes; // Le nombre de lignes du terrain
	private int cols; // Le nombre de colonnes du terrain
	private Tuile[][] tab; // tableau de Tuiles: Les Lignes est la première dimension du tableau

	/**
	 * Initialise le terrain sans joueur
	 * 
	 * @param lignes
	 *            Le nombre de lignes du terrain
	 * @param cols
	 *            Le nombre de colonnes du terrain
	 */
	public Terrain(int lignes, int cols) {
		this.lignes = lignes;
		this.cols = cols;

		tab = new Tuile[lignes][cols];

		for (int i = 0; i < lignes; i++)
			for (int j = 0; j < cols; j++)
				this.tab[i][j] = new Tuile();

	}

	/**
	 * Retourne la position d'une tuile vide du plateau, choisie aléatoirement.
	 * 
	 * @return position une instance de position générée aléatoirement
	 */
	public Position getTuileVideAleatoire() {
		Position position;

		do {
			position = new Position((int) (Math.random() * lignes), (int) (Math
					.random() * cols));
		} while (!(getTuile(position).estVide()));// tuile doit être vide

		return position;
	}

	/**
	 * Retourne la position d'une tuile vide du plateau, choisie aléatoirement
	 * en fonction des points de déplacement.
	 * 
	 * @param tuilesAccesibles
	 *            arraylist de tuiles accessibles
	 * @return position une instance de position générée aléatoirement en
	 *         foction des tuiles accéssibles autours du pion
	 */
	public Position getTuileVidePourDeplacement(
			ArrayList<Position> tuilesAccesibles) {

		return tuilesAccesibles.get((int) (Math.random() * tuilesAccesibles
				.size()));
	}

	/**
	 * Retourne la tuile en position p sur le terrain. Permet de faire le lien
	 * entre une position et une tuile
	 * 
	 * @param p
	 *            La position de la tuile désirée
	 */
	public Tuile getTuile(Position p) {
		return tab[p.getLigne()][p.getCol()];
	}

	/**
	 * Retourne l'occupant de la tuile en position p, null si la tuile est vide.
	 * 
	 * @param p
	 *            La position de la tuile
	 * @return null ou occupant Retourne null si il n'y a pas d'occupant ou une
	 *         instance de Joueur si il y'a un occupant
	 */
	public Joueur getOccupant(Position p) {

		if (getTuile(p).estVide())
			return null;
		else
			return getTuile(p).getOccupant();

	}

	/**
	 * Positionne les joueurs sur le terrain. Doit choisir des positions valides
	 * du terrain. Un seul joueur par tuile.
	 * 
	 * @param joueur
	 *            Joueur instance de type Joueur qu'il faut positionner
	 */
	public void PositionnerJoueurs(Joueur joueur, Position position) {

		// Met a jour la tuile l'ancienne tuile
		getTuile(joueur.getPosition()).setOccupant(null);
		
		// On modifie la position du joueur dans la classe joueur
		joueur.setPosition(position);

		// Met a jour la nouvelle tuile avec un occupant
		getTuile(position).setOccupant(joueur);

	}

	/**
	 * Vérifie que p est une position correcte du terrain.
	 * 
	 * @return true ssi p designe une case du tableau
	 */
	public boolean positionCorrecte(Position p) {
		boolean positionOK = false;
		if ((p.getCol() >= 0 && p.getCol() < this.cols)
				&& (p.getLigne() >= 0 && p.getLigne() < this.lignes)) { // les
																		// ligne/
																		// colonnes
																		// sont
																		// dans
																		// le
																		// tableau
			positionOK = true;
		}
		return positionOK;
	}

	public int getLigne() {
		return lignes;
	}

	public int getCol() {
		return cols;
	}

}
