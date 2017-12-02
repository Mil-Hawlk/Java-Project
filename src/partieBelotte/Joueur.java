package partieBelotte;

import java.util.Random;

/**
 * La classe Joueur comporte toutes les caractéristiques liées au joueur
 * (Nom, cartes en main, numéro de joueur et d'équipe, score)
 * @author pierre
 * @author david
 * @version 1.0
 */
public class Joueur {
    /**
     * Représente le nom du joueur
     */
    private String nomDuJoueur;
    /**
     * Représente la main (= les cartes) du joueur
     */
    private Carte mainDuJoueur[] = new Carte[8]; 
    /**
     * Id du joueur
     */
    private  int numeroJoueur;
    /**
     * Score du joueur
     */
    private int score = 0;
    /**
     * Id de l'équipe correspondant au joueur
     */
    private int numeroEquipe;
    /**
     * Permet de créer l'ID du joueur quand il n'est pas renseigné
     */
    private static int incrementJoueur = 0;

    /**
     * Constructeur 3 : Cas où l'on a toutes les infos sur le joueur
     * @param pNomDuJoueur son nom
     * @param pNumeroJoueur son Id de joueur
     * @param pScore son score
     */
    protected Joueur(String pNomDuJoueur, int pNumeroJoueur, int pScore)
    {
        numeroJoueur = pNumeroJoueur;
        numeroEquipe = pNumeroJoueur%2;
        nomDuJoueur = pNomDuJoueur;
        score = pScore;
    }

    /**
     * Constructeur 2 : Cas ou l'on a le nom et le numéro du joueur
     * @param pNomDuJoueur son nom
     * @param pNumeroJoueur son Id de joueur
     */
    protected Joueur(String pNomDuJoueur, int pNumeroJoueur)
    {
        nomDuJoueur = pNomDuJoueur;
        numeroJoueur = pNumeroJoueur;
        numeroEquipe = numeroJoueur%2;
    }

    /**
     * Constructeur 1: Casou l'on n'a que le nom du joueur
     * @param pNomDuJoueur nom du joueur
     */
    protected Joueur(String pNomDuJoueur)
    {
        this.incrementJoueur++;
        nomDuJoueur = pNomDuJoueur;
        numeroJoueur = incrementJoueur;
    }
    
    /**
     * Cette méthode permet de choisir les équipes de façon aléatoire
     * Attention, elle n'est pas fonctionnelle, je crois...
     * @param j1 joueur 1
     * @param j2 joueur 2
     * @param j3 joueur 3
     * @param j4 joueur 4
     */
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
    
    /**
     * La méthode changerScore ajoute le paramètre pScore au score du joueur
     * Retourne un integer correspondant au nouveau score
     * @param pScore score à ajouter
     * @return nouveau score
     */
    public int changerScore(int pScore)
    {
        score = score + pScore;
        return score;
    }
    
    /**
     * getScore permet de retourner un entier correspondant au score du joueur
     * @return score du joueur
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * La méthode permet d'obtenir l'entier correspondant à l'Id du joueur
     * @return Id du joueur
     */
    public int getNumeroJoueur()
    {
        return numeroJoueur;
    }
    
    /**
     * La méthode permet d'obtenir le nom du joueur
     * Retourne un objet String
     * @return nom du joueur
     */
    public String getNomJoueur()
    {
        return nomDuJoueur;
    }
    
    /**
     * La méthode permet d'obtenir le numéro de l'équipe du joueur
     * Retourne un integer
     * @return numéro d'équipe
     */
    public int getNumeroEquipe()
    {
        return numeroEquipe;
    }
    
    /**
     * Permet de mettre une carte au rang "rang" dans la main du joueur
     * @param carteAjout carte à ajouter
     * @param rang rang souhaité pour la carte
     */
    protected void setCarte(Carte carteAjout, int rang)
    {
        mainDuJoueur[rang] = carteAjout;
    }
    
    /**
     * Accesseur pour la main du joueur
     * Retourne l'objet Carte situé au rang "rang"
     * @param rang rang dans la main du joueur
     * @return Carte qui s'y trouve (ou null si vide)
     */
    protected Carte getCarte(int rang)
    {
        Carte carteTransit = mainDuJoueur[rang];
        mainDuJoueur[rang] = null;
        return carteTransit;
    }
    
    /**
     * La méthode permet d'afficher le paquet de cartes d'un joueur
     */
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
    
    /**
     * Cette méthode permet de trier les cartes par couleur puis par valeur
     * au sein de la main d'un joueur
     * @param atout Couleur atout
     */
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
    
    /**
     * Calcul du nombre de cartes restantes dans la main du joueur
     * Retourne un entier
     * @return nombre de cartes
     */
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
    
    /**
     * La méthode teste si la carte à l'emplacement rang existe
     * @param rang emplacement à tester
     * @return vrai ou faux
     */
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
