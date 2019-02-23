package puissance4;

import java.util.Observable;

public class ZDialogInfo extends Observable {
	private String nb_ligne, nb_colonne;
	private String nb_joueur;
	
	public ZDialogInfo(){};
	public ZDialogInfo(String nb_joueur, String nb_ligne, String nb_colonne){
		this.nb_colonne=nb_colonne;
		this.nb_ligne=nb_ligne;
		this.nb_joueur=nb_joueur;		
	};
	
	public boolean check(){
		if(this.nb_ligne !=null && this.nb_colonne !=null && this.nb_joueur !=null){
			return true;
		}
		else {
			return false;
		}			
	}
	
	public void setNb_ligne(String nb_ligne) {
		this.nb_ligne = nb_ligne;
		setChanged();
		notifyObservers();
	}
	
	public void setNb_colonne(String nb_colonne) {
		this.nb_colonne = nb_colonne;
		setChanged();
		notifyObservers();
	}
	
	
	public void setNb_joueur(String nb_joueur) {
		this.nb_joueur = nb_joueur;
		setChanged();
		notifyObservers();
	}
	
	public int getNb_ligne() {
		return Integer.parseInt(nb_ligne);
	}
	
	public int getNb_colonne() {
		return Integer.parseInt(nb_colonne);
	}
	
	
	public int getNb_joueur() {
		return Integer.parseInt(nb_joueur);
	}

		
	}

