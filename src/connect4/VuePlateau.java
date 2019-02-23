package connect4;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;



	/**
	 *
	 * @author  Arcadius SOGLO
	 */



	public class VuePlateau extends JFrame implements ActionListener,Observer{	
		Modele opts;
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		JPanel pane = new JPanel();
		
		JPanel lignesColPane = new JPanel(new GridLayout(2, 2, 10, 10));
		JLabel nbRowLabel = new JLabel("Nombre de lignes du tableau :");
		JTextField nb_ligne = new JTextField("6", 2);
		JLabel nbColLabel = new JLabel("Nombre de colonnes du tableau :");
		JTextField nb_colonne = new JTextField("7", 2);
		
		

		JButton ok = new JButton("Ok");
		

		public VuePlateau(Modele opts) {
			
			super("Options de jeu");
			setSize(600, 400);
			setLocation(50, 50);
			
			opts.addObserver(this);
			
			pane.setLayout(gbl);
			opts.addObserver(this);
			// Nb de lignes, colonnes
			buildConstraints(constraints, 0, 0, 2, 1, 80, 30);
			lignesColPane.add(nbRowLabel);
			lignesColPane.add(nb_ligne);
			lignesColPane.add(nbColLabel);
			lignesColPane.add(nb_colonne);
			
			lignesColPane.setBorder(BorderFactory.createTitledBorder("Taille du Plateau"));
			gbl.setConstraints(lignesColPane, constraints);
			
			pane.add(lignesColPane);
			
		
			// Bouton Ok
			ok.addActionListener(this);
			
			buildConstraints(constraints, 1, 6, 6, 2, 80, 30);
			gbl.setConstraints(ok, constraints);
			pane.add(ok);
			
			
			setContentPane(pane);
			
			setVisible(true);

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			
			this.opts = opts;

		}
		

		public void buildConstraints (GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {
			gbc.gridx = gx;			// Coordonn�es dans la "grille"
			gbc.gridy = gy;
			gbc.gridwidth = gw;		// Nombre de cellules sur lesquelles s'�tend l'objet
			gbc.gridheight = gh; 
			gbc.weightx = wx;		// "Largeur", en proportion
			gbc.weighty = wy;
		}
		
		public void actionPerformed(ActionEvent evt) {
			Object src = evt.getSource();
			
			if (src == ok) {
				try {
					int nbRow = Integer.parseInt(nb_ligne.getText());
					int nbCol = Integer.parseInt(nb_colonne.getText());
					if (nbRow <= 0 || nbCol <= 0)
						throw new NumberFormatException();
						opts.setSize(nbRow, nbCol, true);
					setVisible(false);
					
				}catch (NumberFormatException e) {
				PopUp.erreurMsgOk("Erreur : le nombre de ligne et le nombre de colonnes doivent �tre des entiers", "Erreur");
			}
			}
		}


		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			//nb_ligne.setText(String.valueOf(opts.getGameWidth()));
			//nb_colonne.setText(String.valueOf(opts.getGameHeight()));
			
		}

		
	}


