package modeles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Classe representant une tuile du terrain dans le module du jeu. Communement
 * appelée "Case" par les joueurs
 * 
 * @author amine BOUABDALLAOUI, hugo COURTECUISSE, edouard DELANOE
 */
public class Tuile {
	/**
	 * occupant de la case
	 */
	private Joueur occupant;
	/**
	 * pour permetre au modèle d'envoyer des évènements aux vues.
	 */
	private PropertyChangeSupport pcsOccupant = new PropertyChangeSupport(this);

	/**
	 * Initialise la tuile sans occupant
	 */
	public Tuile() {
		occupant = null;
	}

	/**
	 * Retourne true si aucun joueur n'est sur la case.
	 * 
	 * @return boolean true si ausun joueur n'est sur la case, false si il y'en
	 *         a un
	 */
	public boolean estVide() {
		return this.occupant == null;
	}

	/**
	 * Retourne l'occupant ne marche uniquement que si la case n'est pas vide !
	 * 
	 * @return occupant une instance de Joueur qui occupe la case
	 */
	public Joueur getOccupant() {
		return this.occupant;
	}

	/**
	 * Ajoute et enlève l'occupant sur la case
	 * 
	 * @param occupant
	 *            : L'occupant de la case, une instance de Joueur
	 */
	public void setOccupant(Joueur occupant) {

		
		Joueur ancienOccupant = (Joueur) this.occupant;

		
		this.occupant = occupant;


		this.pcsOccupant.firePropertyChange("occupant", ancienOccupant, this.occupant);
	}

	/**
	 * Ajout d'un écouteur de changement d'occupant.
	 */
	public void ajouterEcouteurOccupant(PropertyChangeListener ecouteur) {
		pcsOccupant.addPropertyChangeListener(ecouteur);
	}

	/**
	 * Suppression d'un écouteur de changement d'occupant.
	 */
	public void retirerOccupantEcouteur(PropertyChangeListener ecouteur) {
		pcsOccupant.removePropertyChangeListener(ecouteur);
	}

}
