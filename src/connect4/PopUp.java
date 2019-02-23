package connect4;


import javax.swing.*;


public class PopUp {
	public static int lire_entier(String message, String titre) {

		String valeur;

		while (true) {	// On boucle tant que l'utilisateur n'a pas entr� un entier
			try {
				valeur = JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
				return Integer.parseInt(valeur);
			} catch (NumberFormatException e) {
				erreurMsgOk("Erreur : entrez un entier", "Erreur");
			}
		}

	}

	public static int question_ouinon(String message, String titre) {

		int val = JOptionPane.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return val;

	}

	public static void infoMsgOk(String message, String titre) {

		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);

	}

	/** Affiche un message d'erreur avec un showMessageDialog
	 * @param message Message a afficher
	 * @param titre Titre de la fenetre de message
	 */	
	public static void erreurMsgOk(String message, String titre) {

		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.ERROR_MESSAGE);

	}



}
