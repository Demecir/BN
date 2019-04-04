package com.iut.bataillenavale;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
class Fenetre extends JFrame
{
	
	//Cr�ation des variables et initialisation
	private final int WIDTH = 620 ;
	private final int HEIGHT = 440 ;
	private int lig, col, coo, nbre ;
    int touche = 0 ;  //nb de touche que j'ai fait
    int toucheA = 0 ; //nb de touche de l'adversaire
    int colo = 0 ;
    int ln = 0 ; 
    int continuer = 0 ;
	int choix = 0 ;
	int ok = 0 ;	
	String grille[][] = new String[11][11] ;
  	String grilleAdver[][] = new String[11][11] ;
   	String grilleCachee[][] = new String[11][11] ;	
   	
   	
	//Cr�ation des composants graphiques
	public JButton valider, validerB, validerC, jouer, sortir ;
	private JLabel lblVotreFlotte, labelA, labelB, labelVous, labeladver ;
	private JTextArea textArea, textArea2, textAreaRegle ;
	private JScrollPane scrollPane, scrollPane2, scrollPane3 ;
	private JTextField ligne, colonne ;
	private JCheckBoxMenuItem bat5, bat4, bat3a, bat3b, bat2;	
	private JComboBox<String> coordonnee ;
	private JMenuBar menuBar ;
	private JMenu bateau, menu;
	private JButton commencer ;
	
	//Cr�ation des constructeurs 
   	Methode met = new Methode() ;	
   	private JLabel label_1;


	public Fenetre()
	{
		setBackground(new Color(0, 153, 51));
		
		//Cr�� un objet outil et lui affecte ses caract�ristiques (il va me permmetre d'afficher l'image)
		Toolkit kit = Toolkit.getDefaultToolkit() ;
		Dimension screenSize = kit.getScreenSize() ;
		int screenHeight = screenSize.height ;
		int screenWidth = screenSize.width ;
		
		
		//G�n�ration de la fen�tre 
		setTitle("Bataille navale") ;
		setSize(738, 449) ;
		setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2) ;
 		//Place une image au sommet de la fen�tre
 		Image img = kit.getImage("C:\\Users\\Utilisateur\\Desktop\\BN.jpg") ;
		setIconImage(img) ;
		
		
	
		//Cr�ation des grilles de jeux
		
		grille = met.Initialise(grille) ;
		grilleAdver = met.Initialise(grille) ;
		grilleCachee = met.Initialisecach(grille) ;

			
		//1. Barre du Menu	
		menuBar = new JMenuBar() ;
		setJMenuBar(menuBar) ;

		
		//2. Options du Menu
		menu = new JMenu("Fichier") ;
		menuBar.add(menu) ;
		menu.setEnabled(true) ;
		
		bateau = new JMenu("Bateau") ;
		menuBar.add(bateau) ;
		bateau.setEnabled(false) ;
		

		//3a. Sous-Options du Menu FICHIER
		JMenuItem nouvelle = new JMenuItem("Connexion au serveur") ;
		menu.add(nouvelle) ;
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)) ;
		nouvelle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//R�initialise les variables � 0 ;
				continuer = ln = colo = choix = ok = 0 ;
				commencer.setEnabled(false) ;
				bateau.setEnabled(true) ;
				grille = met.Initialise(new String[11][11]) ;
				grilleAdver = met.Initialisecach(new String[11][11]) ;
				grilleCachee = met.Initialisecach(new String[11][11]) ;
				//Raffiche les nouvelles grilles de jeux
				met.FirstPrint(grilleCachee, textArea2) ;
				met.FirstPrint(grille, textArea) ;
				bat5.setSelected(false) ;

				bat5.setEnabled(true) ;
				bat4.setSelected(false) ;
				bat4.setEnabled(false) ;
				bat3a.setSelected(false) ;
				bat3a.setEnabled(false) ;
				bat3b.setSelected(false) ;
				bat3b.setEnabled(false) ;
				bat2.setSelected(false) ;
				bat2.setEnabled(false) ;
				
				
				
				
				
				
				scrollPane.setVisible(true) ;
				scrollPane2.setVisible(true) ;
				scrollPane3.setVisible(false) ;
				menu.setEnabled(true) ;

				lblVotreFlotte.setVisible(true) ;
				labelA.setVisible(true) ;

				labelVous.setVisible(false) ;
				labeladver.setVisible(false) ;
			}
		}) ;
		
		JMenuItem facile = new JMenuItem("Commencer la partie") ;
		menu.add(facile) ;
		facile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//setVisible affiche les Boutons et JTextField pour commencer la partie en 
				//mode facile
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				valider.setVisible(false); 
				validerB.setVisible(true) ;
				validerC.setVisible(false) ;
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;	
			
			
	
		
			}
		}) ;		


		//3b. Sous-Options du Menu BATEAU
		bat5 = new JCheckBoxMenuItem("Bateau � 5 cases") ;
		bateau.add(bat5) ;
		bat5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//On place le bateau � 5 case
				bat5.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;
				labelB.setVisible(true) ;
				nbre = 5 ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
				bat4.setEnabled(true);
			}
		}) ;
				
		bat4 = new JCheckBoxMenuItem("Bateau � 4 cases") ;
		bateau.add(bat4) ;
		bat4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//On place le bateau � 4 case
				nbre = 4 ;
				bat4.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;	
				bat3a.setEnabled(true);
			}
		}) ;

		
		bat3a = new JCheckBoxMenuItem("Bateau � 3 cases (1er)") ;
		bateau.add(bat3a) ;
		bat3a.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//On place le bateau � 3 case
				nbre = 3 ;
				bat3a.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
				bat3b.setEnabled(true);
			}
		}) ;
	
				
		bat3b = new JCheckBoxMenuItem("Bateau � 3 cases (2eme)") ;
		bateau.add(bat3b) ;
		bat3b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//On place le bateau � 3 case (le deuxi�me)
				nbre = 3 ;
				bat3b.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
				bat2.setEnabled(true);
			}
		}) ;
		
					
		bat2 = new JCheckBoxMenuItem("Bateau � 2 cases") ;
		bateau.add(bat2) ;
		bat2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//On place le bateau � 2 case
				nbre = 2 ;
				bat2.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
				commencer.setEnabled(true) ;
			}
		}) ;
		
		commencer = new JButton("Valider") ;
		commencer.setVisible(false) ;
		commencer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				
			}
		}) ; 
		
		//Cr�ation d'un container pour acceuillir les diff�rents composant de l' IHM	
		Container contentPane = getContentPane() ;

		

		
		//Cr�ation des textes
		lblVotreFlotte = new JLabel("                                           Votre flotte") ;
		labelA = new JLabel("Flotte ennemie") ;
		labelA.setHorizontalAlignment(SwingConstants.RIGHT);
		labelB = new JLabel("Entrer les coordonn�es pour :") ;
		labelVous = new JLabel("VOUS AVEZ GAGNE") ;
		labeladver = new JLabel("VOUS AVEZ PERDU") ;
		
		lblVotreFlotte.setVisible(false) ;
		labelA.setVisible(false);
		labelB.setVisible(false) ;
		labelVous.setVisible(false) ;
		labeladver.setVisible(false) ;

	
		//Cr�ation des zones de jeux
		textArea = new JTextArea(9, 40) ;		
		textArea.setForeground(SystemColor.windowText);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setToolTipText("");
		textArea.setFont(new Font("Montserrat", Font.BOLD, 14));
		textArea.setLineWrap(true) ;	
		textArea.setEnabled(false) ;
		

		textArea2 = new JTextArea(8, 40) ;
		textArea2.setToolTipText("");
		textArea2.setBackground(Color.WHITE);
		textArea2.setForeground(Color.BLACK);
		textArea2.setFont(new Font("Montserrat", Font.BOLD, 14));
		textArea2.setLineWrap(true) ;	
		textArea2.setEnabled(false) ;
	 	
	 	//Cr�ation de la zone pour l'�cran de pr�sentation
		JPanel pane = new JPanel(); 
		JLabel image = new JLabel( new ImageIcon( "BN.jpg")); 
		pane.setLayout(new BorderLayout());
		pane.add(image); 
		
		scrollPane = new JScrollPane(textArea) ;
		scrollPane2 = new JScrollPane(textArea2) ;
		scrollPane3 = new JScrollPane(textAreaRegle) ;
		scrollPane.setVisible(false) ;
		scrollPane2.setVisible(false) ;

				
		met.FirstPrint(grille, textArea) ;
		met.FirstPrint(grilleAdver, textArea2); 

		/**********
		 TEXTFIELD
		**********/
		
		//Cr�ation des JtextField pour rentrer les coordonn�es 
		ligne = new JTextField("la ligne", 8) ;
		colonne = new JTextField("la colonne", 8) ;
		ligne.setVisible(false) ;
		colonne.setVisible(false) ;
		

		
		//Pour placer un bateau soit en Vertical, soit en Horizontal
		coordonnee = new JComboBox<String>() ;
		coordonnee.setEditable(false) ;
		coordonnee.addItem("1. Longeur") ;
		coordonnee.addItem("2. Hauteur") ;
		coordonnee.setVisible(false) ;
		

		//1er bouton valider
		//Il sert � valider les placements des b�teaux
		
		valider = new JButton("Valider") ;
		valider.setVisible(false) ;
		valider.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Stock en m�moire les entr�es des lignes et colonnes
				lig = Integer.parseInt(ligne.getText()) ;
				col = Integer.parseInt(colonne.getText()) ;
				
				//G�re la JComboBox
				String test =(String)coordonnee.getSelectedItem() ;
				char a = test.charAt(0) ;
				test = a + "";
				coo = Integer.parseInt(test) ;;	
			
				//Place le b�teau
				met.Bateau(grille, nbre, lig, col, coo) ;
				//Affiche � nouveau le b�teau
				met.FirstPrint(grille, textArea) ;
				
				//Cache les zone de textes et boutons
				valider.setVisible(false); 
				ligne.setVisible(false) ;
				colonne.setVisible(false) ;
				coordonnee.setVisible(false) ;
				labelB.setVisible(false) ;
			}
		}) ; 
		
		
		//2�me bouton valider
		
		validerB = new JButton("Valider") ;
		validerB.setVisible(false) ;
		validerB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Stock en m�moire les entr�es des lignes et colonnes
				lig = Integer.parseInt(ligne.getText()) ;
				col = Integer.parseInt(colonne.getText()) ;
				
				//Entr�e utilisateur lors de la partie
				//Touch� ou � c�t�, suivant le cas
				if (grilleAdver[lig][col] == "  ?  " || grilleAdver[lig][col] == "  ?  \n")
					{
					grilleCachee[lig][col] = "O" ;	
					}	
				else if (grilleAdver[lig][col] == "\04")
						{
						grilleCachee[lig][col] = "X" ;
						toucheA = toucheA + 1 ;
						}
				
				//R�affice la grille de l'adversaire		
				met.FirstPrint(grilleCachee, textArea2) ;
				
			
				//R�affiche la grille de jeu de l'utilisateur
				met.FirstPrint(grille, textArea) ;
				
				
				//G�re le moment ou l'utilisateur gagne ou perd
				if (toucheA == 17) 
					{
					labelVous.setVisible(true) ;
					ligne.setVisible(false) ;
					colonne.setVisible(false) ;
					validerB.setVisible(false) ;
					labelB.setVisible(false) ;
					}
				if (touche == 17)
					{
					labeladver.setVisible(true) ;
					ligne.setVisible(false) ;
					colonne.setVisible(false) ;
					validerB.setVisible(false) ;
					labelB.setVisible(false) ;
					}	
			}
		}) ; 		
		
		
		
		//Les Box g�rent l'affichage
	
		Box hbox1 = Box.createHorizontalBox() ;
		Box hbox2 = Box.createHorizontalBox() ;
		Box hbox3 = Box.createHorizontalBox() ;
		Box hbox4 = Box.createHorizontalBox() ;
		
		Box.createVerticalBox();
		Box vbox2 = Box.createVerticalBox() ;
		hbox1.add(lblVotreFlotte) ;
		
		label_1 = new JLabel("                                                                        ");
		hbox1.add(label_1);
		hbox1.add(labelA) ;
		hbox2.add(scrollPane) ;
		hbox2.add(Box.createHorizontalStrut(5)) ;
		hbox2.add(scrollPane2) ;
		hbox2.add(scrollPane3) ;
		hbox3.add(ligne) ;
		hbox3.add(colonne) ;
		hbox3.add(coordonnee) ;
		hbox3.add(valider) ;
		hbox3.add(validerB) ;



		hbox4.add(labelB) ;
		hbox4.add(labelVous) ;
		hbox4.add(labeladver) ;
		

		vbox2.add(hbox4) ;
		vbox2.add(Box.createVerticalStrut(5)) ;
		vbox2.add(hbox3) ;
	
		contentPane.add(hbox1, BorderLayout.NORTH) ;
		contentPane.add(hbox2, BorderLayout.CENTER) ;
		contentPane.add(vbox2, BorderLayout.SOUTH) ;
	}	
	
	
	
}