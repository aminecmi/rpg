package vue.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;


import modeles.EtatJeu;
import modeles.Joueur;
import modeles.joueurReel;
import modeles.pionsReg;


public class MediateurVuePrincipale {

	/**
	 * Référence vers notre joueur courant.
	 */
	EtatJeu etatJeu;

	/**
	 * Composant visuel représentant le terrain.
	 */
	private VueTerrain composantVisuelTerrain;

	public VueTerrain getComposantTerrain() {
		return this.composantVisuelTerrain;
	}

	/**
	 * Constructeur.
	 * @param fenetreText 
	 */
	public MediateurVuePrincipale(VueTerrain composantTerrain,
			final EtatJeu etatJeu, final VuePrincipale vue, final JTextPane fenetreText) {

		this.composantVisuelTerrain = composantTerrain;
		this.etatJeu = etatJeu;

		// On place un écouteur de clic sur chacune des tuiles du terrain.
		for (int i = 0; i < this.composantVisuelTerrain.getComponentCount(); i++) {
			final VueTuile composantVisuelTuile = (VueTuile) this.composantVisuelTerrain
					.getComponent(i);
			composantVisuelTuile.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("static-access")
				public void mouseClicked(MouseEvent e) {

					if (etatJeu.getJoueurCourrant() instanceof joueurReel) {
						if (etatJeu.getJoueurs().size() != 1){
							if(( (joueurReel) etatJeu.getJoueurCourrant()).ecartCorrect(composantVisuelTuile.getPosition())){
								JOptionPane erreur = new JOptionPane();
								erreur.showMessageDialog(null, "Impossible de se déplacer ici, vous n'avez que "+etatJeu.getJoueurCourrant().getDeplacement()+ " de déplacement", "Erreur", JOptionPane.ERROR_MESSAGE);
							}else{
															
								vue.jouerUnTour(etatJeu, fenetreText, composantVisuelTuile.getPosition());
							}
						}
					}
				}
				
				public void mouseEntered(MouseEvent e) {
					if (etatJeu.getTerrain().getOccupant(composantVisuelTuile.getPosition()) != null){
						Joueur joueurCase = etatJeu.getTerrain().getOccupant(composantVisuelTuile.getPosition());
						// -- Version sur deux lignes avec les problémes d'affichages
						//composantVisuelTuile.setToolTipText("<html>Nom Hp/HpMax Attaque Def heritage Nombre de vie Niveau Arme <br /> " +
															//joueurCourant.getNom()+" "+ joueurCourant.getPv()+"/"+joueurCourant.getPv()+"     "+ joueurCourant.getAtt()+"      "+ joueurCourant.getdef()+"    "+joueurCourant.getHeritage()+"        "+joueurCourant.getNbVies()+"          "+joueurCourant.getNiveau()+"     "+joueurCourant.getArme().getNom()+" </html>");
						if(joueurCase instanceof pionsReg){
							composantVisuelTuile.setToolTipText("<html><b>Points vie</b> : "+joueurCase.getHeritage());
						}
						else
						composantVisuelTuile.setToolTipText("<html><b>Nom</b> : "+joueurCase.getNom()+" <b>Hp</b> : "+ joueurCase.getPv()+" <b>Attaque</b> : "+ joueurCase.getAtt()+" <b>Def</b> : "+ joueurCase.getdef()+" <b>Heritage</b> : "+joueurCase.getHeritage()+" <br> <b>Nombre de vie</b> : "+joueurCase.getNbVies()+"  <b>Niveau</b> : "+joueurCase.getNbVies()+" <b>Arme</b> : "+joueurCase.getArme().getNom());
					}
				} 
			});
		}
	}
}