package vue.swing;


import javax.swing.JTextPane;

import modeles.Combat;
import modeles.EtatJeu;
import modeles.Joueur;
import modeles.joueurReel;
import modeles.joueurOrdinateur;
import modeles.Position;
import modeles.pionsReg;

/**
 * Classe principale du jeu!
 * 
 */
public class VuePrincipale {
	
	/**
	 * Permet de joueur 
	 * 
	 * @param etat
	 * @param fenetreText
	 */
	public void commencerJeu(EtatJeu etat, JTextPane fenetreText) {
		if ((int) (Math.random() * 10) + 1==10)
			new pionsReg(etat.getTerrain());
	

		fenetreText.setText(fenetreText.getText()
				+ "\nC'est le tour du joueur: "
				+ etat.getJoueurCourrant().getNom()+"\n");

		if (etat.getJoueurCourrant() instanceof joueurReel) {
			// Si on est un joueur utilisateur on attend le click
		} else {

			// retourne la position du pionMachine ou du pion utilisateur
			Position positionChoisie = ((joueurOrdinateur) etat
					.getJoueurCourrant()).proposeDeplacement();

			jouerUnTour(etat, fenetreText, positionChoisie);
		}

	}
	
	/**
	 * Cette méthode commune au pions reel et au pions machine permet de detecter un combat 
	 * ou un pions regénérateur et de positionner le joueur sur le terrain
	 * 
	 * 
	 * @param etat
	 * @param fenetreText
	 * @param positionChoisie
	 */
	public void jouerUnTour(EtatJeu etat, JTextPane fenetreText,
			Position positionChoisie) {

		Joueur joueurPerdant;

		// Si le joueur a choisit une case différente de la sienne
		if (etat.getTerrain().getOccupant(positionChoisie) != etat
				.getJoueurCourrant()) {
			//Si il y a quelqu'un sur la case choisie
			if (etat.getTerrain().getOccupant(positionChoisie) != null) {
				//Si le pion est un pionReg
				if (etat.getTerrain().getOccupant(positionChoisie) instanceof pionsReg) {
					fenetreText.setText(fenetreText.getText()
							+ "\n"+etat.getJoueurCourrant().getNom()+" utilise une potion \n");
					etat.getJoueurCourrant().setPv(
							etat.getJoueurCourrant().getPv()
									+ etat.getTerrain().getOccupant(
											positionChoisie).getHeritage());
					etat.getTerrain().getTuile(positionChoisie).setOccupant(
							null);
					etat.getTerrain().PositionnerJoueurs(
							etat.getJoueurCourrant(), positionChoisie);
				} else {
					fenetreText
							.setText(fenetreText.getText()
									+ "\nUn combat est lancé, il se déroule en 4 rounds \n");

					etat.setJoueurDefense(etat.getTerrain().getOccupant(
							positionChoisie));

					// Initialise et lance le combat, le joueur perdant sera
					// determiné a la fin du round
					Combat combat = new Combat(etat);
					joueurPerdant = combat.lancer();

					if (joueurPerdant == null)
					    	
						fenetreText
								.setText(fenetreText.getText()
										+ " \nEgalité les deux joueurs sont téléportés !\n");
			
					else
						fenetreText.setText(fenetreText.getText() + "\n"
								+ joueurPerdant.getNom() + " a perdu !\n");
					

					combat.resoudreCombat(joueurPerdant, positionChoisie);

					// Si il y'a pas eu de combat on le positionne sur la case
					// voulue
				}
			} else {
				etat.getTerrain().PositionnerJoueurs(etat.getJoueurCourrant(),
						positionChoisie);
			}
		}

		if (etat.getJoueurs().size() == 1) {
			if (etat.getJoueurs().get(0) instanceof joueurReel)
			fenetreText.setText(fenetreText.getText() + "\nFélicitaion "
					+ etat.getJoueurs().get(0).getNom() + "! Vous avez gagné.\nLe monde vous doit une fière chandelle");
			else
				fenetreText.setText(fenetreText.getText() + "\n\n"
						+ etat.getJoueurs().get(0).getNom() + " a gagné! Il va conquérir le reste du monde.\nMais qu'allons nous faire?");
		} else {
			etat.passeLeTour();
			
			
			
			commencerJeu(etat, fenetreText);
		}
	}
}
