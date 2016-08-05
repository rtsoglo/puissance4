package puissance4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZDialog extends JDialog {
	
	private ZDialogInfo zinfo = new ZDialogInfo(); //modele associe a ma vue
	
	private boolean sendData;
	private JLabel label_ligne, label_colonne,label_joueur;
	private JTextField saisi_ligne, saisi_colonne, saisi_joueur;
	
	public ZDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		
	}	
	
		public ZDialogInfo showZDialog(){
			this.sendData=false;
			this.setVisible(true);
			return this.zinfo;
			
		}
	
		private void initComponent(){
			
			//Taille de jeu
			JPanel taille_jeu = new JPanel();
			JPanel ligne = new JPanel();
			ligne.setBackground(Color.white);
			JPanel colonne = new JPanel();
			colonne.setBackground(Color.white);
			taille_jeu.setBackground(Color.white);
			taille_jeu.setPreferredSize(new Dimension(220,60));
			saisi_ligne = new JTextField();
			saisi_ligne.setPreferredSize(new Dimension(100,25));
			
			saisi_colonne = new JTextField();
			saisi_colonne.setPreferredSize(new Dimension(100,25));
			taille_jeu.setBorder(BorderFactory.createTitledBorder("Taille du Plateau"));
			label_ligne = new JLabel("Saisir nombre de ligne");
			label_colonne = new JLabel("Saisir nombre de colonne");
			ligne.add(label_ligne);
			ligne.add(saisi_ligne);
			colonne.add(label_colonne);
			colonne.add(saisi_colonne);
			taille_jeu.add(ligne);
			taille_jeu.add(colonne);
			
			
			//nombre de joueurs
			JPanel nbplayer = new JPanel();
			nbplayer.setBackground(Color.white);
			nbplayer.setPreferredSize(new Dimension(220,60));
			saisi_joueur = new JTextField();
			saisi_joueur.setPreferredSize(new Dimension(100,25));
			nbplayer.setBorder(BorderFactory.createTitledBorder("Mode de jeu"));
			label_joueur = new JLabel("Saisir nombre de joueur");
			nbplayer.add(label_joueur);
			nbplayer.add(saisi_joueur);
			
			
			JButton okBouton = new JButton("Start");
			JButton cancelBouton = new JButton("Cancel");
			
			okBouton.addActionListener(new ActionListener(){
			      public void actionPerformed(ActionEvent arg0) {        
			        zinfo = new ZDialogInfo(saisi_joueur.getText(), saisi_ligne.getText(), saisi_colonne.getText());
			        
			        setVisible(false);
			      }
			});
			
			     
			      cancelBouton.addActionListener(new ActionListener(){
			    	  public void actionPerformed(ActionEvent e) {
			    		  setVisible(false);
					}
			        });      
			      
			      
			      JPanel control = new JPanel();
			      control.add(okBouton);
			      control.add(cancelBouton);

			      this.getContentPane().add(taille_jeu, BorderLayout.CENTER);
			      this.getContentPane().add(nbplayer, BorderLayout.NORTH);
			      this.getContentPane().add(control, BorderLayout.SOUTH);
			     
		}	
	}
	
	


