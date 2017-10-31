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
public class Sexe_Serveur {
    protected String sexe ="";
    protected boolean choix;
    protected int coefficient= 5;
    
    public Sexe_Serveur(boolean pchoix){
        this.choix=pchoix;
        this.sexe=definir_sexe(pchoix);
    }
    
    public Sexe_Serveur(boolean pchoix, int pcoeff){
        this.choix=pchoix;
        this.sexe=definir_sexe(pchoix);
        this.coefficient=pcoeff;
    }
    
    private String definir_sexe(boolean choix){
        if(choix){
            return("Homme");
        }else{
            return("Femme");
        }
    }
    
}
