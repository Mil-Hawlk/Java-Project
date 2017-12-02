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
public class Sexe_Client {
    
        
    String sexe;
    Object attribut;
    
    /**
     * 
     * @param pattribut 
     */
    public Sexe_Client(Object pattribut){
        /*Le client est une femme si son attribut est de la classe bijou ou 
        c'est un homme si son attribut est de la classe TShirt*/
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
