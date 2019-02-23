package connect4;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
*
* @author  Arcadius SOGLO
*/


public class Game_View extends JFrame implements MouseListener,  WindowListener{
	static int nbGrilles;
	
	
	Controleur jeu;
	GridLayout gridLay;
	
	
	JLabel statusBar;
    
	/* mes boutons */
	JButton restart ;
	JButton Stop ;
	JButton bChange_Grid;
	JButton bChange_players;

	JLabel label_nb_players ;
	JLabel label_nb_ligne ;
	JLabel label_nb_colonne;
	
	JTextArea Score_Max ; //zone de sauvegarde des score max
	JPanel game_panel = new JPanel(); //panel qui contiendra la grille de jeu
	JPanel pane = new JPanel(); //panel qui contiendra la matrice de jeu
	
	public Game_View(int nbRow, int nbCol, Controleur jeu) {
		super("Puissance 4");
		setSize(400, 300);
		setLocation(50, 50);
		
		game_panel.setLayout(new BorderLayout());
		
		gridLay = new GridLayout(nbRow, nbCol, 0, 0);
		pane.setLayout(gridLay);
		
		makeCells(nbRow, nbCol, jeu);
		
		game_panel.add(pane, "Center");
		
		statusBar = new JLabel(jeu.opts.getJoueur1(), SwingConstants.LEFT);
		
		
		game_panel.add(statusBar, "South");
		
		setVisible(false);
		
		addWindowListener(this);
		
		nbGrilles++;   // Une nouvelle grille a ete cree
		
		
		// panel de droite de ma vue
		JPanel panel = new JPanel();
		GridLayout magridlayout = new GridLayout(5,1);
		magridlayout.setVgap(16);
		magridlayout.setHgap(16);
		panel.setLayout(magridlayout);
		panel.setBorder(BorderFactory.createTitledBorder("Details du jeu en cours"));
		panel.setBackground(Color.WHITE);
					
		label_nb_players = new JLabel("Nombre de joueurs : " +jeu.opts.getNb_joueurs());
		label_nb_ligne = new JLabel("Nombre de lignes : " + jeu.opts.getGameHeight());
		label_nb_colonne = new JLabel("Nombre de colonne : " +jeu.opts.getGameWidth());
		JLabel label_style1 = new JLabel("");
		JLabel label_style2 = new JLabel("");
		JLabel vide = new JLabel();
		panel.add(label_style1);
		panel.add(label_nb_ligne);
		panel.add(label_nb_colonne);
		panel.add(label_nb_players);
		panel.add(label_style2);
		
					
		////recuperer dans un fichier les scores max et le mettre dans le JTextArea  ?
		File score = new File("scoremax.txt");
		Score_Max = new JTextArea("les scores max ici. naviguez vers le bas ! \n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+"\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n");
		readTextFile(Score_Max, score); //methodes defini plus bas qui ouvre le fichier et insere les lignes 
		
		JScrollPane panel_score_max = new JScrollPane(Score_Max);
		panel_score_max.setBorder(BorderFactory.createTitledBorder("Score Max"));
		Score_Max.setLineWrap(true);
		panel_score_max.setBackground(Color.WHITE);
					
		//Panel de boutons  et listiners associes
		JPanel panel_bouton = new JPanel();
		GridLayout magridlayout2 = new GridLayout(2,2);
		magridlayout2.setVgap(16);
		magridlayout2.setHgap(16);
		panel_bouton.setLayout(magridlayout2);
		restart = new JButton(" Restart");
		
		restart.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent event) {
					JButton src = (JButton)event.getSource();
						dispose();
						new Controleur(true);	
		}});
					
		Stop = new JButton(" Exit ");
		Stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
			JButton src = (JButton)event.getSource();
			JOptionPane.showMessageDialog(null, "Good Bye !  ", "Message fin", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
		}});
					
					
		bChange_Grid = new JButton("Changer Plateau");
		bChange_Grid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				JButton src = (JButton)event.getSource();
					dispose();
					new VuePlateau(jeu.opts);
			}
		});
					
		bChange_players = new JButton(" Nouveau Jeu");
		bChange_players.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				JButton src = (JButton)event.getSource();
				dispose();
				new Controleur(true);
			}
		});
		
		panel_bouton.add(bChange_Grid);
		panel_bouton.add(bChange_players);
		panel_bouton.add(restart);
		panel_bouton.add(Stop);
		panel_bouton.setBackground(Color.WHITE);
					
					
					
					
		//----------------init frame positionnement panel de gauche et de droite cote a cote en mode GBL--------------------
						//--------------------------------------------------------------------------------//
		//Jpanel de gauche et de droite 
		JPanel panel_left = new JPanel();
		JPanel panel_right = new JPanel();

		//Mettre les 2 panel cote a cote avec GridBagLayout
		panel_left.setBackground(Color.white);
		panel_left.setBorder(BorderFactory.createLineBorder(Color.blue));
		panel_right.setBackground(Color.white);
		panel_right.setBorder(BorderFactory.createLineBorder(Color.blue));
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(gbl);
		gbc.gridx = 0;//colonne 0,0 coin sup gauche
		gbc.gridy = 0;//position de la ligne ici ligne 0
		gbc.gridwidth=1; //la largeur de mon bloc
		gbc.gridheight=1; //la hauteur de mon bloc
		gbc.weightx=0.9; //effet de grandissement(pourcentage a prendre sur largeur) ici 75%
		gbc.weighty=1.0; //sur la hauteur lelement occupe tjrs 100% du container
		gbc.fill= GridBagConstraints.BOTH; //permet de specifier si lelement saggrandit sur la largeur ou hauteur
		this.add(game_panel,gbc);
		gbc.gridx = 1;//colonne 1 coin sup gauche
		gbc.gridy = 0;//ligne 0
		gbc.gridwidth=1;//largeur de mon colonne en colonne 
		gbc.gridheight=1;//hauteur de mon bloc en colonne
		gbc.weightx=0.1; //il occupe 25% de lespace totale sur la largeur
		gbc.weighty=1.0; //sur la hauteur lelement occupe tjrs 100% du container
		gbc.fill= GridBagConstraints.BOTH;
		this.add(panel_right,gbc);
		this.setVisible(true);
		this.setTitle("Puissance 4 avec MVC/J2D");
		this.setPreferredSize(new Dimension(800,600));
		this.pack();
				    
				
		//------------positionnement specifique a la droite--------------------------------
		
		 GridLayout layout_droite = new GridLayout(3,1);
		 panel_right.setLayout(layout_droite);
					   
		Score_Max.setEditable(false);
		Score_Max.setLineWrap(true);
		Score_Max.setBackground(Color.WHITE);
		panel_right.add(panel_score_max); 
		panel_right.add(panel);
		panel_right.add(panel_bouton);
		
	}
	

	/*methode pour creer les cases */	
	public void makeCells(int nbRow, int nbCol, Controleur jeu) {
		Case c;
		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbCol; j++) {
				c = new Case(i + 1, j + 1, 0, jeu);
				c.addMouseListener(this);
				pane.add(c);
			}
		}	
	}
	
	
	/*methode pour ouvrir le fichier de score et recuperer les scores et les afficher dans la zone score_max*/
	
	public void readTextFile(JTextArea texte, File fileName) 
 	{
 		try 
 			{
  			BufferedReader inStream  
      				= new BufferedReader (new FileReader(fileName));
  			
 			String line = inStream.readLine(); 
 			
 			//if (line.equals("Les Anciens Scores \n")) {  // Test de validitee de fichier pas necessaire
	 		 	while (line != null)
	 		 	 {                        
	     	       texte.append(line + " \n");                
			      line = inStream.readLine();                  
	  			}
   			inStream.close();                              
  			//}// else
			//Saisie.erreurMsgOk("Le fichier nest pas authentique");
 			
 			}
 		
 			catch (IOException e) {	
			}
 			catch (Exception e) {
              texte.setText("Exception cause: "+e);
   		      e.printStackTrace();
  			}	
 		
 	}	
//-------------controle des actions sur la vue --------------------------------------//
	
	public void mouseClicked(MouseEvent evt) {
		

	}
	
	public void mouseEntered(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.col;
		int ligne = src.jeu.ChercheColonne(col);
		if (ligne != -1) {
			Case cc = (Case)src.jeu.plateau.pane.getComponent((src.jeu.opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierBg(new Color(125, 255, 242));
			repaint();
			
		}
	}
	
	
	public void mouseExited(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.col;
		int ligne = src.jeu.ChercheColonne(col);
		if (ligne != -1) {
			Case cc = (Case)src.jeu.plateau.pane.getComponent((src.jeu.opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierBg(Color.white);
			repaint();
		}
		
	}
	
	public void mousePressed(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		src.jeu.jouer(src.col);
			
	}
	
	public void mouseReleased(MouseEvent evt) {
		
	}
	
				
	public void windowActivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		JOptionPane.showMessageDialog(null, "Merci d'avoir jouer a puissance 4 ", "Information ", JOptionPane.INFORMATION_MESSAGE);

		//nbGrilles--;
		//if (nbGrilles == 0) // 
			//System.exit(-1);
	}
	
	public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowIconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowOpened(java.awt.event.WindowEvent windowEvent) {
		//String joueur1= ;
		JOptionPane.showMessageDialog(null, "Bienvenue a puissance 4 \n "
				+ "Premier joueur joue un pion \n"
				+ "pour commencer la partie", "Welcome message", JOptionPane.INFORMATION_MESSAGE);

		
	}


	
}
