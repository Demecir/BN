package com.iut.bataille;

import java.io.* ;
import javax.swing.* ;
import java.awt.*; 
import java.awt.event.* ;

public class Bataille
{

	public static void main(String [] args)
	{
		Fenetre frame = new Fenetre() ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.show() ;	
	}	
}

class Fenetre extends JFrame
{
	//Cr�ation des variables et initialisation
	private final int WIDTH = 520 ;
	private final int HEIGHT = 340 ;
	private int lig, col, coo, nbre ;
    int touche = 0 ;
    int toucheA = 0 ; 
    int colo = 0 ;
    int ln = 0 ; 
    int continuer = 0 ;
	int choix = 0 ;
	int ok = 0 ;	
	String grille[][] = new String[11][11] ;
  	String grilleOrdi[][] = new String[11][11] ;
   	String grilleCachee[][] = new String[11][11] ;	
   	
   	
	//Cr�ation des composants graphiques
	public JButton valider, validerB, validerC, jouer, sortir ;
	private JLabel label, labelA, labelB, labelVous, labelOrdi ;
	private JTextArea textArea, textArea2, textAreaRegle ;
	private JScrollPane scrollPane, scrollPane2, scrollPane3 ;
	private JTextField ligne, colonne ;
	private JCheckBoxMenuItem bat5, bat4, bat3a, bat3b, bat2, batOrdi ;	
	private JComboBox coordonnee ;
	private JMenuBar menuBar ;
	private JMenu bateau, menu, apropos;
	private JMenu commencer ;
	private JMenuItem propos ;
	private JRadioButtonMenuItem difficile, facile ;
	 
	//Cr�ation des constructeurs 
   	Methode met = new Methode() ;	
	Quitter quit = new Quitter() ;
	Propos frame2 = new Propos() ;


	public Fenetre()
	{
		
		/*********************
		 Option de la fen�tre
		*********************/
		
		//Cr�� un objet outil et lui affecte ses caract�ristiques
		//Toolkit kit = Toolkit.getDefaultToolkit() ;
		//Cr�� un outil dimension et lui affecte les dimensions actuelles de l'�cran
		//Dimension screenSize = kit.getScreenSize() ;
		//Donne la valeur de la hauteur de l'�cran
		//int screenHeight = screenSize.height ;
		//Donne la valeur de la longueur de l'�cran
		//int screenWidth = screenSize.width ;
		
		
		//Titre de la fen�tre
		setTitle("Bataille navale") ;
		//Dimension de la fen�tre
		setSize(WIDTH, HEIGHT) ;
		//La taille de la fen�tre n'est pas modifiable	
		setResizable(false) ;
		//Emplacement centr� sur l'�cran, en fonction de la r�solution de l'�cran
		//setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2) ;
 		//Place une image au sommet de la fen�tre
 		//Image img = kit.getImage("bataille.gif") ;
		//setIconImage(img) ;

		/***********************************
		 INITIALISATION DES GRILLES DE JEUX
		***********************************/
		
		grille = met.Initialise(grille) ;
		grilleOrdi = met.Initialise(grille) ;
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
		menu.setEnabled(false) ;
		
		bateau = new JMenu("Bateau") ;
		menuBar.add(bateau) ;
		bateau.setEnabled(false) ;
		
		apropos = new JMenu("?") ;
		menuBar.add(apropos) ;
		apropos.setEnabled(false) ;
		
		propos = new JMenuItem("A propos") ;
		apropos.add(propos) ;
		propos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK)) ;

		propos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
			//Appelle la fen�tre de la class apropos
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
			frame2.show() ;	
			}
		}) ;		
		
		
		//3a. Sous-Options du Menu FICHIER
		JMenuItem nouvelle = new JMenuItem("Nouvelle partie") ;
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
				grilleOrdi = met.Initialise(new String[11][11]) ;
				grilleCachee = met.Initialise(new String[11][11]) ;
				//Raffiche les nouvelles grilles de jeux
				met.FirstPrint(grilleCachee, textArea2) ;
				met.FirstPrint(grille, textArea) ;
				//setSelected � la JCheckBoxMenuItem sa valeur de d�part, sans le v dans la
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
				batOrdi.setSelected(false) ;
				batOrdi.setEnabled(true) ;
				difficile.setSelected(false) ;
				facile.setSelected(false) ;
				labelVous.setVisible(false) ;
				labelOrdi.setVisible(false) ;
			}
		}) ;
		
		
		commencer = new JMenu("Commencer la partie") ;
		menu.add(commencer) ;
		commencer.setEnabled(false) ;
				
		
		facile = new JRadioButtonMenuItem("Niveau facile") ;
		commencer.add(facile) ;
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
				difficile.setSelected(false) ;
				facile.setEnabled(false) ;
				difficile.setEnabled(false) ; 
		
			}
		}) ;		
			
		
		difficile = new JRadioButtonMenuItem("Niveau difficile") ;
		commencer.add(difficile) ;
		difficile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//setVisible affiche les Boutons et JTextField pour commencer la partie en 
				//mode difficile
				ligne.setVisible(true) ;
				colonne.setVisible(true) ;
				valider.setVisible(false) ;
				validerB.setVisible(false) ;
				validerC.setVisible(true) ;
				labelB.setVisible(true) ;
				ligne.setText("la ligne") ;
				colonne.setText("la colonne") ;	
				facile.setSelected(false) ;
				facile.setEnabled(false) ;
				difficile.setEnabled(false) ;
			}
		}) ;


		JMenuItem quitter = new JMenuItem("Quitter") ;
		menu.add(quitter) ;
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK)) ;
		quitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				quit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
				quit.show() ;	
			}
		}) ;
	
		
		//3b. Sous-Options du Menu BATEAU
		bat5 = new JCheckBoxMenuItem("Bateau � 5 cases") ;
		bateau.add(bat5) ;
		bat5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement du bateau � 5 cases
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
				
		bat4 = new JCheckBoxMenuItem("Bateau � 4 cases") ;
		bateau.add(bat4) ;
		bat4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement du bateau � 4 cases
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

		
		bat3a = new JCheckBoxMenuItem("Bateau � 3 cases (1er)") ;
		bateau.add(bat3a) ;
		bat3a.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement d'un des bateaus � 3 cases
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
	
				
		bat3b = new JCheckBoxMenuItem("Bateau � 3 cases (2eme)") ;
		bateau.add(bat3b) ;
		bat3b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement d'un bateaux � 3 cases
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
		
					
		bat2 = new JCheckBoxMenuItem("Bateau � 2 cases") ;
		bateau.add(bat2) ;
		bat2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement du bateau � 2 cases
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
		
		//Ajoute une barre de s�paration dans le menu bateau, entre le bateau � 2 cases et
		//et les bateaux de l'ordi
		bateau.addSeparator() ;
		
		batOrdi = new JCheckBoxMenuItem("Bateaux de l'Ordi") ;
		bateau.add(batOrdi) ;
		batOrdi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Param�tre les fonctions n�cessaire au placement des bateaux de l'ordi
				batOrdi.setEnabled(false) ;
				commencer.setEnabled(true) ;
				facile.setEnabled(true) ;
				difficile.setEnabled(true) ;
				met.Ordi(grilleOrdi) ;
				ligne.setVisible(false) ;
				colonne.setVisible(false) ;
				valider.setVisible(false) ;
				validerB.setVisible(false) ;
				validerC.setVisible(false); 
			}
		}) ;
	

		/************
		 CONTENTPANE
		************/
		
		//Construction d'un container qui contiendra les boutons, JTextArea etc...	
		Container contentPane = getContentPane() ;

		
		/*******************
		 LABEL, text d'info
		*******************/
		
		//Cr�ation des textes
		label = new JLabel("Vous") ;
		labelA = new JLabel("L'ordinateur") ;
		labelB = new JLabel("Entrer les coordonn�es pour :") ;
		labelVous = new JLabel("VOUS AVEZ GAGNE") ;
		labelOrdi = new JLabel("VOUS AVEZ PERDU") ;
		
		label.setVisible(false) ;
		labelA.setVisible(false);
		labelB.setVisible(false) ;
		labelVous.setVisible(false) ;
		labelOrdi.setVisible(false) ;

		
		/*********
		 TEXTAREA
		*********/

		//Cr�ation des zones de jeux
		textArea = new JTextArea(8, 40) ;
		textArea.setLineWrap(true) ;	
		textArea.setEnabled(false) ;
		textArea2 = new JTextArea(8, 40) ;
		textArea2.setLineWrap(true) ;	
		textArea2.setEnabled(false) ;
	 	
	 	//Cr�ation de la zone pour l'affichage des r�gles du jeu
		textAreaRegle = new JTextArea(8, 40) ;
		textAreaRegle.setLineWrap(true) ;
		textAreaRegle.append("============"+ "\n" + "Bataille navale" + "\n"+ "============") ;
		textAreaRegle.append("\n" + "\n" + "Ce jeu n'est autre que le classic de la bataille navale. Les r�gles du jeu restent les " + "\n" + "m�mes.") ;
		textAreaRegle.append(" Cependant, il est important de savoir que ce jeu ne g�re pas encore les entr�es utilisateurs, ") ;
		textAreaRegle.append("donc, par cons�quent, il vous faudra �tre attentif afin d'entrer les donn�es de " + "\n" + "fa�on correct") ;
		textAreaRegle.append("\n\n" + "Le jeu comporte deux niveaux, un facile et un difficile" );
		textAreaRegle.append("\n\n" + "Pour commencer une partie, faites: Fichier, Nouvelle partie. Ensuite, Bateau et placer " + "\n" + "vos b�teaux comme vous le d�sirez, peu importe l'ordre. ") ;
		textAreaRegle.append("N'oubliez pas que le programmene g�re pas les erreurs. Une fois que vous aurez fait placer les b�teaux de l'ordinateur, " + "\n" + "vous pourrez ") ;
		textAreaRegle.append("choisir le niveau de jeu en faisant : Commencer la partie et en choisissant " + "\n" + "votre niveau") ;
		textAreaRegle.append(". Ce n'est qu'une fois le choix fait que la partie d�butera") ;
		textAreaRegle.append("\n\n" + "Maintenant que vous savez comment faire, c'est � vous de jouer") ; 
		textAreaRegle.append("\n\n" + "Bon jeu, Matthieu") ; 
		
		scrollPane = new JScrollPane(textArea) ;
		scrollPane2 = new JScrollPane(textArea2) ;
		scrollPane3 = new JScrollPane(textAreaRegle) ;
		scrollPane.setVisible(false) ;
		scrollPane2.setVisible(false) ;

				
		met.FirstPrint(grille, textArea) ;
		met.FirstPrint(grilleOrdi, textArea2); 

		/**********
		 TEXTFIELD
		**********/
		
		//Cr�ation des JTextField pour l'entr�e des utilisateurs pour le placement des 
		//b�teaux mais aussi pour jouer les coups lors de la partie
		ligne = new JTextField("la ligne", 8) ;
		colonne = new JTextField("la colonne", 8) ;
		ligne.setVisible(false) ;
		colonne.setVisible(false) ;
		
		/*************************
		 BOUTONS JOUER ET QUITTER
		*************************/
		
		//Ce sont les boutons au tout d�but, lorsque les r�gles du jeu s'affiche
		
		//le bouton jouer pour commencer le jeu
		jouer = new JButton("Jouer") ;
		jouer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Affiche les composants de d�part pour commencer le jeu
				scrollPane.setVisible(true) ;
				scrollPane2.setVisible(true) ;
				scrollPane3.setVisible(false) ;
				menu.setEnabled(true) ;
				apropos.setEnabled(true) ;
				jouer.setVisible(false) ;
				sortir.setVisible(false) ;
				label.setVisible(true) ;
				labelA.setVisible(true) ;
			}
		}) ;
		
		//le bouton quitter pour sortir du programme
		sortir = new JButton("Quitter") ;
		sortir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				quit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
				quit.show() ;	
			}
		}) ;
		
		
		/****************************
		 COMBOBOX, pour la coordonee
		****************************/
		
		//Cr�ation d'une JComboBox pour choisir la longeur ou la largeur lors du placement
		//des b�teaux
		coordonnee = new JComboBox() ;
		coordonnee.setEditable(false) ;
		coordonnee.addItem("1. Longeur") ;
		coordonnee.addItem("2. Hauteur") ;
		coordonnee.setVisible(false) ;
		
		/***************
		 BOUTON VALIDER	
		***************/
		
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
		
		
		
		/***************************************
		 BOUTON VALIDERB pour jeu niveau facile	
		***************************************/
		
		//2�me bouton valider
		//Il sert lors du jeu en mode facile
		
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
				if (grilleOrdi[lig][col] == "*")
					{
					grilleCachee[lig][col] = " " ;	
					}	
				else if (grilleOrdi[lig][col] == "\04")
						{
						grilleCachee[lig][col] = "X" ;
						toucheA = toucheA + 1 ;
						}
				
				//R�affice la grille de l'ordinateur			
				met.FirstPrint(grilleCachee, textArea2) ;
				
				
				//Jeu de l'ordinateur
				do
					{
					//Appelle la fonction d'entr�e de coordonn�e pour la ligne de l'ordi
					lig = (int)(Math.random() * (10) + 1) ;
					//Puis la colonne
					col = (int)(Math.random() * (10) + 1) ;	
					}					
				while(grille[lig][col] == " " || grille[lig][col] == "X") ;
	
				//Touch� ou � c�t�, suivant le cas
				if (grille[lig][col] == "*")
					{
					grille[lig][col] = " " ;	
					}	
				else if (grille[lig][col] == "\04")
						{
						grille[lig][col] = "X" ;
						touche = touche + 1 ;
						}
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
					labelOrdi.setVisible(true) ;
					ligne.setVisible(false) ;
					colonne.setVisible(false) ;
					validerB.setVisible(false) ;
					labelB.setVisible(false) ;
					}	
			}
		}) ; 		
		
		/******************************************
		 BOUTON VALIDERC pour jeu niveau difficile	
		*******************************************/
		
		//2�me bouton valider
		//Il sert lors du jeu en mode difficile
		
		validerC = new JButton("Valider") ;
		validerC.setVisible(false) ;
		validerC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Stock en m�moire les entr�es des lignes et colonnes
				lig = Integer.parseInt(ligne.getText()) ;
				col = Integer.parseInt(colonne.getText()) ;

				//Entr�e utilisateur lors de la partie
				//Touch� ou � c�t�, suivant le cas						
				if (grilleOrdi[lig][col] == "*")
					{
					grilleCachee[lig][col] = " " ;	
					}	
				else if (grilleOrdi[lig][col] == "\04")
						{
						grilleCachee[lig][col] = "X" ;
						toucheA = toucheA + 1 ;
						}
				//R�affiche la grille de l'utilisateur			
				met.FirstPrint(grilleCachee, textArea2) ;
				
					
				//Jeu de l'ordinateur
				if(continuer == 0) 
					{
					do
						{
						lig = (int)(Math.random() * (10) + 1) ;
						col = (int)(Math.random() * (10) + 1) ;	
						}					
					while(grille[lig][col] == " " || grille[lig][col] == "X") ;
				
						
					if (grille[lig][col] == "*")
						{
						grille[lig][col] = " " ;
						continuer = 0 ;	
						}	
					else if (grille[lig][col] == "\04")
							{
							grille[lig][col] = "X" ;
							touche = touche + 1 ;
							continuer = 1 ;
							ln = lig ;
							colo = col ;
							choix = 1 ;
							}
					}
				else
					{
					if (choix == 1 && ln - 1 == 0)
						{
						choix = choix + 1 ;	
						ln = lig ;
						colo = col ;
						continuer = 1; 
						} 
					if (choix == 2 && colo - 1 == 0)
						{
						choix = choix + 1 ;	
						colo = col ;
						ln = lig ;
						continuer = 1 ;
						}
					if(choix == 3 && colo + 1 == 11)
						{
						choix = choix + 1 ;	
						colo = col ;
						ln = lig ;
						continuer = 1 ;	
						}
					if(choix == 4 && ln + 1 == 11)
						{
						choix = 0 ;	
						colo = col ;
						ln = lig ;
						continuer = 0 ;	
						}	 			
						
						switch (choix) 
							{
								case 1 :
									{
									if (grille[ln-1][colo] != "\04" && grille[ln-1][colo] != "X")
									{
									grille[ln-1][colo] = " " ;
									choix = choix + 1 ;
									ln = lig ;
									colo = col ;	
									continuer = 1;								
									}	
								else if (grille[ln-1][colo] == "\04") 
										{
										grille[ln-1][colo] = "X" ;
										touche = touche + 1 ;
										ln = ln - 1 ;
										continuer = 1;	
										}
									else if(grille[ln-1][colo] == "X")
										{
										ln = ln - 1 ;	
										}
									}
								break ;
								
								case 2 :
									{
									if (grille[ln][colo-1] != "\04" && grille[ln][colo-1] != "X")
										{
										grille[ln][colo-1] = " " ;
										choix = choix + 1 ;	
										ln = lig ;
										colo = col ;	
										continuer = 1;						
										}	
									else if (grille[ln][colo-1] == "\04")
											{
											grille[ln][colo-1] = "X" ;
											touche = touche + 1 ;
											colo = colo - 1 ;
											continuer = 1;	
											}
										else if(grille[ln][colo-1] == "X")
												{
												colo = colo - 1 ;		
												}	
										}
								break ;	
								
								
								case 3 :
									{
									if (grille[ln][colo+1] != "\04" && grille[ln][colo+1] != "X")
										{
										grille[ln][colo+1] = " " ;
										choix = choix + 1 ;	
										ln = lig ;
										colo = col ;
										continuer = 1;									
										}	
									else if (grille[ln][colo+1] == "\04")
											{
											grille[ln][colo+1] = "X" ;
											touche = touche + 1 ;
											colo = colo+1 ;
											continuer = 1;	
											}
										else if(grille[ln][colo+1] == "X")
												{
												colo = colo + 1 ;		
												}
									}
								break ;	
		
		
								case 4 :
									{
									if (grille[ln+1][colo] != "\04" && grille[ln+1][colo] != "X")
										{
										grille[ln+1][colo] = " " ;
										choix = 0 ;
										ok = 0 ;
										ln = lig ;
										colo = col ;	
										continuer = 0 ;								
										}	
									else if (grille[ln+1][colo] == "\04")
											{
											grille[ln+1][colo] = "X" ;
											touche = touche + 1 ;
											ln = ln + 1 ;
											continuer = 1;	
											}
										else if(grille[ln+1][colo] == "X")
											{
											ln = ln + 1;	
											}
									}
								break ;	 
		
										
							}
						}
						
			//R�affiche la grille de l'utilisateur	 		
			met.FirstPrint(grille, textArea) ;


			//G�re le moment ou l'utilisateur gagne ou perd
			if (toucheA == 17) 
				{
				labelVous.setVisible(true) ;
				ligne.setVisible(false) ;
				colonne.setVisible(false) ;
				validerC.setVisible(false) ;
				labelB.setVisible(false) ;
				}
			if (touche == 17)
				{
				labelOrdi.setVisible(true) ;
				ligne.setVisible(false) ;
				colonne.setVisible(false) ;
				validerC.setVisible(false) ;
				labelB.setVisible(false) ;
				}

			}
		}) ; 		
		
		
		
		
			
		
		/****
		 BOX
		****/
		
		//Les Box sont cr��es afin d'ajouter les composants et les centr�s de facon
		//horizontal ou vertical
		
		Box hbox1 = Box.createHorizontalBox() ;
		Box hbox2 = Box.createHorizontalBox() ;
		Box hbox3 = Box.createHorizontalBox() ;
		Box hbox4 = Box.createHorizontalBox() ;
		
		Box vbox = Box.createVerticalBox() ;
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
		hbox3.add(validerC) ;
		hbox3.add(jouer) ;
		hbox3.add(sortir) ;
		hbox4.add(labelB) ;
		hbox4.add(labelVous) ;
		hbox4.add(labelOrdi) ;
		

		vbox2.add(hbox4) ;
		vbox2.add(Box.createVerticalStrut(5)) ;
		vbox2.add(hbox3) ;
		
		//On ajoute les Box au container
		contentPane.add(hbox1, BorderLayout.NORTH) ;
		contentPane.add(hbox2, BorderLayout.CENTER) ;
		contentPane.add(vbox2, BorderLayout.SOUTH) ;
	}	
}


//Fen�tre A propos du jeu
class Propos extends JFrame
{
	//Initialisation des variables
	private final int WIDTH = 300 ;
	private final int HEIGHT = 380 ;	
	JTextArea textArea ;
	JScrollPane scrollPane;
	JButton bouton ;
	
	//Cr�ation d'un constructeur quitter

	
	public Propos()
	{
		//Cr�� un objet outil et lui affecte ses caract�ristiques
		Toolkit kit = Toolkit.getDefaultToolkit() ;
		//Cr�� un outil dimension et lui affecte les dimensions actuelles de l'�cran
		Dimension screenSize = kit.getScreenSize() ;
		//Donne la valeur de la hauteur de l'�cran
		int screenHeight = screenSize.height ;
		//Donne la valeur de la longueur de l'�cran
		int screenWidth = screenSize.width ;
		
		//Titre de la fen�tre
		setTitle("A propos") ;
		//Dimension de la fen�tre
		setSize(WIDTH, HEIGHT) ;
		//La taille de la fen�tre n'est pas modifiable		
		setResizable(false) ;
		//Emplacement centr� sur l'�cran, en fonction de la r�solution de l'�cran
		setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2) ;
 		//Place une image au sommet de la fen�tre
 		Image img = kit.getImage("bataille.gif") ;
		setIconImage(img) ;		

		//Cr�ation du bouton retour au jeu
		bouton = new JButton("Retour au jeu") ;
		bouton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
			 setVisible(false) ;	
			}
		}) ; 
		
		//Construction d'un container qui contiendra les boutons, JTextArea etc...
		Container contentPane = getContentPane() ;
		
		//Cr�ation des Box
		Box vbox = Box.createVerticalBox() ;
		Box hbox = Box.createHorizontalBox() ;
		
		
		//On ajoute les composants aux Boxs
		hbox.add(bouton) ;
		vbox.add(Box.createVerticalStrut(20)) ;
		vbox.add(hbox) ;
		vbox.add(Box.createVerticalStrut(20)) ;
		
		//On ajoute les boxs au container
		//En cr�ant le text A propos
		contentPane.add(new JLabel("<html><h3>&nbsp;Bataille navale</h3><hr>"+
				"<p>&nbsp;GRAPHIQUE<p><p>&nbsp;Version : 1.1 Evaluation/Test"
				+ "<p><p>&nbsp;Auteur : Zufferey Matthieu"
				+ "<p><p>&nbsp;Date : 08.08.2002"
				+ "<p><p><p><b>&nbsp;Copyright 2002 Zufferey Matthieu</html>"), BorderLayout.NORTH) ;
		contentPane.add(vbox, BorderLayout.SOUTH) ;

	}	
}

//Fen�tre du r�sultat de la partie
class Quitter extends JFrame
{
	//Initialisation des variables
	private final int WIDTH = 300 ;
	private final int HEIGHT = 110 ;
	
	//Cr�ation des composants graphiques	
	JTextArea textArea ;
	JScrollPane scrollPane;
	JButton oui, non ;
	JLabel text ;
	
	public Quitter()
	{
		//Cr�� un objet outil et lui affecte ses caract�ristiques
		//Toolkit kit = Toolkit.getDefaultToolkit() ;
		//Cr�� un outil dimension et lui affecte les dimensions actuelles de l'�cran
		//Dimension screenSize = kit.getScreenSize() ;
		//Donne la valeur de la hauteur de l'�cran
		//int screenHeight = screenSize.height ;
		//Donne la valeur de la longueur de l'�cran
		//int screenWidth = screenSize.width ;
 		//Place une image au sommet de la fen�tre
 		//Image img = kit.getImage("bataille.gif") ;
		//setIconImage(img) ;
		
		//Titre de la fen�tre
		setTitle("Bataille navale") ;
		//Dimension de la fen�tre
		setSize(WIDTH, HEIGHT) ;
		//La taille de la fen�tre n'est pas modifiable		
		setResizable(false) ;
		//Emplacement centr� sur l'�cran, en fonction de la r�solution de l'�cran
		//setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2) ;
		
		//Le text qui est affichez au centre de la fen�tre
		text = new JLabel("Souhaitez-vous r�ellement quitter le jeu ?") ;
		
		//Initialisation du bouton oui
		oui = new JButton("oui") ;
		//Action du bouton oui
		oui.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0) ;
			}
		}) ; 
		
		//Initialisation du bouton non
		non = new JButton("non") ;
		//Action du bouton non
		non.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false) ;
			}
		}) ; 
		
		//Cr�ation d'une premi�re boite horizontal contenant le text
		Box hbox = Box.createHorizontalBox() ;
		hbox.add(text) ;
		
		//Cr�ation d'une deuxi�me boite horizontal contenant les boutons
		Box hbox2 = Box.createHorizontalBox() ;
		hbox2.add(oui) ;
		hbox2.add(Box.createHorizontalStrut(50)) ;
		hbox2.add(non) ;
		
		//Cr�ation d'une bo�te verticale contenant les deux aures boites
		Box vbox = Box.createVerticalBox() ;
		vbox.add(Box.createVerticalStrut(20)) ;
		vbox.add(hbox) ;
		vbox.add(Box.createVerticalStrut(5)) ;
		vbox.add(hbox2) ;

		//Cr�ation d'un container
		Container contentPane = getContentPane() ;
		
		//On ajoute les box au container
		contentPane.add(vbox, BorderLayout.CENTER) ;
		
	}
}