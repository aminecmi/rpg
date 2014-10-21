package modeles;

/**
 * Les pions regenérants sont des pions permettant de redonner un peu de vie au joueur 
 * qui tombe sur sa case. Appellé aussi potion.
 * 
 *
 */

public class pionsReg extends Joueur{
	
	int type; // Le type correspond a la taille de la potion une potion de type 1 
			  // donneras moins de point de vie qu'une potion de type 2
	
	/**
	 * Constructeur
	 * 
	 * @param terrain leTerrain de jeu
	 */
	public pionsReg(Terrain terrain){
		
		super(terrain, terrain.getTuileVideAleatoire());

		super.pv = 1;
		super.xp = 0;
		super.att = 1;
		super.deplacement = 0;
		super.nbVies = 0;
		super.def = 0;
		super.niveau = 1;
		super.maxXP = 1;
		terrain.getTuile(super.getPosition()).setOccupant(this);
		this.type = (int) (Math.random() * 3) + 1;
		
		if (this.type == 1)
			super.heritage = 5;
		else if (this.type == 2)
			super.heritage = 10;
		else
			super.heritage = 20;
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
