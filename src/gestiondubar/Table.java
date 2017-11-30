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
import java.util.LinkedList;
import java.util.List;

public class Table {
    protected int place = 4;
    protected List client = new LinkedList();
    
    public void lancee_belote(){
        try{
            if(this.place!=0){
                throw new Exception("Erreur: Il manque de joueur(s)");
            }
            else{
                System.out.println("GO pour la partie");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void quitter(Client pclient){
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
                throw new Exception("Erreur: Le client n'est pas présent dans "
                        + "le bar"); 
                }
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
