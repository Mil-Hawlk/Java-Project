/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondubar;

/**
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */
import java.util.LinkedList;
import java.util.List;
import partieBelotte.JouerBelote;

public class Table {
    protected int place = 4;
    protected List client = new LinkedList();
    
    /**
     * @exception Exception
     */
    public void lancee_belote(){
        /*Fonction permettant de lancer une partie de belote, exception s'il
        manque de joueurs*/
        try{
            if(this.place!=0){
                throw new Exception("Erreur: Il manque de joueur(s)");
            }
            else{
                System.out.println("GO pour la partie");
                Client joueur1 = (Client)client.get(0);
                Client joueur2 = (Client)client.get(1);
                Client joueur3 = (Client)client.get(2);
                Client joueur4 = (Client)client.get(3);
                JouerBelote maPartie = new JouerBelote(501,joueur1.getPrenom(),
                        joueur2.getPrenom(),joueur3.getPrenom(),
                        joueur4.getPrenom());
                System.out.println("Resultats de la dernière partie : \n");
                maPartie.afficherDerniereMancheJouee();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param 
     * @exception Exception
     */
    public void quitter(Client pclient){
        /*Fonction permettant à un client de quitter la table*/
        try{
            int compteur=0;
            while(compteur<this.client.size() && 
                    this.client.get(compteur)!=pclient){
                compteur++;
                }
                if(this.client.get(compteur) == pclient){
                    this.client.remove(compteur);
                    pclient.est_interieur=0;
                    this.place++;
                }
                else{
                throw new Exception("Erreur: Le client n'est pas présent sur" 
                        + "la table"); 
                }
        } 
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
