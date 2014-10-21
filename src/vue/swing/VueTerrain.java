package vue.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import modeles.Position;
import modeles.Terrain;

/**
 * Permet de représenter le terrain qui est un tableau de vue tuile
 * 
 *
 */
@SuppressWarnings("serial")
public class VueTerrain extends JPanel {

	/**
	 * Référence vers un modèle de terrain.
	 */
	private Terrain terrain;

	public Terrain getTerrain() {
		return this.terrain;
	}

	public VueTerrain(Terrain t) {
		this.terrain = t;
		this.setBackground(Color.BLACK);
		GridLayout gl = new GridLayout(this.terrain.getCol(), this.terrain.getLigne());
		this.setLayout(gl);
this.setPreferredSize(new Dimension(t.getLigne()*50,t.getCol()*50));
		for (int i = 0; i < this.terrain.getCol(); i++) 
			for (int j = 0; j < this.terrain.getLigne(); j++) 
				this.add(new VueTuile(this.terrain, new Position (i,j)));
			
		
	}
}
