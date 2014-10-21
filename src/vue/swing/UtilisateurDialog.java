package vue.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe permettant au joueur de choisir le nom de son pion ainsi que l'image qui le réprésente 
 * 
 *
 */
@SuppressWarnings("serial")
public class UtilisateurDialog extends JDialog {

        private UtilisateurDialogInfo zInfo = new UtilisateurDialogInfo();
        @SuppressWarnings("unused")
		private boolean sendData;
        private JLabel nomLabel;
        private JTextField nom;
        protected JFrame fenetre;
        /**
         * Constructeur
         * @param parent
         * @param title
         * @param modal
         */
        public UtilisateurDialog(JFrame parent, String title, boolean modal){
                super(parent, title, modal);
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
        public UtilisateurDialogInfo showZDialog(){
                this.sendData = false;
                this.setVisible(true);          
                return this.zInfo;              
        }
        
        /**
         * Initialise le contenu de la boîte
         */
        private void initComponent(){

                
                //Le nom
                JPanel panNom = new JPanel();
                panNom.setBackground(Color.white);
                panNom.setPreferredSize(new Dimension(220, 60));
                nom = new JTextField();
                nom.setPreferredSize(new Dimension(100, 25));
                panNom.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));
                nomLabel = new JLabel("Saisir un nom :");
                panNom.add(nomLabel);
                panNom.add(nom);
                
                
                //Icone
                JPanel conteneurIcon = new JPanel();
                conteneurIcon.setBackground(Color.white);
                conteneurIcon.setLayout(new GridLayout(1,3));
                conteneurIcon.setPreferredSize(new Dimension(250, 250));

    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier1.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier2.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier3.gif")));
    	            conteneurIcon.add(new JLabel(new ImageIcon("images/Guerrier4.gif")));
    	            
    	       // Liste déroulante
    	       JPanel conteneurlisteDeroulante = new JPanel();
    	       conteneurlisteDeroulante.setBackground(Color.white);
               conteneurlisteDeroulante.setPreferredSize(new Dimension(220, 120));
               
    	       		final JComboBox listeDeroulanteAvatar = new JComboBox();
    	       		listeDeroulanteAvatar.setPreferredSize(new Dimension(130, 25));
    	       		listeDeroulanteAvatar.addItem("Le premier Tux");
    	       		listeDeroulanteAvatar.addItem("Le deuxieme Tux");
    	       		listeDeroulanteAvatar.addItem("Le troisieme Tux");
    	       		listeDeroulanteAvatar.addItem("La quatrieme Tux");
    	       		
           		conteneurlisteDeroulante.setBorder(BorderFactory.createTitledBorder("Avatar :"));
           		conteneurlisteDeroulante.add(new JLabel("Choisir son avatar :"));
           		conteneurlisteDeroulante.add(listeDeroulanteAvatar);
           		
                JPanel content = new JPanel();
                content.setBackground(Color.white);
                content.add(conteneurlisteDeroulante);
                content.add(conteneurIcon);

    
                
                JPanel control = new JPanel();
                JButton okBouton = new JButton("OK");
                
                okBouton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {                         
                                zInfo = new UtilisateurDialogInfo(nom.getText(), (String) listeDeroulanteAvatar.getSelectedItem());
                                setVisible(false);
                        }
                   
                });
                
                JButton cancelBouton = new JButton("Annuler");
                cancelBouton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                setVisible(false);
                        }                       
                });
                
                control.add(okBouton);
                control.add(cancelBouton);
                
                this.getRootPane().setDefaultButton(okBouton);
                this.getContentPane().add(panNom, BorderLayout.WEST);
                this.getContentPane().add(content, BorderLayout.CENTER);
                this.getContentPane().add(control, BorderLayout.SOUTH);
        }  

        
}

