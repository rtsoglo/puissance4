package connect4;

import java.io.*;

/**
*
* @author  Arcadius SOGLO
* 
*/


public class Controleur{
	
	boolean enCours;	// Indique si la partie est en cours ou terminee
	boolean joueur;		// Indique le joueur en cours
	
	int nbCoups;		// Contient le nombre de coups joues (detection partie nulle)
	int[] historique;	// Enregistre les colonnes jouees
	Modele opts;		
	
	Game_View plateau;		//  la vue du jeu. controle la vue /validation action user
	int [][] matJeu;	// La matrice qui contiendra la partie
	
	public Controleur(boolean optTrue) {
		if (optTrue)
			opts = new Modele(this);
			
		nbCoups = 0;
		enCours = true;
	}

	
	public boolean joueurGagne(boolean joueur, int ligneM, int colM) {  // le M majuscule indique que c'est une ligne correspondant a la position dans la matrice
		int jVal = 1; // Variable contenant la valeur du joueur,
		if (joueur)
			jVal = 2;
		
		if (horiGagne(jVal, ligneM, colM) || vertGagne(jVal, ligneM, colM) || diag1Gagne(jVal, ligneM, colM) || diag2Gagne(jVal, ligneM, colM))
			return true;
		
		return false;
	}
	
	
	public boolean horiGagne(int jVal, int ligneM, int colM) {
		int nbAlign = 0;  // nombre de pions qui sont alignes les uns a la suite des autres
		int colMin = colM - 3;
		if (colMin <= 0)
			colMin = 0;
		
		int colMax = colM + 3;
		if (colMax >= opts.getGameWidth())
			colMax = opts.getGameWidth() - 1;
		
		for (int i = colMin; i <= colMax; i++) {
			if (this.matJeu[ligneM][i] == jVal)
				nbAlign++;
			else
				nbAlign = 0;

			if (nbAlign == 4)
				return true;
		}
		return false;
	}
	
	
	public boolean vertGagne(int jVal, int ligneM, int colM) {
		int nbAlign = 0;  // nombre de pions qui sont alignes les uns a la suite des autres
		int ligneMin = ligneM - 3;
		if (ligneMin <= 0)
			ligneMin = 0;
		
		int ligneMax = ligneM + 3;
		if (ligneMax >= opts.getGameHeight())
			ligneMax = opts.getGameHeight() - 1;
		
		
		for (int i = ligneMin; i <= ligneMax; i++) {
			if (this.matJeu[i][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
		
			if (nbAlign == 4)
				return true;
		}
		return false;
	}
	
	
	public boolean diag1Gagne(int jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		int compteur = 0;
		while (ligneMax + 1 < opts.getGameHeight() && colMax + 1 < opts.getGameWidth() && compteur <= 2) {  //On va en bas a droite du plateau
			ligneMax++;
			colMax++;
			compteur++;   // on ne va que 3 cases en bas � droite au maximum
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMin >= 1 && compteur <= 2) {  //On va en haut a gauche du plateau de jeu
			ligneMin--;
			colMin--;
			compteur++;   // on ne va que 3 cases en bas � droite au maximum
		}
		
		ligneM = ligneMin;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
				
			ligneM++;
			colM++;
			
		} while (ligneM <= ligneMax && colM <= colMax);
		
		return false;
	}
	
	
	public boolean diag2Gagne(int jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		int compteur = 0;
		while (ligneMax + 1 < opts.getGameHeight() && colMin >= 1 && compteur <= 2) {  //On va en bas a gauche du plateau
			ligneMax++;
			colMin--;
			compteur++;   // on ne va que 3 cases en bas � droite au maximum
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < opts.getGameWidth() && compteur <= 2) {  //On va en haut a droite du plateau de jeu
			ligneMin--;
			colMax++;
			compteur++;   // on ne va que 3 cases en bas � droite au maximum
		}
		
		ligneM = ligneMax;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
			
			ligneM--;
			colM++;
					
		} while (ligneM >= ligneMin && colM <= colMax);
		
		return false;
	}
	
	
	public void jouer(int col) {
		boolean coupValable;	// Contient la validite du coup que le jouer veut jouer
		int ligne = 0;		// La ou sera stockee la ligne jouee, le 0 sert pour le compilateur
		
		coupValable = false;
			
		ligne = this.ChercheColonne(col);
		coupValable = this.coupValable(ligne, col);
		
		if (coupValable)
			validerCoup(ligne, col);
		
	}

	
	public void validerCoup(int ligne, int col){
		
		if (!joueur) {
			this.matJeu[ligne - 1][col - 1] = 1;  // 1 designe dans la matrice un pion du joueur "false"
			Case cc = (Case)plateau.pane.getComponent((opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierVal(1);
		} else {
			this.matJeu[ligne - 1][col - 1] = 2;  // 2 designe dans la matrice un pion du joueur "true"
			Case cc = (Case)plateau.pane.getComponent((opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierVal(2);
		}

		boolean gagne = this.joueurGagne(joueur, ligne - 1, col - 1);
		if (gagne) {
			
			File score = new File("scoremax.txt");
			enCours = false; // La partie est terminee
			this.enregistrer(score);
			if (!joueur)
				PopUp.infoMsgOk("Le joueur " + opts.getJoueur1() +" a gagne", "Bravo");
			else
				PopUp.infoMsgOk("Le joueur " +opts.getJoueur2() + " a gagne", "Bravo");
		}
		
		
		nbCoups++;  
		this.setNbCoups(nbCoups);
		if (nbCoups >= opts.getGameHeight() * opts.getGameWidth() && !gagne) {
			PopUp.infoMsgOk("Aucun des 2 joueurs n'a su gagner... : partie nulle", "Partie nulle");
			enCours = false;  // La partie est terminee
		}
		
		historique[nbCoups - 1] = col;
		joueur = !joueur;
		if (joueur) {
			plateau.statusBar.setText(" (Le joueur " + opts.getJoueur1() +" joue )" + " shots : "+ this.getNbCoups());
			
		}
		else {
			plateau.statusBar.setText(" (Le joueur " + opts.getJoueur2()+ " joue )" + " shots : "+ this.getNbCoups());
			
		}
		
		if (!enCours) {		// Proposition de faire une nouvelle partie
			int ok = PopUp.question_ouinon("La partie est terminee, voulez-vous en faire une nouvelle ?", "Nouvelle partie");
			if (ok == 0){
				nouveauJeu();
		}
		}
	}
		
	
	// Methode testant la validite d'un coup
	
	public boolean coupValable(int ligne, int col) {
		
		if (ligne == -1) {
			PopUp.erreurMsgOk("Vous ne pouvez pas jouer ce coup : la colonne est remplie", "Coup invalide");
			return false;
		}
		
		
		return true;
	}
	
	// Methode cherchant la premiere ligne jouable d'une colonne 
	
	public int ChercheColonne(int col) {
		for (int i = opts.getGameHeight(); i >= 1; i--) {
			if (matJeu[i - 1][col - 1] == 0)
				return i;
		}
		return -1; // Aucune ligne n'a ete trouve : la colonne est remplie
	}
	


	public int [] getHistorique(){
		return this.historique;
	}
	
	public int getNbCoups(){
		return this.nbCoups;
	}
	
	public void setNbCoups(int shots){
		this.nbCoups=shots;
		
	}
	
	
	public static void nouveauJeu() {
		Controleur j = new Controleur(true);
	}
	
	// enregistrer les scores
	
	public void enregistrer(File score) {
		try {
			FileWriter fw = new FileWriter(score,true);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(" \n");
			out.write(opts.getJoueur1()   + " vs ");
			out.write(opts.getJoueur2() + " \n");
			out.write("Nombre de Colonne : " + opts.getGameHeight() + " \n");
			out.write("Nombre de Ligne :" + opts.getGameWidth() + " \n");
			out.write(" Score  : "+ nbCoups + "\n");
			out.close();
		} catch (IOException e) {
			
		}
	}
	
	

}
