package partieBelotte;

import java.util.Random;

/**
Un jeu de cartes est défini par toutes ses cartes, contenues dans un tableau arrayList
L'ordre des cartes dans le tableau définit l'ordre de tirage des cartes dans le tas
 */
public class JeuDeCartes {
    Carte monJeu[] = new Carte[32];
    
    protected JeuDeCartes()
    {
        int z = 0;
        for(CarteValeur i : CarteValeur.values())
        {
            for(Couleur j : Couleur.values())
            {
                this.monJeu[z] = new Carte(i,j);
                z++;
            }
        }
    }
    
    protected void melangerCartes()
    {
        Random alea = new Random();
        Carte carteTransition;
        int nbAlea;
        for(int i=0;i<32;i++)
        {
            carteTransition = this.monJeu[i];
            nbAlea = alea.nextInt(32);
            this.monJeu[i] = this.monJeu[nbAlea];
            this.monJeu[nbAlea] = carteTransition;
        }
        
    }
    
    protected void triCartes()
    {
        /* A la manière de celui qui retire les cubes d'un Rubick's cube pour le
        résoudre, on réinitialise le jeu depuis le début - #onTricheUnPeu */
        int z = 0;
        for(CarteValeur i : CarteValeur.values())
        {
            for(Couleur j : Couleur.values())
            {
                this.monJeu[z] = new Carte(i,j);
                z++;
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
                jeuTransition1[i] = this.monJeu[i];
            }
            else
            {
                jeuTransition2[i-EndroitCoupe] = this.monJeu[i];
            }
        }
        for(int i=0; i<(32-EndroitCoupe); i++)
        {
            this.monJeu[i] = jeuTransition2[i];
        }
        for(int i=(32-EndroitCoupe); i<32; i++)
        {
            this.monJeu[i] = jeuTransition1[i-32+EndroitCoupe];
        }
        
    }
    
    
}
