/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partieBelotte;

import java.util.Random;

/**
 *
 * @author david
 */
public class Joueur {
    private String nomDuJoueur;
    private Carte mainDuJoueur[] = new Carte[8]; 
    private  int numeroJoueur;
    private int score = 0;
    private int numeroEquipe;
    private static int incrementJoueur = 0;
    
    /*Constructeur 3 : On a toutes les infos sur le joueur*/
    protected Joueur(String pNomDuJoueur, int pNumeroJoueur, int pScore)
    {
        numeroJoueur = pNumeroJoueur;
        numeroEquipe = pNumeroJoueur%2;
        nomDuJoueur = pNomDuJoueur;
        score = pScore;
    }

    /*Constructeur 2 : On a le nom et le numéro du joueur*/
    protected Joueur(String pNomDuJoueur, int pNumeroJoueur)
    {
        nomDuJoueur = pNomDuJoueur;
        numeroJoueur = pNumeroJoueur;
        numeroEquipe = numeroJoueur%2;
    }
    
    /*Constructeur 1: on n'a que le nom du joueur*/
    protected Joueur(String pNomDuJoueur)
    {
        this.incrementJoueur++;
        nomDuJoueur = pNomDuJoueur;
        numeroJoueur = incrementJoueur;
    }
    
    protected void tirageSortEquipes(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
        int i=0;
        int j=0;
        Random alea = new Random();
        j1.numeroEquipe = alea.nextInt(2) + 1;
        j2.numeroEquipe = alea.nextInt(2) + 1;
        if(j2.numeroEquipe == j1.numeroEquipe)
        {
            // Petite astuce tirée des hannetons pour transformer 1 en 2 et 2 en 1
            j3.numeroEquipe = (j1.numeroEquipe *3)%2 + 1;
            j4.numeroEquipe = (j1.numeroEquipe *3)%2 + 1;
        }
        else
        {
            j3.numeroEquipe = alea.nextInt(2) + 1;
            j4.numeroEquipe = (j3.numeroEquipe *3)%2 + 1;
        }
    }
    
    public int changerScore(int pScore)
    {
        score = score + pScore;
        return score;
    }
    
    public int donnerScore()
    {
        return score;
    }
    
    public int donnerNumeroJoueur()
    {
        return numeroJoueur;
    }
    
    public String donnerNomJoueur()
    {
        return nomDuJoueur;
    }
    
    public int donnerNumeroEquipe()
    {
        return numeroEquipe;
    }
    
    protected void ajouterCarte(Carte carteAjout, int rang)
    {
        mainDuJoueur[rang] = carteAjout;
    }
    
    protected Carte jeterCarte(int rang)
    {
        Carte carteTransit = mainDuJoueur[rang];
        mainDuJoueur[rang] = null;
        return carteTransit;
    }
    
    protected void afficherPaquet()
    {
        for(int i=1;i<=8;i++)
        {
            if(mainDuJoueur[i-1]!=null)
            {
                System.out.println("Carte n°"+i+": "
                        +mainDuJoueur[i-1].getFigureNom()+" de "
                        +mainDuJoueur[i-1].getCouleur());
            }
        }
    }
    
    protected void triPaquetCartes(Couleur atout)
    {
        //1ere étape : On trie les cartes par couleurs, par ordre alphabétique
        Carte cartePivot = new Carte(CarteValeur.sept,Couleur.Coeur);
        int rangPivot;
        int i;
        //On va comparer les 7 premières cartes avec chacunes des suivantes
        for(i=0; i<7; i++)
        {
            rangPivot = -1;
            for(int j=i+1; j<8; j++)
            {
                //On compare les cartes 2 à 2
                if(mainDuJoueur[i].getCouleur().compareTo(mainDuJoueur[j].getCouleur())>0)
                {
                    rangPivot = j;
                    cartePivot = mainDuJoueur[j];
                }
            }
            if(rangPivot>0)
            {
                mainDuJoueur[rangPivot] = mainDuJoueur[i];
                mainDuJoueur[i] = cartePivot;
                i--;
            }
        }
        //2e étape, on trie par valeur
        for(int j=0; j<7; j++)
        {
            i=1;
            while((mainDuJoueur[j].getCouleur().matches(mainDuJoueur[j+i].getCouleur()))
                    &&(i+j+1<mainDuJoueur.length))
            {
                if(mainDuJoueur[j].getCouleur().matches(atout.getCouleur()))
                {
                    if(mainDuJoueur[j+i].getValeurAtout()>mainDuJoueur[j].getValeurAtout())
                    {
                        cartePivot = mainDuJoueur[j];
                        mainDuJoueur[j] = mainDuJoueur[j+i];
                        mainDuJoueur[j+i] = cartePivot;
                    }
                }
                if(mainDuJoueur[j].getCouleur().matches(atout.getCouleur())==false)
                {
                    if(mainDuJoueur[j+i].getValeurNormale()>mainDuJoueur[j].getValeurNormale())
                    {
                        cartePivot = mainDuJoueur[j];
                        mainDuJoueur[j] = mainDuJoueur[j+i];
                        mainDuJoueur[j+i] = cartePivot;
                    }   
                }
                i++;
            }
        }
    }
    
    protected int nombreCartesDansPaquet()
    {
        int nombreCartes = 0;
        for(int i=0;i<8;i++)
        {
            if(mainDuJoueur[i]!=null)
            {
                nombreCartes++;
            }
        }
        return nombreCartes;
    }
    
    protected boolean estUneCarte(int rang)
    {
        if(mainDuJoueur[rang]!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
