package vue.swing;
/**
 * Classe permettant la liaison entre la classe creerJeu et la fenetre
 * 
 * @author courtecuisse
 *
 */
public class CreerJeuFenetreInfo {

	private int tailleTerrain;
	private int nombrePersonnageReel;
	private int nombrePersonnageOrdinateur;
	
	public CreerJeuFenetreInfo(){
		
	}
	
	public CreerJeuFenetreInfo(int nombrePersonnageOrdinateur, int nombrePersonnageReel, int tailleTerrain) {
		this.nombrePersonnageOrdinateur = nombrePersonnageOrdinateur;
		this.nombrePersonnageReel = nombrePersonnageReel;
		this.tailleTerrain = tailleTerrain;
	}

	public int getTailleTerrain() {
		return tailleTerrain;
	}

	public void setTailleTerrain(int tailleTerrain) {
		this.tailleTerrain = tailleTerrain;
	}

	public int getNombrePersonnageReel() {
		return nombrePersonnageReel;
	}

	public void setNombrePersonnageReel(int nombrePersonnage) {
		this.nombrePersonnageReel = nombrePersonnage;
	}

	
	public int getNombrePersonnageOrdinateur() {
		return nombrePersonnageOrdinateur;
	}

	public void setNombrePersonnageOrdinateur(int nombrePersonnageOrdinateur) {
		this.nombrePersonnageOrdinateur = nombrePersonnageOrdinateur;
	}
}
