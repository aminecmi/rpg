package modeles;

import java.util.ArrayList;

import modeles.Joueur;
import modeles.Terrain;
import modeles.EtatJeu;
/**
 * représente l'état actuel du jeu.
 * @author abouabdallaoui
 *
 */
public class EtatJeu {
		/**
		 * le terrain du jeu
		 */
		private Terrain terrain;

		/**
		 * Les joueurs participant au jeu
		 */
		public ArrayList<Joueur> joueurs;
		/**
		 * le joueur courrant
		 */
		private Joueur joueurCourrant;
		/**
		 * le joueur qui se défend
		 */
		private Joueur joueurDefense;
		
		public EtatJeu(Terrain terrain,ArrayList<Joueur> joueurs){
			this.terrain= terrain;
			this.joueurs=joueurs;
			this.joueurCourrant=this.joueurs.get(0);
			this.joueurDefense=null;
		}
		
		public Joueur getJoueurCourrant (){
			return  joueurCourrant;
		}
		
		public void setJoueurCourrant (Joueur joueurCourrant){
			this.joueurCourrant = joueurCourrant;
		}
		
		public Terrain getTerrain (){
			return  this.terrain;
		}
		
		public void setTerrain (Terrain terrain){
			 this.terrain = terrain;
		}
		
		public Joueur getJoueurDefense (){
			return  this.joueurDefense;
		}
		
		public void setJoueurDefense (Joueur joueur){
			 this.joueurDefense = joueur;
		}
		
		public ArrayList<Joueur> getJoueurs (){
			return this.joueurs;
		}
		
		public void setJoueurs (Joueur joueurCourrant){
			 this.joueurs.add(joueurCourrant);
		}
		
		public void setArrayListJoueurs (ArrayList<Joueur> joueurs){
			 this.joueurs = joueurs;
		}
		/**
		 * supprimer le joueur de l'arrayList
		 * @param joueur
		 */
		public void supprimerArrayListJoueur(Joueur joueur) {

			for (int i = 0; i < this.joueurs.size(); i++) {
				if (joueurs.get(i) == joueur) {
					joueurs.remove(i);
				}
			}
		}
		
		/**
		 * Méthode testant la fin du jeu
		 * 
		 * @return true ssi le jeu est terminé
		 */
		public boolean jeuFini() {
			return joueurs.size() == 1;
		}
		/**
		 * passer au premier joueur de la liste
		 */
		public void remiseAZero(){

				joueurCourrant=joueurs.get(0);
			
		}
		/**
		 * permet de passer au joueur suivant dans l'arrayList
		 */
		public void passeLeTour() {
			int index=this.joueurs.indexOf(joueurCourrant);
			index++;
			if(index >= joueurs.size()){
				setJoueurCourrant(joueurs.get(0));
			}
			else
			setJoueurCourrant(joueurs.get(index));
		}
}