package connect4;


import java.util.Observable;

/* modele de jeu */

/**
 * 
 * @author SOGLO Arcadius
 *
 */


public class Modele extends Observable  {
	private int nbRow;			// Nombre de lignes
	private int nbCol;			// Nombre de colonnes
	Controleur jeu;
	private int nb_joueurs; //nombre de joueurs
	
	
	private String joueur1;  //nom des joueurs
	private String joueur2;
	private String joueur3;
	private String joueur4;
	
	public Modele(Controleur j) {
		
		this.jeu = j;
		VuePlateau_Players optionsGUI = new VuePlateau_Players(this);
		
	}
	
	public Modele(int nbRow, int nbCol, int nb_joueurs, Controleur jeu) {
		this.jeu = jeu;
		setSize(nbRow, nbCol, true);
		setNb_joueurs(nb_joueurs);
		
	}
	
	
	/* Regle les dimensions du jeu
	 * initSize Definit si le plateau de jeu doit etre initalise avec la taille entree */
	
	public void setSize(int nbRow, int nbCol, boolean initSize) {
		this.nbRow = nbRow;
		this.nbCol = nbCol;
		if (initSize)
			initSize(nbRow, nbCol);
		setChanged();
		notifyObservers();
	}
	
	/* Initialise le plateau de jeu */
	
	public void initSize(int nbRow, int nbCol) {
		jeu.plateau = new Game_View(nbRow, nbCol, jeu);
		jeu.plateau.setVisible(true);
		jeu.matJeu = new int[nbRow][nbCol];
		jeu.historique = new int[nbRow * nbCol];
	}
	
	/* Retourne le nombre de colonnes du jeu */
	
	public int getGameWidth() {
		return nbCol;
	}
	
	/*  Retourne le nombre de lignes du jeu */
	
	public int getGameHeight() {
		return nbRow;
	}
	
	/* Retourne le nombre de joeur */
	public int getNb_joueurs() {
		return nb_joueurs;
	}

	/*recupere nom joueur 1 */
	public String getJoueur1() {
		return joueur1;
	}

	public String getJoueur2() {
		return joueur2;
	}

	public String getJoueur3() {
		return joueur3;
	}

	public String getJoueur4() {
		return joueur4;
	}

	
	/* modifie le nombre de joeurs */
	
	public void setNb_joueurs(int nb_joueurs) {
		this.nb_joueurs = nb_joueurs;
		setChanged();
		notifyObservers();
	}
	
	/* modifie le nom des joueurs*/
	public void setJoueur1(String j1){
		this.joueur1=j1;
		setChanged();
		notifyObservers();
	}
	
	public void setJoueur2(String j2){
		this.joueur2=j2;
		setChanged();
		notifyObservers();
	}
	
	public void setJoueur3(String j3){
		this.joueur3=j3;
		setChanged();
		notifyObservers();
	}
	
	public void setJoueur4(String j4){
		this.joueur4=j4;
		setChanged();
		notifyObservers();
	}
	
	
	
}
