package vue.swing;
/**
 * Classe permettant la transition entre la fenetre (UtilisateurDialog) et la classe qui en a besoin
 * 
 *
 */
public class UtilisateurDialogInfo {



		private String nom;
        private String tuxChoisis;
        
        public UtilisateurDialogInfo(){}
        public UtilisateurDialogInfo(String nom, String tuxChoisis){
                this.nom = nom;
                this.tuxChoisis = tuxChoisis;
        }
        
        //------------------------------------
        
        public String getTuxChoisis() {
    		return tuxChoisis;
    	}
    	public void setTuxChoisis(String tuxChoisis) {
    		this.tuxChoisis = tuxChoisis;
    	}
    	
        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String toString(){
                String str;
                        str = "Nom : " + this.nom + "\n "+tuxChoisis;
                return str;
        }
}