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
public class Sexe {
    
    String sexe;
    Object attribut;
    
    public Sexe(Object attribut){
        if(attribut.getClass() == TShirt.class){
            this.sexe= "Homme";
        }        
        else{
            if(attribut.getClass() == Bijoux.class){
                this.sexe = "Femme";
            }
        } // sinon exeption à faire
    }
}
