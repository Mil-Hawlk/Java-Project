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
        System.out.println("Début de la partie de belotte");
        Joueur j1 = new Joueur("David",1);
        Joueur j2 = new Joueur("Pierre",2);
        Joueur j3 = new Joueur("Jacky",3);
        Joueur j4 = new Joueur("Michel",4);
        System.out.println("Lancement de la première manche");
        Manche manche1 = new Manche(j1, j2, j3, j4);
        manche1.jouerManche(j1, j2, j3, j4);
        j1.afficherPaquet();
    }
    
}
