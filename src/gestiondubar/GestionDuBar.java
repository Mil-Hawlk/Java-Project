/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondubar;

/**
 *
 * @author pierr
 */
public class GestionDuBar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boisson leffe = new Boisson("leffe",6.7,1.8,2);
        Boisson cuvee = new Boisson("Cuvée des Trolls",5,1.2,2);
        Client David = new Client("David","Brakmar",10,"Grouikk",cuvee,leffe);
        David.se_Presenter();
        
    }
    
}
