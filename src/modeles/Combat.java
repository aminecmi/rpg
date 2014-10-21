package modeles;


/**
 * Classe Représentant un combat entre deux Joueurs. Un combat se déroule entre
 * 2 joueurs, en 4 rounds, à l'issue desquels il y a un vainqueur et un perdant.
 * 
 */

public class Combat {

	private EtatJeu etat; // Répresente l'état du jeu

	public Combat(EtatJeu etat) {
		this.etat = etat;
	}

	/**
	 * Effectue un round, le joueur 1 est l'attaquant et le joueur 2 est le
	 * défenseur
	 * 
	 * @param attaquant
	 *            L'attaquant du round
	 * @param defenseur
	 *            Le défenseur
	 * @return int -1 Si il y'a eu un mort et le nombre de dégats dans le cas
	 *         contraire
	 */

	public int round(Joueur attaquant, Joueur defenseur) {
		int j1Attaque = attaquant.attaque();
		int j2Defense = defenseur.seDefend(j1Attaque);
		int j2PvRestant = defenseur.getPv() - j2Defense;

		// Si le combat n'est pas terminé, on enleve les points de vie a j2
		// sinon on met ses points de vie a zero et on envoie la solution du
		// problème.
		if (!combatFini(j2PvRestant)) {

			defenseur.setPv(j2PvRestant);

			return j2Defense;
		} else {

			defenseur.setPv(0);

			return -1;
		}
	}

	/**
	 * Cette méthode permet de faire tourner les 4 rounds, deux par deux. Le per
	 * 
	 * @return Joueur Le joueur gagnant le round
	 */
	public Joueur lancer() {

		// Les variables permettant de gérer le round
		int solutionRounds = 0; // La solution du round retourne -1 si il y'a eu
		// un mort ou le dégats du défenseur
		int nbreRound = 0; // Le nombre de rounds

		int degatsTotalsJ1 = 0, degatsTotalsJ2 = 0; // Contient le total de
		// dégats de joueur 1 et
		// joueur 2

		Joueur joueurPerdant = null; // Le joueur qui n'a pas gagné le match

		// Pour économiser de la place et en clarté on fait deux tours par
		// boucle
		while (nbreRound <= Ressources.nbRounds && joueurPerdant == null) {
			// On joue le premier tour joueur 1 est attaquant et joueur 2
			// défenseur

			solutionRounds = round(etat.getJoueurCourrant(), etat
					.getJoueurDefense());

			// Si joueur2 n'est pas mort dans le round 1 ou 3
			if (solutionRounds != -1) {
				degatsTotalsJ1 += solutionRounds;

				// Deuxième tour, joueur 2 est attaquant et joueur 1 défenseur
				solutionRounds = round(etat.getJoueurDefense(), etat
						.getJoueurCourrant());

				// Si joueur1 n'est pas mort dans le round 2 ou 4
				if (solutionRounds != -1)
					degatsTotalsJ2 += solutionRounds;
				else
					joueurPerdant = etat.getJoueurCourrant();

			} else
				joueurPerdant = etat.getJoueurDefense();

			nbreRound = nbreRound + 2;

		}

		// Résolution du combat, si il n'y a aucun mort
		if (joueurPerdant == null) {
			joueurPerdant = siPasDeGagnant(etat.getJoueurCourrant(), etat
					.getJoueurDefense(), joueurPerdant, degatsTotalsJ2,
					degatsTotalsJ1);
		}

		return joueurPerdant;
	}

	/**
	 * verifie si le combat est fini
	 * 
	 * @param j2PvRestant
	 * @return joueurPerdant
	 */
	public boolean combatFini(int j2PvRestant) {
		return j2PvRestant <= 0;
	}

	/**
	 * Designe le perdant avec les dégats des joueurs
	 * 
	 * @param joueurPerdant
	 *            Le joueur est normallement a null
	 * @param degatsTotalsJ2
	 *            Les dégats totals du joueur 2
	 * @param degatsTotalsJ1
	 *            Les dégats totals du joueur 1
	 * @return joueurPerdant
	 */
	public Joueur siPasDeGagnant(Joueur joueur1, Joueur joueur2,
			Joueur joueurPerdant, int degatsTotalsJ2, int degatsTotalsJ1) {

		// Résolution du combat, si il n'y a aucun mort
		if (joueurPerdant == null) {

			// Elle se fait par le nombre de dégats infligé par le joueur
			if (degatsTotalsJ2 == degatsTotalsJ1)
				joueurPerdant = null;
			else if (degatsTotalsJ2 > degatsTotalsJ1)
				joueurPerdant = joueur1;
			else
				joueurPerdant = joueur2;

		}

		return joueurPerdant;
	}

	/**
	 * Gère le problême égalité parfaite entre les deux joueurs, c'est a dire
	 * qu'il ont perdu le même nombre de dégats. Les deux joueurs sont
	 * téléportés sur une case aléatoire un message est aussi affiché dans
	 * l'interface
	 * 
	 * @param positionChoisie
	 *            Position La position du joueur qui était attqué
	 * @param joueur
	 *            Joueur L'attaquant
	 * 
	 */
	public void egalite(Position positionChoisie, Joueur joueur) {
		etat.getTerrain().getOccupant(positionChoisie).seTeleporte();
		joueur.seTeleporte();

	}

	/**
	 * Cas ou l'occupant de la case perd le combat, l'occupant est alors déplacé
	 * aléatoirement sur le terrain et le gagnant est positionné sur la case
	 * voulu
	 * 
	 * @param joueurPerdant
	 *            Joueur Le joueur ayant perdu le round
	 * @param joueur
	 *            Joueur est l'attaquant
	 * @param positionChoisie
	 *            Position La position choisie sur le terrain
	 * @return joueur Joueur le gagnant
	 */
	public Joueur occupantPerd(Joueur joueurPerdant, Joueur joueur,
			Position positionChoisie) {
		joueurPerdant.seTeleporte();
		etat.getTerrain().PositionnerJoueurs(joueur, positionChoisie);
		return joueur;
	}

	/**
	 * Cas ou l'attaquant de la case perd le combat, l'occupant garde sa case et
	 * l'atatquant est alors déplacé aléatoirement sur le terrain.
	 * 
	 * @param joueurPerdant
	 *            Joueur Le joueur ayant perdu le round
	 * @param joueur
	 *            Joueur est l'attaquant
	 * @param positionChoisie
	 *            Position La position choisie sur le terrain
	 * @return joueur Joueur le gagnant
	 */
	public Joueur attaquantPerd(Joueur joueurPerdant, Joueur joueur,
			Position positionChoisie) {
		joueur = etat.getTerrain().getOccupant(positionChoisie);
		joueurPerdant.seTeleporte();
		return joueur;
	}

	/**
	 * Resout un combat si un des joueurs est mort pendant le jeu
	 * 
	 * @param joueurMort
	 *            Le joueurMort est celui qui est mort
	 */
	public void CombatSiMort(Joueur joueurMort) {

		etat.supprimerArrayListJoueur(joueurMort);
		etat.getTerrain().getTuile(joueurMort.getPosition()).setOccupant(null);
		joueurMort.setPosition(null);
	}

	/**
	 * Resout un combat si le joueur 2 a encore des vies.
	 * 
	 * @param joueurGagnant
	 *            Le joueurGagnant est celui qui a gagné
	 * @param joueurPerdant
	 *            Le joueurPerdant est celui qui a perdu
	 */
	public void CombatAvecMortEtRenaissance(Joueur joueurGagnant,
			Joueur joueurPerdant) {
		// Combat avec mort et renaissance
		joueurGagnant
				.setPv(joueurPerdant.getHeritage() + joueurGagnant.getPv());
		joueurPerdant.setPv(Ressources.pv);
	}

	/**
	 * Resout combat en connaisant le perdant
	 * 
	 * @param joueurPerdant
	 *            Le joueur perdant le round
	 * 
	 * @param positionChoisie
	 *            La position chisie par le joueur l'indice du joueur courant
	 *            dans l'arrayList
	 */
	public void resoudreCombat(Joueur joueurPerdant, Position positionChoisie) {
		Joueur joueurGagnant = null;

		// Si il y'a pas eu d'égalité, c'est à dire que les joueurs n'ont pas eu
		// le même nombre de dégats
		if (joueurPerdant == null)
			egalite(positionChoisie, etat.getJoueurCourrant());
		else {

			// Determination du gagnant car pour l'instant on ne connait que le
			// perdant
			joueurGagnant = determinerJoueurGagnant(joueurPerdant,
					positionChoisie);

			// Résolution du combat, si il a eu des morts
			resolutionCombat(joueurPerdant, joueurGagnant, positionChoisie);

		}
	}

	/**
	 * Determine le joueur gagnant du round
	 * 
	 * @param joueurPerdant
	 * @param positionChoisie
	 * @return joueurGagnant
	 */
	public Joueur determinerJoueurGagnant(Joueur joueurPerdant,
			Position positionChoisie) {

		if (joueurPerdant.getNom() == etat.getJoueurCourrant().getNom())
			// Si il est gagnant on le positionne sur la case du perdant et
			// on positionne le perdant sur une case vide
			return etat.getJoueurDefense();
		else
			// Si il n'est pas le gagnant, on le possitione aléatoirement
			// sur le terrain et l'occupant de la case reste le même
			return etat.getJoueurCourrant();

	}

	/**
	 * reolution du combat
	 * 
	 * @param joueurPerdant
	 * @param joueurGagnant
	 */
	public void resolutionCombat(Joueur joueurPerdant, Joueur joueurGagnant,
			Position positionChoisie) {

		// Teste si il doit changer de niveau

		// On met l'expérience en fonction du niveau

			joueurGagnant.setXp(joueurGagnant.getXp() + 1 * 10);

		if (joueurPerdant.getPv() <= 0) {
			// On enlève une vie au joueur et on rajoute de l'expérience au
			// gagnant eu perdant
			joueurPerdant.setNbVies(joueurPerdant.getNbVies() - 1);

			// Si il a encore des vies on le fait renaitre sur une case vide
			// aléatoire du tableau
			if (joueurPerdant.getNbVies() >= 0){
				CombatAvecMortEtRenaissance(joueurGagnant, joueurPerdant);

			// Si il n'a plus de vie on l'élimine du terrain et de
			// l'ArrayList de joueur
			}else{
				CombatSiMort(joueurPerdant);
				
				// On met une arme au joueur gagnant
				Armes arme= new Armes();
				arme.armeAleatoire();
				if (arme.getAttaque()>joueurGagnant.getArme().getAttaque() && joueurGagnant.getNiveau()>= arme.getNiveauMinimum())
					joueurGagnant.setArme(arme);
				
			}
		}
		if (joueurPerdant.getNbVies() >= 0) {
			if (joueurPerdant.getNom() == etat.getTerrain().getOccupant(
					positionChoisie).getNom())
				occupantPerd(joueurPerdant, etat.getJoueurCourrant(),
						positionChoisie);
			else
				attaquantPerd(etat.getJoueurCourrant(), etat.getTerrain()
						.getTuile(positionChoisie).getOccupant(),
						positionChoisie);
		} else {
			if (joueurGagnant.getPosition() != positionChoisie) {

				etat.getTerrain().PositionnerJoueurs(joueurGagnant,
						positionChoisie);
			}

		}

		siNiveauSuperrieur(joueurGagnant);
	}

	/**
	 * On attribut des P att par rapport au niveau du joueur
	 * 
	 * @param joueur
	 */
	public void siNiveauSuperrieur(Joueur joueur) {
		
		if (joueur.getMaxXp() <= joueur.getXp()) {
			joueur.setXp(0);
			joueur.setNiveau(joueur.getNiveau() + 1);
			joueur.setAtt(joueur.getAtt() + 2);
			joueur.setMaxXp(joueur.getMaxXp()+10);
		}
		
	}
}
