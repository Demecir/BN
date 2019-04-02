package com.iut.bataillenavale;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

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
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Bataille {

	JFrame frmBatailleNavale;
	static Bataille window = new Bataille();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frmBatailleNavale.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Bataille() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBatailleNavale = new JFrame();
		frmBatailleNavale.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Utilisateur\\Desktop\\BN.jpg"));
		frmBatailleNavale.setTitle("Bataille Navale");
		frmBatailleNavale.setBounds(50, 50, 1500, 1000);
		frmBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				window.frmBatailleNavale.setVisible(false);
				
				Fenetre frame = new Fenetre() ;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
				frame.show() ;	
				
				
			
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\BN.jpg"));
		btnNewButton.setBackground(UIManager.getColor("TextPane.background"));
		frmBatailleNavale.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}

}


@SuppressWarnings("serial")
class Fenetre extends JFrame
{
	
	//Création des variables et initialisation
	private final int WIDTH = 620 ;
	private final int HEIGHT = 440 ;
	private int lig, col, coo, nbre ;
    int touche = 0 ;
    int toucheA = 0 ; 
    int colo = 0 ;
    int ln = 0 ; 
    int continuer = 0 ;
	int choix = 0 ;
	int ok = 0 ;	
	String grille[][] = new String[11][11] ;
  	String grilleAdver[][] = new String[11][11] ;
   	String grilleCachee[][] = new String[11][11] ;	
   	
   	
	//Création des composants graphiques
	public JButton valider, validerB, validerC, jouer, sortir ;
	private JLabel label, labelA, labelB, labelVous, labeladver ;
	private JTextArea textArea, textArea2, textAreaRegle ;
	private JScrollPane scrollPane, scrollPane2, scrollPane3 ;
	private JTextField ligne, colonne ;
	private JCheckBoxMenuItem bat5, bat4, bat3a, bat3b, bat2;	
	private JComboBox<String> coordonnee ;
	private JMenuBar menuBar ;
	private JMenu bateau, menu;
	private JMenu commencer ;
	//Création des constructeurs 
   	Methode met = new Methode() ;	


	public Fenetre()
	{
		
		/*********************
		 Option de la fenêtre
		*********************/
		
		//Créé un objet outil et lui affecte ses caractéristiques
		Toolkit kit = Toolkit.getDefaultToolkit() ;
		//Créé un outil dimension et lui affecte les dimensions actuelles de l'écran
		Dimension screenSize = kit.getScreenSize() ;
		//Donne la valeur de la hauteur de l'écran
		int screenHeight = screenSize.height ;
		//Donne la valeur de la longueur de l'écran
		int screenWidth = screenSize.width ;
		
		
		//Titre de la fenêtre
		setTitle("Bataille navale") ;
		//Dimension de la fenêtre
		setSize(WIDTH, HEIGHT) ;
		//La taille de la fenêtre n'est pas modifiable	
		setResizable(false) ;
		//Emplacement centré sur l'écran, en fonction de la résolution de l'écran
		setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2) ;
 		//Place une image au sommet de la fenêtre
 		Image img = kit.getImage("C:\\Users\\Utilisateur\\Desktop\\BN.jpg") ;
		setIconImage(img) ;
		
		
	

		/***********************************
		 INITIALISATION DES GRILLES DE JEUX
		***********************************/
		
		grille = met.Initialise(grille) ;
		grilleAdver = met.Initialise(grille) ;
		grilleCachee = met.Initialise(grille) ;

		/*****************
		 CREATION DU MENU
		*****************/

		

			
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
		JMenuItem nouvelle = new JMenuItem("Nouvelle partie") ;
		menu.add(nouvelle) ;
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)) ;
		nouvelle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Réinitialise les variables à 0 ;
				continuer = ln = colo = choix = ok = 0 ;
				commencer.setEnabled(false) ;
				bateau.setEnabled(true) ;
				grille = met.Initialise(new String[11][11]) ;
				grilleAdver = met.Initialise(new String[11][11]) ;
				grilleCachee = met.Initialise(new String[11][11]) ;
				//Raffiche les nouvelles grilles de jeux
				met.FirstPrint(grilleCachee, textArea2) ;
				met.FirstPrint(grille, textArea) ;
				//setSelected à la JCheckBoxMenuItem sa valeur de départ, sans le v dans la
				//case
				bat5.setSelected(false) ;
				//setEnabled affiche la JCheckBoxMenuItem mais en gris clair, pas possible
				//de cliquer dessus
				bat5.setEnabled(true) ;
				bat4.setSelected(false) ;
				bat4.setEnabled(true) ;
				bat3a.setSelected(false) ;
				bat3a.setEnabled(true) ;
				bat3b.setSelected(false) ;
				bat3b.setEnabled(true) ;
				bat2.setSelected(false) ;
				bat2.setEnabled(true) ;
				
				scrollPane.setVisible(true) ;
				scrollPane2.setVisible(true) ;
				scrollPane3.setVisible(false) ;
				menu.setEnabled(true) ;

				label.setVisible(true) ;
				labelA.setVisible(true) ;
				
		


				labelVous.setVisible(false) ;
				labeladver.setVisible(false) ;
			}
		}) ;
		
		
		commencer = new JMenu("Commencer la partie") ;
		menu.add(commencer) ;
		commencer.setEnabled(false) ;
		
		//3b. Sous-Options du Menu BATEAU
		bat5 = new JCheckBoxMenuItem("Bateau à 5 cases") ;
		bateau.add(bat5) ;
		bat5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Paramètre les fonctions nécessaire au placement du bateau à 5 cases
				bat5.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;
				labelB.setVisible(true) ;
				nbre = 5 ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
			}
		}) ;
				
		bat4 = new JCheckBoxMenuItem("Bateau à 4 cases") ;
		bateau.add(bat4) ;
		bat4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Paramètre les fonctions nécessaire au placement du bateau à 4 cases
				nbre = 4 ;
				bat4.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;	
			}
		}) ;

		
		bat3a = new JCheckBoxMenuItem("Bateau à 3 cases (1er)") ;
		bateau.add(bat3a) ;
		bat3a.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Paramètre les fonctions nécessaire au placement d'un des bateaus à 3 cases
				nbre = 3 ;
				bat3a.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
			}
		}) ;
	
				
		bat3b = new JCheckBoxMenuItem("Bateau à 3 cases (2eme)") ;
		bateau.add(bat3b) ;
		bat3b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Paramètre les fonctions nécessaire au placement d'un bateaux à 3 cases
				nbre = 3 ;
				bat3b.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
			}
		}) ;
		
					
		bat2 = new JCheckBoxMenuItem("Bateau à 2 cases") ;
		bateau.add(bat2) ;
		bat2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Paramètre les fonctions nécessaire au placement du bateau à 2 cases
				nbre = 2 ;
				bat2.setEnabled(false) ;
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				coordonnee.setVisible(true) ;
				valider.setVisible(true) ;	
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;
			}
		}) ;
		
		//Ajoute une barre de séparation dans le menu bateau, entre le bateau à 2 cases et
		//et les bateaux de l'ordi
		bateau.addSeparator() ;
		
		
	

		/************
		 CONTENTPANE
		************/
		
		//Construction d'un container qui contiendra les boutons, JTextArea etc...	
		Container contentPane = getContentPane() ;

		
		/*******************
		 LABEL, text d'info
		*******************/
		
		//Création des textes
		label = new JLabel("Vous") ;
		labelA = new JLabel("L'adversaire") ;
		labelB = new JLabel("Entrer les coordonnées pour :") ;
		labelVous = new JLabel("VOUS AVEZ GAGNE") ;
		labeladver = new JLabel("VOUS AVEZ PERDU") ;
		
		label.setVisible(false) ;
		labelA.setVisible(false);
		labelB.setVisible(false) ;
		labelVous.setVisible(false) ;
		labeladver.setVisible(false) ;

		
		/*********
		 TEXTAREA
		*********/

		//Création des zones de jeux
		textArea = new JTextArea(8, 40) ;
		textArea.setLineWrap(true) ;	
		textArea.setEnabled(false) ;

		textArea2 = new JTextArea(8, 40) ;
		textArea2.setLineWrap(true) ;	
		textArea2.setEnabled(false) ;
	 	
	 	//Création de la zone pour l'écran de présentation
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
		
		//Création des JTextField pour l'entrée des utilisateurs pour le placement des 
		//bâteaux mais aussi pour jouer les coups lors de la partie
		ligne = new JTextField("la ligne", 8) ;
		colonne = new JTextField("la colonne", 8) ;
		ligne.setVisible(false) ;
		colonne.setVisible(false) ;
		
		/*************************
		 BOUTONS JOUER ET QUITTER
		*************************/
		
		//Ce sont les boutons au tout début, lorsque les règles du jeu s'affiche
		
		//le bouton jouer pour commencer le jeu

		

		
		
		/****************************
		 COMBOBOX, pour la coordonee
		****************************/
		
		//Création d'une JComboBox pour choisir la longeur ou la largeur lors du placement
		//des bâteaux
		coordonnee = new JComboBox<String>() ;
		coordonnee.setEditable(false) ;
		coordonnee.addItem("1. Longeur") ;
		coordonnee.addItem("2. Hauteur") ;
		coordonnee.setVisible(false) ;
		
		/***************
		 BOUTON VALIDER	
		***************/
		
		//1er bouton valider
		//Il sert à valider les placements des bâteaux
		
		valider = new JButton("Valider") ;
		valider.setVisible(false) ;
		valider.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Stock en mémoire les entrées des lignes et colonnes
				lig = Integer.parseInt(ligne.getText()) ;
				col = Integer.parseInt(colonne.getText()) ;
				
				//Gère la JComboBox
				String test =(String)coordonnee.getSelectedItem() ;
				char a = test.charAt(0) ;
				test = a + "";
				coo = Integer.parseInt(test) ;;	
			
				//Place le bâteau
				met.Bateau(grille, nbre, lig, col, coo) ;
				//Affiche à nouveau le bâteau
				met.FirstPrint(grille, textArea) ;
				
				//Cache les zone de textes et boutons
				valider.setVisible(false); 
				ligne.setVisible(false) ;
				colonne.setVisible(false) ;
				coordonnee.setVisible(false) ;
				labelB.setVisible(false) ;
			}
		}) ; 
		
		
		
		/***************************************
		 BOUTON VALIDERB pour Tirer	
		***************************************/
		
		//2ème bouton valider
		//Il sert lors du jeu en mode facile
		
		validerB = new JButton("Valider") ;
		validerB.setVisible(false) ;
		validerB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Stock en mémoire les entrées des lignes et colonnes
				lig = Integer.parseInt(ligne.getText()) ;
				col = Integer.parseInt(colonne.getText()) ;
				
				//Entrée utilisateur lors de la partie
				//Touché ou à côté, suivant le cas
				if (grilleAdver[lig][col] == "*")
					{
					grilleCachee[lig][col] = " " ;	
					}	
				else if (grilleAdver[lig][col] == "\04")
						{
						grilleCachee[lig][col] = "X" ;
						toucheA = toucheA + 1 ;
						}
				
				//Réaffice la grille de l'ordinateur			
				met.FirstPrint(grilleCachee, textArea2) ;
				
				
			
				
				//Réaffiche la grille de jeu de l'utilisateur
				met.FirstPrint(grille, textArea) ;
				
				
				//Gère le moment ou l'utilisateur gagne ou perd
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
		
		
		/****
		 BOX
		****/
		
		//Les Box sont créées afin d'ajouter les composants et les centrés de facon
		//horizontal ou vertical
		
		Box hbox1 = Box.createHorizontalBox() ;
		Box hbox2 = Box.createHorizontalBox() ;
		Box hbox3 = Box.createHorizontalBox() ;
		Box hbox4 = Box.createHorizontalBox() ;
		
		Box.createVerticalBox();
		Box vbox2 = Box.createVerticalBox() ;
		
		hbox1.add(Box.createHorizontalStrut(110)) ;
		hbox1.add(label) ;
		hbox1.add(Box.createHorizontalStrut(210)) ;
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
		
		//On ajoute les Box au container
		contentPane.add(hbox1, BorderLayout.NORTH) ;
		contentPane.add(hbox2, BorderLayout.CENTER) ;
		contentPane.add(vbox2, BorderLayout.SOUTH) ;
	}	
	
	
	
}

