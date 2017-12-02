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
public class Sexe_Serveur {
    protected String sexe ="";
    protected boolean choix;
    protected int coefficient= 5;
    
    /**
     * 
     * @param pchoix 
     */
    public Sexe_Serveur(boolean pchoix){
        /*Le serveur est un homme si son choix est true ou une femme si c'est 
        false, son coefficient est de base de 5*/
        this.choix=pchoix;
        this.sexe=definir_sexe(pchoix);
    }
    
    /**
     * 
     * @param pchoix
     * @param pcoeff 
     */
    public Sexe_Serveur(boolean pchoix, int pcoeff){
        /*Deuxieme constructeur prenant en plus le coefficient en paramêtre*/
        this.choix=pchoix;
        this.sexe=definir_sexe(pchoix);
        this.coefficient=pcoeff;
    }
    
    /**
     * 
     * @param choix
     * @return 
     */
    private String definir_sexe(boolean choix){
        /*Fonction afin de définir leur sexe en String en fonction du booléen*/
        if(choix){
            return("Homme");
        }else{
            return("Femme");
        }
    }
    
}
