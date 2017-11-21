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
public class Sexe_Client {
    
        
    String sexe;
    Object attribut;
    
    public Sexe_Client(Object pattribut){
        if(pattribut.getClass() == TShirt.class){
            this.sexe= "Homme";
            this.attribut=pattribut;
        }        
        else{
            if(pattribut.getClass() == Bijoux.class){
                this.sexe = "Femme";
                this.attribut=pattribut;
            }
        }
    }
    
}
