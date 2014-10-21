package vue.swing;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modeles.Joueur;
import modeles.Position;
import modeles.Terrain;
import modeles.Tuile;

/**
 * Permet de représenter la tuile sur le terrain
 * 
 *
 */
@SuppressWarnings("serial")
public class VueTuile extends JPanel {

	public static final Integer LARGEUR = 50;
	public static final Integer HAUTEUR = 50;

	/**
	 * Référence vers un modèle de tuile.
	 */
	private Tuile tuile;

	public Tuile getTuile() {
		return this.tuile;
	}
	
	/**
	 * Position de la tuile.
	 */
	private Position position;

	public Position getPosition() {
		return this.position;
	}

	/**
	 * Référence vers l'objet VueJoueur pouvant être présent sur la tuile.
	 */
	private VueJoueur pion;

	/**
	 * Constructeur.
	 */
	public VueTuile(Terrain terrain, Position position) {
		this.tuile = terrain.getTuile(position);
		this.position = position;

		this.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		this.setLayout(new GridLayout(1, 1));

		// La VueTuile courante se met à l'écoute des changements d'occupant de
		// son modèle de Tuile.
		 terrain.getTuile(position).ajouterEcouteurOccupant(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getNewValue() == null) {
					remove(pion);
					pion = null;
					repaint();
				}
					else {
						

						repaint();
					pion = new VueJoueur((Joueur) evt.getNewValue());
					add(pion);
					pion.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
					revalidate();
				}
			}

		});
	}
	
	/**
	 * Méthode se chargant automatiquement permettant de créé l'image de la tuile
	 * 
	 */
    public void paintComponent(Graphics g){
        try {
                Image img = ImageIO.read(new File("images/tuile.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
                e.printStackTrace();
        }
        
    } 
}
