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


public class VuePlateau_Players extends JFrame implements ActionListener,Observer {
	
	Modele opts;
	
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	JPanel pane = new JPanel();
	
	JPanel lignesColPane = new JPanel(new GridLayout(2, 2, 10, 10));
	JLabel nbRowLabel = new JLabel("Nombre de lignes du tableau :");
	JTextField saisiLigne = new JTextField("6", 2);
	JLabel nbColLabel = new JLabel("Nombre de colonnes du tableau :");
	JTextField saisiColonne = new JTextField("7", 2);
	
	JPanel panel_saisiJ= new JPanel(new GridLayout(4, 4, 10, 10));
	JLabel label_J1= new JLabel("Veuillez saisir le nom du Joueur 1 :");
	JTextField saisiJ1 = new JTextField("Joueur 1");
	JLabel label_J2= new JLabel("Veuillez saisir le nom du Joueur 2 :");
	JTextField saisiJ2 = new JTextField("Joueur 2");
	JLabel label_J3= new JLabel("Veuillez saisir le nom du Joueur 3 :");
	JTextField saisiJ3 = new JTextField("Joueur 3");
	JLabel label_J4= new JLabel("Veuillez saisir le nom du Joueur 4 :");
	JTextField saisiJ4 = new JTextField("Joueur 4");
	JPanel panel_choix= new JPanel(new GridLayout(1,1));
	
	JComboBox choix_nb_joueur;  //JCombox choix nombre de joueur
	JLabel label_nb= new JLabel("Veuillez saisir le nombre de joueur : ");

	JButton ok = new JButton("Ok");
	
	 int nombres;
	 public void setnombres(int nb){
		 this.nombres=nb;
	 }
	 
	 public int getnombres(){
		 return nombres;
	 }
	 
	/* constructeur*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VuePlateau_Players(Modele opts) {
		
		super("Options de jeu");
		setSize(600, 400);
		setLocation(50, 50);
		pane.setLayout(gbl);
		opts.addObserver(this);
		// Nb de lignes, colonnes
		buildConstraints(constraints, 0, 0, 2, 1, 80, 30);
		lignesColPane.add(nbRowLabel);
		lignesColPane.add(saisiLigne);
		lignesColPane.add(nbColLabel);
		lignesColPane.add(saisiColonne);
		
		lignesColPane.setBorder(BorderFactory.createTitledBorder("Taille du Plateau"));
		gbl.setConstraints(lignesColPane, constraints);
		
		pane.add(lignesColPane);
		
		String[] tab_int = {" 2" ," 3" ," 4" };
		choix_nb_joueur = new JComboBox(tab_int);
		
		panel_choix.add(label_nb);
		panel_choix.add(choix_nb_joueur);
		
		opts.setNb_joueurs(2);
		choix_nb_joueur.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				Object src = e.getSource();
				if(choix_nb_joueur.getSelectedIndex()==0){
					opts.setNb_joueurs(2);
					
					
				}
				
				if(choix_nb_joueur.getSelectedIndex()==1){
					opts.setNb_joueurs(3);
					
				}
				if(choix_nb_joueur.getSelectedIndex()==2){
					opts.setNb_joueurs(4);
					
				}
					

				//System.out.println(opts.getNb_joueurs());
				
			}});
	
		panel_saisiJ.add(label_J1);
		panel_saisiJ.add(saisiJ1);
		panel_saisiJ.add(label_J2);
		panel_saisiJ.add(label_J2);
		panel_saisiJ.add(saisiJ2);
		panel_saisiJ.add(label_J3);
		panel_saisiJ.add(saisiJ3);
		panel_saisiJ.add(label_J4);
		panel_saisiJ.add(saisiJ4);
		buildConstraints(constraints, 1, 5, 2, 1, 80, 30);
		gbl.setConstraints(panel_saisiJ, constraints);
		pane.add(panel_saisiJ); 
		panel_saisiJ.setBorder(BorderFactory.createTitledBorder("Pseudo des Joueurs"));
		
		buildConstraints(constraints, 1, 4, 20, 1, 80, 30);
		gbl.setConstraints(panel_choix, constraints);
		
		
		
		pane.add(panel_choix);
		
		panel_choix.setBorder(BorderFactory.createTitledBorder("Nombres de joueurs"));
		
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
				int nbRow = Integer.parseInt(saisiLigne.getText());
				int nbCol = Integer.parseInt(saisiColonne.getText());
				if (nbRow <= 0 || nbCol <= 0)
					throw new NumberFormatException();
					opts.setSize(nbRow, nbCol, true);
					opts.setJoueur1(saisiJ1.getText());
					opts.setJoueur2(saisiJ2.getText());
					opts.setJoueur3(saisiJ3.getText());
					opts.setJoueur4(saisiJ4.getText());
				
			
				setVisible(false);
				
			}catch (NumberFormatException e) {
			PopUp.erreurMsgOk("Erreur : le nombre de ligne et le nombre de colonnes doivent etre des entiers", "Erreur");
		}
		}
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		//saisiLigne.setText(String.valueOf(opts.getGameWidth()));
		//saisiColonne.setText(String.valueOf(opts.getGameHeight()));
		//saisiJ1.setText(opts.getJoueur1());
		//saisiJ2.setText(opts.getJoueur2());
		//saisiJ3.setText(opts.getJoueur3());
		//saisiJ4.setText(opts.getJoueur4());
		
		
	}


	}

