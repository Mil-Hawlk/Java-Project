package partieBelotte;

import java.util.Random;

/**
Un jeu de cartes est défini par toutes ses cartes, contenues dans un tableau arrayList
L'ordre des cartes dans le tableau définit l'ordre de tirage des cartes dans le tas
 */
public class JeuDeCartes {
    private Carte monJeu[] = new Carte[32];
    
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
    
    protected void premiereDistribution(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
    /* Distribue 5 cartes du jeu à chaque joueur 
        Attention, le tirage est effectué selon l'ordre numéroJoueur*/
        for(int i=0; i<5; i++)
        {
            j1.ajouterCarte(monJeu[i+(j1.donnerNumeroJoueur()-1)*5], i);
            j2.ajouterCarte(monJeu[i+(j2.donnerNumeroJoueur()-1)*5], i);
            j3.ajouterCarte(monJeu[i+(j3.donnerNumeroJoueur()-1)*5], i);
            j4.ajouterCarte(monJeu[i+(j4.donnerNumeroJoueur()-1)*5], i);
        }
    }
    
    protected void afficherCarteTournante()
    {
        /*Après distribution, la carte tournante est la 21e carte (n°20)*/
        System.out.println("La carte tournante est "+monJeu[20].getFigureNom()+
                " de "+monJeu[20].getCouleur()+"\n");
    }
    
    protected String donnerCouleurCarteTournante()
    {
        return monJeu[20].getCouleur();
    }
    
    protected void deuxiemeDistribution(Joueur j1, Joueur j2, Joueur j3, Joueur j4, int joueurQuiPrend)
    {
        /* On donne la carte tournante au premierJoueur, et on distribue le reste */
        if(joueurQuiPrend==j1.donnerNumeroJoueur())
        {
            System.out.println(j1.donnerNomJoueur()+" prend la carte tournante");
            j1.ajouterCarte(monJeu[20], 5);
            j1.ajouterCarte(monJeu[21], 6);
            j1.ajouterCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j2.ajouterCarte(monJeu[23+3*i], 5+i);
                j3.ajouterCarte(monJeu[23+3*i+1], 5+i);
                j4.ajouterCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j2.donnerNumeroJoueur())
        {
            System.out.println(j2.donnerNomJoueur()+" prend la carte tournante");
            j2.ajouterCarte(monJeu[20], 5);
            j2.ajouterCarte(monJeu[21], 6);
            j2.ajouterCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.ajouterCarte(monJeu[23+3*i], 5+i);
                j3.ajouterCarte(monJeu[23+3*i+1], 5+i);
                j4.ajouterCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j3.donnerNumeroJoueur())
        {
            System.out.println(j3.donnerNomJoueur()+" prend la carte tournante");
            j3.ajouterCarte(monJeu[20], 5);
            j3.ajouterCarte(monJeu[21], 6);
            j3.ajouterCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.ajouterCarte(monJeu[23+3*i], 5+i);
                j2.ajouterCarte(monJeu[23+3*i+1], 5+i);
                j4.ajouterCarte(monJeu[23+3*i+2], 5+i);
            }
        }
        if(joueurQuiPrend==j4.donnerNumeroJoueur())
        {
            System.out.println(j4.donnerNomJoueur()+" prend la carte tournante");
            j4.ajouterCarte(monJeu[20], 5);
            j4.ajouterCarte(monJeu[21], 6);
            j4.ajouterCarte(monJeu[22], 7);
            for(int i=0; i<3; i++)//Reste 3 cartes chacun à distribuer
            {
                j1.ajouterCarte(monJeu[23+3*i], 5+i);
                j2.ajouterCarte(monJeu[23+3*i+1], 5+i);
                j3.ajouterCarte(monJeu[23+3*i+2], 5+i);
            }
        }
    }
    
    protected void afficherCarte(int rang)
    {
        System.out.println("Il s'agit du "+monJeu[rang].getFigureNom()+" de "+monJeu[rang].getCouleur());
    }
}
