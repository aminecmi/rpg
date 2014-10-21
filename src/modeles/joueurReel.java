package modeles;

/**
 * Classe représentant un joueur réel
 */
public class joueurReel extends Joueur {
	/**
	 * place un joueur réel sur le terrain a une position donnée
	 * 
	 * @param terrain
	 * @param position
	 */
	public joueurReel(Terrain terrain, Position position) {
		super(terrain, position);
	}

	/**
	 * verifie si le joueur réel peut aller sur la position
	 * 
	 * @param position
	 * @return vrai si le joueur peut se déplacer sur la case
	 */
	public boolean ecartCorrect(Position position) {
		return Math.abs((this.getPosition().getCol() - position.getCol()))
				+ Math
						.abs((this.getPosition().getLigne() - position
								.getLigne())) > super.deplacement;
	}
}
