package vue.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import modeles.Armes;
import modeles.Joueur;
import modeles.joueurOrdinateur;
import modeles.joueurReel;
import modeles.pionsReg;

/**
 * Permet de représenter un joueur sur le terrain
 * 
 *
 */
@SuppressWarnings("serial")
public class VueJoueur extends JPanel {

	/**
	 * Référence vers un modèle de joueur.
	 */
	private Joueur joueur;

	
	/**
	 * Constructeur.
	 */
	public VueJoueur(Joueur j) {
		this.joueur = j;
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(49,49));
	}
	

	/**
	 * Méthode se chargant automatiquement permettant de créé l'image du guerrier
	 * 
	 */
    public void paintComponent(Graphics g){
        try {
        	Image img;
        	if (this.joueur instanceof joueurReel) 
        		img = ImageIO.read(new File("images/Guerrier"+joueur.getPion()+".gif"));
        	else if (this.joueur instanceof joueurOrdinateur)
        		img = ImageIO.read(new File("images/alien"+joueur.getPion()+".gif"));
        	else if  (this.joueur instanceof pionsReg)
        		img = ImageIO.read(new File("images/potion"+((pionsReg) this.joueur).getType()+".gif"));
        	else
        		img = null;
        	
           g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

        } catch (IOException e) {
                e.printStackTrace();
        }
        
        
    } 
    
	/**
	 * ajouter des écouteurs pour les caractéristiques du joueurs
	 */
	public void ajouterEcouteur(JFrame fenetrePrincipale, JTextPane fenetreText) {
		ecouteurPointDeVie(fenetrePrincipale, fenetreText);
		ecouteurNbVies(fenetrePrincipale, fenetreText);
		ecouteurNiveau(fenetrePrincipale, fenetreText);
		ecouteurPa(fenetrePrincipale, fenetreText);
		ecouteurXp(fenetrePrincipale, fenetreText);
		ecouteurArme(fenetrePrincipale, fenetreText);
		
	}

    // Ecouteurs

	/**
	 * écouteur pour les PV
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurPointDeVie(final JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurPointDeVie(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null) {
					int OldValue = ((Integer) (evt.getOldValue()));
					int NewValue = ((Integer) (evt.getNewValue()));
					
					if (OldValue - NewValue > 0)
						fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom()
								+ " recoit " + (OldValue - NewValue)
								+ " de degat");
					
					fenetreText.setText(fenetreText.getText()+"\nIl reste a "
							+ ((Joueur) evt.getSource()).getNom() + " "
							+ evt.getNewValue() + " PV\n");

				}
			}

		});

	}

	/**
	 * écouteur XP
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurXp(final JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurXp(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null) {
					if((Integer)evt.getNewValue() - (Integer)evt.getOldValue()>0)
						fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom()+" gagne " + ((Integer)evt.getNewValue() - (Integer)evt.getOldValue()) + " XP");

				}
			}

		});

	}

	/**
	 * écouteur Points attaque
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurPa(final JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurPa(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null)
					
					fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom()
							+ " a maintenant " + evt.getNewValue()
							+ " d'attaque\n");
				
			}

		});

	}

	/**
	 * écouteur nbVies
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurNbVies(final JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurNbreVies(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null) {
					int NewValue = ((Integer) (evt.getNewValue()));
				

					if (NewValue >= 0)
						fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom()
								+ " a maintenant " + evt.getNewValue()
								+ " vie(s)\n");
					else
						fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom()
						+ " est mort\n");

				}
			}

		});

	}

	/**
	 * écouteur Niveau
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurNiveau(final JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurNiveau(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null) {

					fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom() 
							+ " a atteint le niveau " + evt.getNewValue()); 

				}
			}

		});

	}
	
	/**
	 * écouteur Arme
	 * @param fenetreText 
	 * @param fenetrePrincipale 
	 * @param fenetreText 
	 * 
	 * @param etat
	 */
	public void ecouteurArme(JFrame fenetrePrincipale, final JTextPane fenetreText) {

		joueur.ajouterEcouteurArme(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getNewValue() != null) {
					
					fenetreText.setText(fenetreText.getText()+"\n"+((Joueur) evt.getSource()).getNom() 
							+ " a maintenant une " + (((Armes) evt.getNewValue()).getNom()+"\n") );
					

				}
			}

		});

	}

}
