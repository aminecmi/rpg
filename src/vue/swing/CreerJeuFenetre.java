package vue.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Permet de lancer la fenetre qui permet de choisir la taille du terrain et le nombre de 
 * pions utilisateur et le nombre de pions machine
 * 
 * @author courtecuisse
 *
 */
@SuppressWarnings("serial")
public class CreerJeuFenetre extends JDialog {

        private CreerJeuFenetreInfo infoCreerJeu = new CreerJeuFenetreInfo(); // classe permettant la liaison entre creerJeu et la fenetre
        @SuppressWarnings("unused")
		private boolean sendData; 
        private JLabel labelTerrain;

        protected JFrame fenetre;
        /**
         * Constructeur
         * @param parent
         * @param title
         * @param modal
         */
        public CreerJeuFenetre(JFrame parent, String title, boolean modal){
                super(parent, title, modal); // Fentre modale oblige
                this.setSize(550, 270);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                this.initComponent();
                this.fenetre=parent;
        }
        
        /**
         * Méthode appelée pour utiliser la boîte 
         * @return zInfo
         */
        public CreerJeuFenetreInfo showZDialog(){
                this.sendData = false;
                this.setVisible(true);          
                return this.infoCreerJeu;              
        }
        
        /**
         * Initialise le contenu de la boîte
         */
        private void initComponent(){

                
                //Le terrain
                JPanel panTerrain = new JPanel();
                panTerrain.setBackground(Color.white);
                
                panTerrain.setLayout(new BorderLayout());
                
                	final JComboBox tailleTerrain = new JComboBox();
                	
                	labelTerrain = new JLabel("La taille du terrain :");
                	
    	       		for (int i=3; i<=30; i++){
    	       		tailleTerrain.addItem(i);
    	       		}
                	
                panTerrain.add(labelTerrain,BorderLayout.NORTH);
                
                panTerrain.add(tailleTerrain, BorderLayout.SOUTH);
                
                JPanel panPlateujeu = new JPanel();
                panPlateujeu.setBackground(Color.white);
                panPlateujeu.add (new JLabel(new ImageIcon("images/plateauJeu.png")));
                
                panTerrain.add(panPlateujeu, BorderLayout.CENTER);
                
    	       // Liste déroulante pour le nombre de personnage
    	       JPanel conteneurNombrePersonnage = new JPanel();
    	       conteneurNombrePersonnage.setBackground(Color.white);

    	       
		           	final JComboBox nombrePersonnageReel = new JComboBox();
		           	final JComboBox nombrePersonnageOrdinateur = new JComboBox();
		        	
		        
		       		for (int i=1; i<=4; i++){
		       			nombrePersonnageReel.addItem(i);
		       		}
		       		
		       		for (int i=1; i<=4; i++){
		       			nombrePersonnageOrdinateur.addItem(i);
		       		}
                	
	     	    conteneurNombrePersonnage.add(new JLabel("Le nombre de joueur r�els :"));
	     	    conteneurNombrePersonnage.add(nombrePersonnageReel);
    	       	
     	        // Liste déroulante pour le nombre de personnage
     	        JPanel conteneurNombrePersonnageOrdi = new JPanel();
     	        conteneurNombrePersonnageOrdi.setBackground(Color.white);

     	        conteneurNombrePersonnageOrdi.add(new JLabel("Le nombre de joueur Ordinateur :"));
     	        conteneurNombrePersonnageOrdi.add(nombrePersonnageOrdinateur);
    	       	
     	        //Icone
                JPanel conteneurIcon = new JPanel();
                conteneurIcon.setBackground(Color.white);
                conteneurIcon.setLayout(new GridLayout(1,3));
                conteneurIcon.setPreferredSize(new Dimension(320, 120));

    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier1.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier2.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier3.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier4.gif")));
    	        
    	        JPanel conteneurIconEtListeDeroulante= new JPanel ();

    	        conteneurIconEtListeDeroulante.add(conteneurNombrePersonnage, BorderLayout.NORTH);
    	        conteneurIconEtListeDeroulante.add(conteneurIcon, BorderLayout.CENTER);
    	        conteneurIconEtListeDeroulante.add(conteneurNombrePersonnageOrdi, BorderLayout.SOUTH);
    	        conteneurIconEtListeDeroulante.setBackground(Color.white);
    	        
                JPanel control = new JPanel();
                JButton okBouton = new JButton("OK");
                
                okBouton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {                         
                        		infoCreerJeu = new CreerJeuFenetreInfo( (int)(Integer) nombrePersonnageOrdinateur.getSelectedItem(), (Integer) nombrePersonnageReel.getSelectedItem(), (Integer) tailleTerrain.getSelectedItem());
                                setVisible(false);
                        }
                   
                });
                
                JButton cancelBouton = new JButton("Annuler");
                cancelBouton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                    			infoCreerJeu = new CreerJeuFenetreInfo( 1, 1, 10);
                                setVisible(false);
                        }                       
                });
                
                control.add(okBouton);
                control.add(cancelBouton);
                
                this.getRootPane().setDefaultButton(okBouton);
                this.getContentPane().add(panTerrain, BorderLayout.WEST);
                this.getContentPane().add(conteneurIconEtListeDeroulante, BorderLayout.CENTER);
                this.getContentPane().add(control, BorderLayout.SOUTH);
        }  

        
}

