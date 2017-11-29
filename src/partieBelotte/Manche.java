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
public class Manche {
    Joueur j1;
    Joueur j2;
    Joueur j3;
    Joueur j4;
    
    protected Manche(Joueur pJ1, Joueur pJ2,
            Joueur pJ3, Joueur pJ4)
    {
        j1 = pJ1;
        j2 = pJ2;
        j3 = pJ3;
        j4 = pJ4;
    }
    
    /* La fonction jouerManche permet de lancer une manche de belotte. Elle 
    modifiera les scores de la manche de chaque joueur */
    protected void jouerManche(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
        Random alea = new Random();
        // On commence par mélanger, couper puis donner 3 cartes pour les 4 joueurs
        JeuDeCartes monJeuDeCartes = new JeuDeCartes();
        monJeuDeCartes.melangerJeuDeCartes();
        monJeuDeCartes.couper(alea.nextInt(32));
        monJeuDeCartes.premiereDistribution(j1, j2, j3, j4);
        monJeuDeCartes.afficherCarteTournante();
        //On décide de faire démarrer j1 en pique et on distribue
        int joueurQuiPrend = 1;
        Couleur atout = Couleur.Pique;
        System.out.println("L'atout est "+atout.getCouleur());
        monJeuDeCartes.deuxiemeDistribution(j1,j2,j3,j4,joueurQuiPrend);
        //Chaque joueur trie ses cartes
        j1.triPaquetCartes(atout);
        j2.triPaquetCartes(atout);
        j3.triPaquetCartes(atout);
        j4.triPaquetCartes(atout);
        //Verifier les annonces !
        j1.changerScore(this.checkAnnonce(j1));
        j2.changerScore(this.checkAnnonce(j2));
        j3.changerScore(this.checkAnnonce(j3));
        j4.changerScore(this.checkAnnonce(j4));
        //On est partis pour les 8 plis !
        this.jouerPli(joueurQuiPrend);
    }
    
    /* La fonction jouer un pli permettra de jouer un pli, sachant quel est l'atout
    et quelles sont les cartes jetées par les js. (Sortie relative au J1, 
    score positif si victoire de son équipe, score négatif sinon?)*/
    protected int jouerPli(int pJoueurQuiPrend)
    {
        return 0;
    }
    
    protected int choixCarte(Joueur pJoueur)
    {
        return 0;
    }
    
    protected int checkAnnonce(Joueur pJoueur)
    {
        return 0;
    }
}
