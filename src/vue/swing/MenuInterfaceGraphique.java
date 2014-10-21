package vue.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Cette classe permet d'instancié le menu qui est tout en haut
 * 
 * @author Hugo, Amine, Edouard
 *
 */
public class MenuInterfaceGraphique {
	
	private JMenuBar menuBar = new JMenuBar();
    private JMenu MenuFichier = new JMenu("Fichier");
    private JMenu MenuAPropos = new JMenu("Aide");
    
    private JMenuItem MenuFichier_Ouvrir = new JMenuItem("Nouvelle partie");
    private JMenuItem MenuFichier_Fermer = new JMenuItem("Fermer");
    private JMenuItem MenuFichier_AfficherCombat = new JMenuItem("Afficher Combat");
    private JMenuItem MenuFichier_Regles = new JMenuItem("Regles");
    private JMenuItem MenuFichier_Auteur = new JMenuItem("Les auteurs");
    private JTextPane fenetre;
	public MenuInterfaceGraphique (JTextPane fenetreText) {
		this.fenetre = fenetreText;
	}
	
	/**
	 * Création du menu avec ses sous catégories
	 * 
	 * @return JMenuBar Le menu avec tous ses éléments 
	 */
	public JMenuBar creeMenu (final JFrame frame){
        
			MenuFichier_Ouvrir.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	frame.dispose();
	            	new CreerJeu();
	            }                               
	        });
	        this.MenuFichier.add(MenuFichier_Ouvrir);
	        
	        
		   	MenuFichier_AfficherCombat.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent arg0) {
		        	JFrame fenetreCombat= new JFrame();
		        	JTextPane fenetreText = new JTextPane();
					fenetreText.setEditable(false);
					fenetreCombat.setTitle("Resume du combat");
					fenetreText.setText(fenetre.getText());
					JScrollPane scrollPane = new JScrollPane(fenetreText);
					fenetreCombat.add(scrollPane);
					scrollPane.setPreferredSize(new Dimension(500,500));
					fenetreCombat.setVisible(true);
					fenetreCombat.pack();

		        } 
                
		   	});
		   	this.MenuFichier.add(MenuFichier_AfficherCombat);
		   	

        	MenuFichier_Fermer.addActionListener(new ActionListener(){
	                public void actionPerformed(ActionEvent arg0) {
	                        System.exit(0);
	                }                               
	        });
        
	   this.MenuFichier.add(MenuFichier_Fermer);
	   



	   
	   		MenuFichier_Regles.addActionListener(new ActionListener(){
	           public void actionPerformed(ActionEvent arg0) {
	                   // On doit afficher les régles ici
	        	   JFrame fenetreRegles = new JFrame();
	        	   fenetreRegles.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        	   fenetreRegles.setLayout(new BorderLayout ());	
	        	    
	        	   		fenetreRegles.setTitle("Regles du jeu");
	        	   		
	        	   		JTextArea fenetreText = new JTextArea(5, 30);
	        	   		
	        	   		fenetreText.setFont(new Font("Arial", Font.PLAIN, 18));
	        	   		fenetreText.setEditable(false);
	        	   		fenetreText.setText("Les joueurs choisissent un nom, et un personnage."+
	        	   						"A tours de role les joueurs se déplacerons sur le terrain.\n"+
										"Pour attaquer un joueur, il faut se déplacer sur sa case. "+
										"Un combat se déroule en 4 round "+
										"Au premier round le joueur qui se déplace attaque.\nCelui qui se defend attaque au deuxième et ainsi de suite."+
										"Si personne ne meurt pendant le combat: "+
										" \nLe gagnant sera celui qui perdra le moins de vie\n"+
										"Si l'attaquant gagne le combat, son adversaire sera téléporté et l'attaquant prendra sa place"+
										" sinon l'attaquant est téléporté.\n"+
										"Quand tous les joueurs se sont déplacés, c'est le tours des pions Ordinateurs qui se déplacent. \nIls attaquent automatiquement le joueur le plus proche. \n"+
										"Le jeu se termine quand il n'y a plus qu'un joueur sur le terrain: LE VAINQUEUR");
	        	   
	        	 
	        	   fenetreRegles.add (fenetreText, BorderLayout.CENTER);
	        	   fenetreRegles.pack();
	        	   fenetreRegles.setLocationRelativeTo(null);
	        	   fenetreRegles.setVisible(true);
	           }                               
		   });
	
	  this.MenuAPropos.add(MenuFichier_Regles);

	  		MenuFichier_Auteur.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent arg0) {
			          // Afficher les auteurs ici
			    	 JFrame fenetreRegles = new JFrame();
		        	 fenetreRegles.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		        	 fenetreRegles.setTitle("Createurs");
		        	 fenetreRegles.add(new JLabel ("Créé par Amine B., Hugo C., Edouard D."));
		        	 
		        	 fenetreRegles.pack();
		        	 fenetreRegles.setLocationRelativeTo(null);
		        	 fenetreRegles.setVisible(true);
			    }                               
			});

	this.MenuAPropos.add(MenuFichier_Auteur);
	
	
	
	   // /!\ Attention : l'odre est important les menus seront créé de gauche a droite 
       this.menuBar.add(MenuFichier);
       this.menuBar.add(MenuAPropos);
       
       return menuBar;
	}
}
