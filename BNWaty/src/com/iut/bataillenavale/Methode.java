package com.iut.bataillenavale;


import javax.swing.* ;

public class Methode 
	{
		
	
	public void FirstPrint(String a [][], JTextArea textArea) // Affiche la grille 
		{
		textArea.setTabSize(2) ;
		textArea.setText(" ") ;
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
	
		textArea.append("") ;
		}
		
			
	
	public String [][] Bateau(String [][] grille, int nbre, int ligne, int colonne, int coordonnee) 
		{
			
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
		
	
	
	
	public String [][] Initialise(String [][] a) // Affiche la table de jeu
		{
		for (int i = 0 ; i < 11 ; i++)
	        {
	        for (int j = 0 ; j < 11 ; j++)
	        	{
	        	if(j==10)
	        	{
	        		a[i][j] = "  *  \n" ;
	        	}
	        	else
	        	a[i][j] = "  *  " ;

	        	}	
	        
	        }
	     
	    //Puis les nombres                    
		for (int i = 0 ; i < 11 ; i++) 
			{
			a[i][0] = i + "  " ;
			a[0][i] = i + "  " ;			
			}	
			
		return a ;
		}	
	

public String [][] Initialisecach(String [][] a) // Affiche la table cible
{
	for (int i = 0 ; i < 11 ; i++)
    {
    for (int j = 0 ; j < 11 ; j++)
    	{
    	if(j==10)
    	{
    		a[i][j] = "  ?  \n" ;
    	}
    	else
    	a[i][j] = "  ?  " ;

    	}	
    
    } 
//Puis les nombres                    
for (int i = 0 ; i < 11 ; i++) 
	{
	a[i][0] = i + "  " ;
	a[0][i] = i + "  " ;			
	}	
	
return a ;
}	
}