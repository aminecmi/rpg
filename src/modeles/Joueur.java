package modeles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Représente un joueur (réel ou ordi)
 * 
 */

public abstract class Joueur {

	private PropertyChangeSupport pcsPointDeVie = new PropertyChangeSupport(
			this);// pour les points de vie
	private PropertyChangeSupport pcsXp = new PropertyChangeSupport(this);// pour
																			// les
																			// XP
	private PropertyChangeSupport pcsPa = new PropertyChangeSupport(this);// pour
																			// les
																			// Points
																			// attaque
	private PropertyChangeSupport pcsNbVies = new PropertyChangeSupport(this);// pour
																				// les
																				// points
																				// de
																				// Vie
	private PropertyChangeSupport pcsNiveau = new PropertyChangeSupport(this);// pour
																				// les
																				// niveaux
	private PropertyChangeSupport pcsArme = new PropertyChangeSupport(this);
	/**
	 * la position du joueur sur le terrain
	 */
	protected int maxXP;
	protected Position position;
	/**
	 * le terrain sur lequel joue le joueur
	 */
	protected Terrain terrain;
	/**
	 * le nom du joueur
	 */
	private String nom = "";
	/**
	 * les points de vie du joueur
	 */
	protected int pv;
	/**
	 * l'xp du joueur
	 */
	protected int xp;
	/**
	 * la capacité d'attaque du joueur
	 */
	protected int att;
	/**
	 * l'héritage du joueur
	 */
	protected int heritage;
	/**
	 * la capacité de deplacement du joueur
	 */
	protected int deplacement;
	/**
	 * le nombre de vies du joueur
	 */
	protected int nbVies;
	/**
	 * la défense du joueur
	 */
	protected int def;
	/**
	 * le niveau du joueur
	 */
	protected int niveau;
	
	protected int pion;
	
	protected Armes arme;
	
	public Armes getArme() {
		return arme;
	}

	public void setArme(Armes arme) {
		
		Armes ancienneArme = this.arme;

		this.arme = arme;

		this.pcsArme
				.firePropertyChange("arme", ancienneArme, this.arme);

	}

	public int getPion() {
		return pion;
	}

	public void setPion(int pion) {
		this.pion = pion;
	}

	public Joueur(Terrain terrain, Position position) {
		this.terrain = terrain;
		this.position = position;
		this.pv = Ressources.pv;
		this.xp = Ressources.xp;
		this.att = Ressources.att;
		this.heritage = Ressources.heritage;
		this.deplacement = Ressources.deplacement;
		this.nbVies = Ressources.nbVies;
		this.def = Ressources.def;
		this.niveau = 1;
		this.maxXP = 30;
		this.arme = new Armes () ;
	}

	/**
	 * retourne les points de dégats (lancé de dé + capacité d'attaque) qu'il
	 * peut infliger à son adversaire
	 * 
	 * @return int att retourne les points de dégats
	 */
	public int attaque() {
		return lancerDe() + this.att+this.arme.getAttaque();
	}

	/**
	 * le défenseur retranche du coup de l'attaquant ses points de défense.
	 * 
	 * @param coup
	 *            représente l'attaque portée par l'attaquant.
	 * @return les dégats réellement infligés au défenseur.
	 */
	public int seDefend(int coup) {
		if ((coup - this.def) < 0)
			return 0;
		else
			return (coup - this.def);
	}

	/**
	 * On voit si le joueur reçoit des dégats
	 * 
	 * @param degatsRecus
	 *            les dégats après décompte de la défense du défenseur.
	 * @return true si le joueur n'a pas perdu de vie durant le combat.
	 * */
	public boolean recoitDegat(int degatsRecus) {
		return degatsRecus <= 0;
	}

	/**
	 * déplace le joueur sur une case vide du terrain choisie aléatoirement
	 */
	public void seTeleporte() {
		Position position = terrain.getTuileVideAleatoire();
		terrain
				.PositionnerJoueurs(terrain.getOccupant(this.position),
						position);
	}

	/**
	 * Retourne un nombre aléatoire grâce a Math.random entre 1 et 6
	 * 
	 * @return int un nombre entre un 1 et 6
	 */
	public int lancerDe() {
		return (int) (Math.random() * 5) + 1;
	}

	public Position getPosition() {
		return position;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public String getNom() {
		return nom;
	}

	public int getPv() {
		return pv;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		int ancienAttaque = this.att;

		this.att = att;

		this.pcsPa.firePropertyChange("pa", ancienAttaque, this.att);

	}

	public int getNbVies() {
		return nbVies;
	}

	public int getdef() {
		return def;
	}

	public int getXp() {
		return xp;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPv(int pv) {
		int ancienPv = this.pv;

		this.pv = pv;

		this.pcsPointDeVie.firePropertyChange("pv", ancienPv, this.pv);
	}

	public void setXp(int xp) {

		int ancienXp = this.xp;

		this.xp = xp;

		this.pcsXp.firePropertyChange("xp", ancienXp, this.xp);
	}

	public void setNbVies(int nbVies) {

		int ancienNbreVies = this.nbVies;

		this.nbVies = nbVies;

		this.pcsNbVies
				.firePropertyChange("nbVies", ancienNbreVies, this.nbVies);
	}

	public void setNiveau(int niveau) {

		int ancienNiveau = this.niveau;

		this.niveau = niveau;

		this.pcsNiveau.firePropertyChange("niveau", ancienNiveau, this.niveau);
	}

	public int getNiveau() {
		return this.niveau;
	}

	public int getHeritage() {
		return this.heritage;
	}

	/**
	 * Ajout d'un écouteur de changement de point de vie.
	 */
	public void ajouterEcouteurPointDeVie(PropertyChangeListener ecouteur) {
		pcsPointDeVie.addPropertyChangeListener(ecouteur);
	}

	/**
	 * Ajout d'un écouteur de changement d'XP.
	 */
	public void ajouterEcouteurXp(PropertyChangeListener ecouteur) {
		pcsXp.addPropertyChangeListener(ecouteur);
	}

	/**
	 * Ajout d'un écouteur de changement de points d'attaque
	 */
	public void ajouterEcouteurPa(PropertyChangeListener ecouteur) {
		pcsPa.addPropertyChangeListener(ecouteur);
	}

	/**
	 * Ajout d'un écouteur de changement de nombre de vie
	 */
	public void ajouterEcouteurNbreVies(PropertyChangeListener ecouteur) {
		pcsNbVies.addPropertyChangeListener(ecouteur);
	}
	
	/**
	 * Ajout d'un écouteur de changement d'arme
	 */
	public void ajouterEcouteurArme(PropertyChangeListener ecouteur) {
		pcsArme.addPropertyChangeListener(ecouteur);
	}
	
	/**
	 * Ajout d'un écouteur de changement de Niveau
	 */
	public void ajouterEcouteurNiveau(PropertyChangeListener ecouteur) {
		pcsNiveau.addPropertyChangeListener(ecouteur);
	}

	public void setHeritage(int i) {
		this.heritage=i;
		
	}

	public void setDef(int i) {
		this.def=i;
		
	}
	
	public int getMaxXp(){
		return maxXP;
	}
	public void setMaxXp(int max){
		this.maxXP=max;
	}

	public int getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	
}