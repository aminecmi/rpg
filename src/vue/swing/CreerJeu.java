package vue.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import modeles.EtatJeu;
import modeles.Joueur;
import modeles.Position;
import modeles.Terrain;
import modeles.joueurOrdinateur;
import modeles.joueurReel;

/**
 * Cette classe permet de tester le jeu en mode console avec l'interface swing
 * 
 * @version 1 15 juin 2010
 * @author Amine, Hugo, Edouard
 */
public class CreerJeu {

	/**
	 * On lance le jeu en 3 étapes grâce a cette méthode, les ressources du jeu
	 * sont paramètrés dans la classe Ressource
	 */
	@SuppressWarnings("static-access")
	public CreerJeu(){
		CreerJeuFenetre CreerJeuFenetre = new CreerJeuFenetre(null, "Informations sur le jeu", true);
		CreerJeuFenetreInfo info = CreerJeuFenetre.showZDialog(); 
		
		/* 1. Création des objets du modèle */

		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		Terrain leTerrain = new Terrain(info.getTailleTerrain(), info.getTailleTerrain());

		/* 2. Création des composants visuels de départ */

		JTextPane fenetreText = new JTextPane();
			fenetreText.setEditable(false);
			fenetreText.setPreferredSize(new Dimension(100,100));
			JScrollPane scrollPane = new JScrollPane(fenetreText);
		scrollPane.setPreferredSize(new Dimension(800,100));
		JFrame fenetrePrincipale = new JFrame();
		fenetrePrincipale.setPreferredSize(new Dimension(600,800));
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.setLayout(new BorderLayout());
		VueTerrain composantVisuelTerrain = new VueTerrain(leTerrain);
		fenetrePrincipale.add(composantVisuelTerrain, BorderLayout.CENTER);
		fenetrePrincipale.setTitle("RPG");

		fenetrePrincipale.setJMenuBar(new MenuInterfaceGraphique(fenetreText).creeMenu(fenetrePrincipale));
		
		fenetrePrincipale.setPreferredSize(new Dimension(800, 600));
		fenetrePrincipale.pack();
		fenetrePrincipale.setExtendedState(fenetrePrincipale.MAXIMIZED_BOTH);
		fenetrePrincipale.add(scrollPane, BorderLayout.SOUTH);
		fenetrePrincipale.setVisible(true);

		
		/* 3. Initialisation : placement des joueurs */

		joueurs = creerLesPersos(info.getNombrePersonnageOrdinateur(), info.getNombrePersonnageReel(), leTerrain);

		EtatJeu etat = new EtatJeu(leTerrain, joueurs);

		associerVue(etat, fenetrePrincipale, fenetreText);
		VuePrincipale vue = new VuePrincipale();
		
		
		new MediateurVuePrincipale(composantVisuelTerrain, etat, vue, fenetreText);
		
		vue.commencerJeu (etat, fenetreText);
	}

	/**
	 * créer les personnages
	 * 
	 * @param nombre
	 *            de joueurs
	 * @param terrain
	 *            ou positionner les joueurs
	 * @return arrayList de joueurs
	 */
	public static ArrayList<Joueur> creerLesPersos(int nombrePersonnageOrdinateur, int nombrePersonnageJoueur, Terrain terrain) {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		Position position = null;

		// Tant qu'il ne reste pas des joueurs a créer
		for (int i = 0; i < nombrePersonnageJoueur; i++) {
			
	        
            UtilisateurDialog zd = new UtilisateurDialog(null, "Information sur le joueur", true);
            UtilisateurDialogInfo zInfo = zd.showZDialog(); 

			// Pour la nouvelle fênetre
        	do {
				position = terrain.getTuileVideAleatoire();
			} while (siPositionExiste(position, joueurs));

			Joueur joueur = new joueurReel(terrain, position);
			joueur.setNom(zInfo.getNom());

			// On modifie la position du joueur dans la classe joueur
			joueur.setPosition(position);
			
			
			if (zInfo.getTuxChoisis() == "Le premier Tux")
				joueur.setPion(1);
			else if (zInfo.getTuxChoisis() == "Le deuxieme Tux") 
				joueur.setPion(2);
			else if (zInfo.getTuxChoisis() == "Le troisieme Tux") 
				joueur.setPion(3);
			else  
				joueur.setPion(4);
			
			// Met a jour la nouvelle tuile avec un occupant
			terrain.getTuile(position).setOccupant(joueur);
			
			joueurs.add(joueur);
        	
		}

		// On créé les pionsOridnateurs
		for (int j = 1; j < nombrePersonnageOrdinateur+1; j++) {
			do {
				position = terrain.getTuileVideAleatoire();
			} while (siPositionExiste(position, joueurs));
			Joueur joueur = new joueurOrdinateur(terrain, position);
			joueur.setNom(String.valueOf("*" + j + "*"));
			
			// On modifie la position du joueur dans la classe joueur
			joueur.setPosition(position);

			// Met a jour la nouvelle tuile avec un occupant
			terrain.getTuile(position).setOccupant(joueur);
			
			joueur.setPion ( j);
			
			joueurs.add(joueur);
		}

		return joueurs;
	}

	/**
	 * verifie si le nom existe dans l'arrayList
	 * 
	 * @param nom
	 * @param joueurs
	 * @return boolean
	 */
	public static boolean siNomExiste(String nom, ArrayList<Joueur> joueurs) {
		boolean siExiste = false; // Retourne true ssi le nom est dans
		// l'ArayList
		int i = 0; // Variable de boucle

		while (!siExiste && i < joueurs.size()) {
			if (joueurs.get(i).getNom().contains(nom)) {
				siExiste = true;
			}
			i += 1;
		}
		return siExiste;
	}

	/**
	 * test si la position est déja prise par un autre jouer
	 * 
	 * @param position
	 * @return boolean
	 */

	public static boolean siPositionExiste(Position position,
			ArrayList<Joueur> joueurs) {
		boolean siPosExiste = false; // Retourne true ssi le nom est dans
		// l'ArayList
		int i = 0; // Variable de boucle

		while (!siPosExiste && i < joueurs.size()) {
			if (joueurs.get(i).getPosition().getCol() == position.getCol()
					&& joueurs.get(i).getPosition().getLigne() == position
							.getLigne()) {
				siPosExiste = true;
			}
			i += 1;
		}
		return siPosExiste;
	}

	/**
	 * permet de rajouter un ecouteur aux pions
	 * 
	 * @param etat
	 * @param fenetrePrincipale
	 */
	public static void associerVue(EtatJeu etat, JFrame fenetrePrincipale,
			JTextPane fenetreText) {
		for (int i = 0; i < etat.joueurs.size(); i++) {

			VueJoueur vueDuJoueur = new VueJoueur(etat.joueurs.get(i));
			vueDuJoueur.ajouterEcouteur(fenetrePrincipale, fenetreText);
		}
	}
	
}
