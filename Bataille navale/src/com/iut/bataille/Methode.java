package com.iut.bataille;


import javax.swing.* ;
import java.io.* ;

public class Methode 
	{
		
	
	public void FirstPrint(String a [][], JTextArea textArea) // Affiche la grille quand vous placez les bateaux
		{
		textArea.setTabSize(2) ;
		textArea.setText("") ;
		for (int i = 0 ; i < 11 ; i++)
			{
				for (int j = 0 ; j < 11 ; j++)
					{
					if (j == 0 && i == 0)
						{
						textArea.append("\t") ;
						}
					else if(j == 0 && i == 10)
						{
							textArea.append(a[i][j] + "\t") ;
							}
						 else if(j == 0 && i > 0)
								{
								textArea.append(a[i][j] + "\t") ;	
								}
							  else
				 				{
								textArea.append(a[i][j] + "\t") ;					
								}
					}
		
			textArea.append("\n") ;	
			} 
	
		textArea.append("\n") ;
		}
		
			
	
	public String [][] Bateau(String [][] grille, int nbre, int ligne, int colonne, int coordonnee) 
		{
			
		int erreur = 0 ;

		int i = 0 ;


		switch (coordonnee)
			{
				case 1 :
					{
						for(int j = colonne ; j < colonne + nbre ; j++)
							{
							grille[ligne][j] = "\04" ;	
							}
						

					}
				break ;
			
				case 2 : 
					{
						
						for(int j = ligne ; j < ligne + nbre ; j++)
							{
							grille[j][colonne] = "\04" ;	
							}
							
					}
				break ;		
			}
		return grille;
		}
		
	
	public void BateauOrdi(String grille [][], int nbre)	
		{
		
		int erreur = 0 ;
		
		do
		{
			int i = 0 ;
			int coordonnee = (int)(Math.random() * (2) + 1) ;
			int ligne = (int)(Math.random() * (10) + 1) ;
			int colonne = (int)(Math.random() * (10) + 1) ;
		
			erreur = 1 ;
		
			switch (coordonnee)
				{
					case 1 :
						{
						if((colonne + nbre) > 11)
							{
							erreur = 0 ;	
							}
						else
							{
							do
								{
								i = colonne ;
								
								for (int j = colonne ; j < (colonne + nbre) ; j++)
									{
									if(grille[ligne][j] == "\04")
										{
										erreur = 0 ;
										i = colonne + nbre ;
										}	
									}
								
								if (erreur != 0)
									{
									for (int j = colonne ; j < (colonne + nbre) ; j++)
										{
										grille[ligne][j] = "\04" ;
										
										}
									i = colonne + nbre ;		
									}	
								}
							while(i < colonne + nbre );
							}	
						}
					break ;
				
					case 2 : 
						{
						if((ligne + nbre) > 11)
							{
							erreur = 0 ;	
							}
						else
							{
							do
								{
								i = ligne ;
								
								for (int j = ligne ; j < (ligne + nbre) ; j++)
									{
									if(grille[j][colonne] == "\04")
										{
										erreur = 0 ;
										i = ligne + nbre ;
										}	
									}
								
								if (erreur != 0)
									{
									for (int j = ligne ; j < (ligne + nbre) ; j++)
										{
										grille[j][colonne] = "\04" ;
										
										}
									i = ligne + nbre ;		
									}	
								}
							while(i < ligne + nbre );
							}	
						}
					break ;		
				}
		}
		while(erreur != 1) ;
		
		
		}

	
	
	
	
	public void Ordi(String [][] grilleOrdi) 
		{
				
		Methode fonction = new Methode() ;
		
		//Choix des coordonnées pour le bateau à 5 cases	
		fonction.BateauOrdi(grilleOrdi, 5) ;
		//Choix des coordonnées pour le bateau à 4 cases	
		fonction.BateauOrdi(grilleOrdi, 4) ;
		//Choix des coordonnées pour le premier bateau à 3 cases	
		fonction.BateauOrdi(grilleOrdi, 3) ;
		//Choix des coordonnées pour le deuxième bateau à 3 cases	
		fonction.BateauOrdi(grilleOrdi, 3) ;
		//Choix des coordonnées pour le bateau à 2 cases
		fonction.BateauOrdi(grilleOrdi, 2) ;
		
		}
		
	
	
	
	public String [][] Initialise(String [][] a)
		{
		for (int i = 0 ; i < 11 ; i++)
	        {
	        for (int j = 0 ; j < 11 ; j++)
	        	{
	        	a[i][j] = "*" ;

	        	}	
	        }
	     
	    //Puis les nombres                    
		for (int i = 0 ; i < 11 ; i++) 
			{
			a[i][0] = i + "" ;
			a[0][i] = i + "" ;			
			}	
			
		return a ;
		}	
	}