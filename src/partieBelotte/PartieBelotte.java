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
        JouerBelote maPartie = new JouerBelote(81,"Luc","Jean","Patrick","André");
        maPartie.afficherDernierePartieJouee();
    }
    
}
