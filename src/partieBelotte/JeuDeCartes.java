package partieBelotte;

import java.util.Random;

/**
 * Cette Classe permet de générer des jeux de 32 cartes
 * comme celui qui est utilisé pour jouer à la belote
 * @author pierre
 * @author david
 * @version 1.0
 */
public class JeuDeCartes {
    /**
     * Ce tableau vide de type Carte
     * représente un jeu de 32 cartes
     */
    private Carte monJeu[] = new Carte[32];
    
    /**
     *Un jeu de cartes est défini par toutes ses cartes,
     * contenues dans un tableau. L'ordre des cartes dans 
     * le tableau définit l'ordre de tirage des cartes dans le tas
     */
    protected JeuDeCartes()
    {
        int z = 0;
        for(CarteValeur i : CarteValeur.values())
        {
            for(Couleur j : Couleur.values())
            {
                monJeu[z] = new Carte(i,j);
                z++;
            }
        }
    }
    
    /**
     * Cette méthode permet de mélanger les cartes
     */
    protected void melangerJeuDeCartes()
    {
        Random alea = new Random();
        Carte carteTransition;
        int nbAlea;
        for(int i=0;i<32;i++)
        {
            carteTransition = monJeu[i];
            nbAlea = alea.nextInt(32);
            monJeu[i] = monJeu[nbAlea];
            monJeu[nbAlea] = carteTransition;
        }
        
    }
    
    /**
     * Cette méthode permet de remettre le jeu de cartes dans l'ordre
     */
    protected void triJeuDeCartes()
    {
        /* A la manière de celui qui retire les cubes d'un Rubick's cube pour le
        résoudre, on réinitialise le jeu depuis le début - #onTricheUnPeu */
        int z = 0;
        for(CarteValeur i : CarteValeur.values())
        {
            for(Couleur j : Couleur.values())
            {
                monJeu[z] = new Carte(i,j);
                z++;
            }
        }
    }
    
    /**
     * Cette méthode permet d'afficher l'intégralité du jeu de cartes
     */
    protected void afficherJeuCartes()
    {
        for(int i=1;i<=32;i++)
        {
            if(monJeu[i-1]!=null)
            {
                System.out.println("Carte n°"+i+": "
                        +monJeu[i-1].getFigureNom()+" de "
                        +monJeu[i-1].getCouleur());
            }
        }
    }
    
    /**
     * Cette méthode permet de couper le jeu de cartes
     * @param EndroitCoupe
     */
    protected void couper(int EndroitCoupe)
    {
        /* On coupe le jeu en deux étapes : on divise en deux tas jeuTransition1
        et jeuTransition2, puis on remet les deux tas dans l'autre sens */
        Carte jeuTransition1[] = new Carte[EndroitCoupe];
        Carte jeuTransition2[] = new Carte[32-EndroitCoupe];
        for(int i=0; i<32; i++)
        {
            if(i<EndroitCoupe)
            {
                jeuTransition1[i] = monJeu[i];
            }
            else
            {
                jeuTransition2[i-EndroitCoupe] = monJeu[i];
            }
        }
        for(int i=0; i<(32-EndroitCoupe); i++)
        {
            monJeu[i] = jeuTransition2[i];
        }
        for(int i=(32-EndroitCoupe); i<32; i++)
        {
            monJeu[i] = jeuTransition1[i-32+EndroitCoupe];
        }
        
    }
    
    /**
     * La méthode premiereDistribution distribue tour à tour
     * et à chacun des joueurs 5 cartes
     * @param j1 joueur 1
     * @param j2 joueur 2
     * @param j3 joueur 3
     * @param j4 joueur 4
     */
    protected void premiereDistribution(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
    /* Distribue 5 cartes du jeu à chaque joueur 
        Attention, le tirage est effectué selon l'ordre numéroJoueur*/
        for(int i=0; i<5; i++)
        {
            j1.setCarte(monJeu[i+(j1.getNumeroJoueur()-1)*5], i);
            j2.setCarte(monJeu[i+(j2.getNumeroJoueur()-1)*5], i);
            j3.setCarte(monJeu[i+(j3.getNumeroJoueur()-1)*5], i);
            j4.setCarte(monJeu[i+(j4.getNumeroJoueur()-1)*5], i);
        }
    }
    
    /**
     * Cette méthode permet d'afficher la carte tournante, 
     * càd la carte retournée côté visible, qui va orienter
     * le choix de jeu des joueurs
     */
    protected void afficherCarteTournante()
    {
        /*Après distribution, la carte tournante est la 21e carte (n°20)*/
        System.out.println("La carte tournante est "+monJeu[20].getFigureNom()+
                " de "+monJeu[20].getCouleur()+"\n");
    }
    
    /**
     * Cette méthode permet de donner la couleur de la carte tournante
     * Elle retourne sa valeur sous forme String
     * @return couleurCarteTournante
     */ 
    protected String getCouleurCarteTournante()
    {
        return monJeu[20].getCouleur();
    }
    
    /**
     * La méthode deuxiemeDistribution donne la carte tournante
     * au joueur qui a choisi l'atout, puis distribue le reste des cartes
     * (2 cartes au joueur en question, 3 aux autres)
     * @param j1 joueur 1
     * @param j2 joueur 2
     * @param j3 joueur 3
     * @param j4 joueur 4
     * @param joueurQuiPrend joueur ayant pris la carte tournante
     */
    protected void deuxiemeDistribution(Joueur j1, Joueur j2, Joueur j3, Joueur j4, int joueurQuiPrend)
    {
        /* On donne la carte tournante au premierJoueur, et on distribue le reste */
        if(joueurQuiPrend==j1.getNumeroJoueur())
        {
            System.out.println(j1.getNomJoueur()+" prend la carte tournante");
            j1.setCarte(monJeu[20], 5);
            j1.setCarte(monJeu[21], 6);
            j1.setCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j2.setCarte(monJeu[23+3*i], 5+i);
                j3.setCarte(monJeu[23+3*i+1], 5+i);
                j4.setCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j2.getNumeroJoueur())
        {
            System.out.println(j2.getNomJoueur()+" prend la carte tournante");
            j2.setCarte(monJeu[20], 5);
            j2.setCarte(monJeu[21], 6);
            j2.setCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.setCarte(monJeu[23+3*i], 5+i);
                j3.setCarte(monJeu[23+3*i+1], 5+i);
                j4.setCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j3.getNumeroJoueur())
        {
            System.out.println(j3.getNomJoueur()+" prend la carte tournante");
            j3.setCarte(monJeu[20], 5);
            j3.setCarte(monJeu[21], 6);
            j3.setCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.setCarte(monJeu[23+3*i], 5+i);
                j2.setCarte(monJeu[23+3*i+1], 5+i);
                j4.setCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j4.getNumeroJoueur())
        {
            System.out.println(j4.getNomJoueur()+" prend la carte tournante");
            j4.setCarte(monJeu[20], 5);
            j4.setCarte(monJeu[21], 6);
            j4.setCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.setCarte(monJeu[23+3*i], 5+i);
                j2.setCarte(monJeu[23+3*i+1], 5+i);
                j3.setCarte(monJeu[23+3*i+2], 5+i);
            }
        }
    }
    
    /**
     * Méthode permettant d'afficher les caractéristiques d'une carte
     * à un certain rang dans le jeu de cartes
     * @param rang entre 0 et 31 (32 cartes)
     */
    protected void afficherCarte(int rang)
    {
        System.out.println("Il s'agit du "+monJeu[rang].getFigureNom()+" de "+monJeu[rang].getCouleur());
    }
}
