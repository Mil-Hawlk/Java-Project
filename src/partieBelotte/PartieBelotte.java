/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partieBelotte;

/**
 *
 * @author david
 */
public class PartieBelotte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carte maCarte = new Carte(CarteValeur.As,Couleur.Coeur);
        System.out.println("La carte est "+maCarte.figure.nom+" de couleur "+maCarte.couleur);
        JeuDeCartes monJeu = new JeuDeCartes();
        System.out.println(monJeu.monJeu[0].getNom()+monJeu.monJeu[0].getCouleur());
        System.out.println(monJeu.monJeu[15].getNom()+monJeu.monJeu[15].getCouleur());
        System.out.println(monJeu.monJeu[31].getNom()+monJeu.monJeu[31].getCouleur());
        monJeu.melangerCartes();
        System.out.println(monJeu.monJeu[0].getNom()+monJeu.monJeu[0].getCouleur());
        System.out.println(monJeu.monJeu[15].getNom()+monJeu.monJeu[15].getCouleur());
        System.out.println(monJeu.monJeu[31].getNom()+monJeu.monJeu[31].getCouleur());
        monJeu.triCartes();
        monJeu.couper(11);
        monJeu.couper(21);
        System.out.println(monJeu.monJeu[0].getNom()+monJeu.monJeu[0].getCouleur());
        System.out.println(monJeu.monJeu[15].getNom()+monJeu.monJeu[15].getCouleur());
        System.out.println(monJeu.monJeu[31].getNom()+monJeu.monJeu[31].getCouleur());
    }
    
}
