package modeles;

import java.util.ArrayList;
/**
 * joueur ordinateur
 *
 */
public class joueurOrdinateur extends Joueur {
	public joueurOrdinateur(Terrain terrain, Position position) {
		super(terrain, position);
	}

	/**
	 * Cette méthode permet au pionMachine de récupérer la position d'un joueur
	 * se trouvant dans son rayon d'attaque! Attention cette méthode ne met pas
	 * a jours la position du joueur sur le plateau
	 * 
	 * @return position La position voulu par le joueur
	 */
	public Position proposeDeplacement() {

		int col, max, min;
		ArrayList<Joueur> joueursDetectes = new ArrayList<Joueur>();
		ArrayList<Position> tuilesAccesibles = new ArrayList<Position>();

		Position position;

		min = this.getPosition().getLigne();
		max = this.getPosition().getLigne();

		for (col = this.getPosition().getCol() - super.deplacement; col <= this
				.getPosition().getCol()
				+ super.deplacement; col++) {
			for (int i = min; i <= max; i++) {

				if (terrain.positionCorrecte(new Position(i, col))) {// position
					// correcte
					position = new Position(i, col);
					if (!terrain.getTuile(position).estVide()) {// si la case
						// est occupée
						joueursDetectes.add(terrain.getTuile(position)
								.getOccupant());// on ajoute la case a l'array
						// pour l'utilisre plus tard

					} else
						tuilesAccesibles.add(position);// sinon la case est
					// libre

				}
			}
			if (col >= this.getPosition().getCol()) {// pour décrémanter après
				// avoir dépassé la case
				// du milieu
				min = min + 2;
				max = max - 2;
			}
			min = min - 1;
			max = max + 1;

		}

		if (joueurLePlusPret(joueursDetectes) != null) {
			position = joueurLePlusPret(joueursDetectes).getPosition();// si il
			// y a
			// un
			// joueur
			// proche,
			// on se
			// dirige
			// vers
			// lui

		} else {
			position = terrain.getTuileVidePourDeplacement(tuilesAccesibles);// sinon
			// on
			// se
			// délace
			// aléatoirement
			// (avec
			// points
			// déplacement)
		}
		return position;
	}

	/**
	 * Permet de trouver le joueur le plus pret du pionOrdinateur qui jou
	 * 
	 * @param joueurDetecter
	 * @return joueurLePlusPret
	 */

	public Joueur joueurLePlusPret(ArrayList<Joueur> joueurDetecter) {

		Joueur joueurLePlusPret = null;

		int colLePlusPret;
		int ligneLePlusPret;
		int distance = super.deplacement;
		int plusproche;

		for (int i = 0; i < joueurDetecter.size(); i++) {
			if (this.getNom() != joueurDetecter.get(i).getNom()) {

				colLePlusPret = this.getPosition().getCol()
						- joueurDetecter.get(i).getPosition().getCol();
				ligneLePlusPret = this.getPosition().getLigne()
						- joueurDetecter.get(i).getPosition().getLigne();

				plusproche = colLePlusPret + ligneLePlusPret;

				if (plusproche < 0) {
					plusproche = -plusproche;
				}

				if (distance >= plusproche) {

					distance = Math.abs(colLePlusPret + ligneLePlusPret);

					joueurLePlusPret = joueurDetecter.get(i);
				}
			}
		}

		return joueurLePlusPret;

	}
}
