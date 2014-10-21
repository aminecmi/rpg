package modeles;
/**
 * Classe permettant de réprésenter les 5 types d'armes disponibles dans le jeu
 * 
 * @author courtecuisse
 *
 */
public class Armes {
	protected int attaque; // Attaque bonus que procure cette arme
	protected String nom; // Le nom de l'arme
	protected int niveauMinimum; // Le niveau minimum pour l'utiliser
	
	/**
	 * Constructeur permettant de donner l'arme par defaut une dague avec un attaque.  
	 * 
	 */
	Armes (){
		this.attaque = 1;
		this.nom = "Dague";
		this.niveauMinimum = 1;
	}
	
	
	public void armeAleatoire (){
		
		switch ((int) (Math.random() * 5) + 1)
		{
		case 1: 			
			this.attaque = 2;
			this.nom = "Epee";
			this.niveauMinimum = 1; 
		break;
		case 2: 			
			this.attaque = 3;
			this.nom = "Arbalette";
			this.niveauMinimum = 1; 
		break;
		case 3: 			
			this.attaque = 4;
			this.nom = "Hache";
			this.niveauMinimum = 2;
		break;
		case 4:
			this.attaque = 5;
			this.nom = "Lance";
			this.niveauMinimum = 2;
		break;
		case 5:
			this.attaque = 6;
			this.nom = "Hallebarde";
			this.niveauMinimum = 3;
		break;
		default:
			this.attaque = 1;
			this.nom = "Dague";
			this.niveauMinimum = 1;
		}

		
	}
	
	public int getAttaque() {
		return attaque;
	}

	public String getNom() {
		return nom;
	}

	public int getNiveauMinimum() {
		return niveauMinimum;
	}
	
	
}
