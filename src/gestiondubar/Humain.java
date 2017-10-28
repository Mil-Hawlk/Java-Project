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
public abstract class Humain {
    private final String prenom;
    private final String surnom;
    protected float porte_monnaie;
    private int cote_popularite = 10;
    protected String crie;
    
    // Constructeur
    public Humain(String cprenom, String csurnom, float cporte_monnaie, String ccrie){
        this.prenom=cprenom;
        this.surnom=csurnom;
        this.porte_monnaie=cporte_monnaie;
        this.crie=ccrie;
    }    
    
    // Accesseur
    public String obtenir_Prenom(){
        return(this.prenom);
    }
    
    public String obtenir_Surnom(){
        return(this.surnom);
    }
    
    // Fonctions
    public void parler(String phrase){
        System.out.println(this.prenom + ": " + phrase);
    }
    
    public void se_Presenter(){
        parler(this.crie + "!!! " +"Je m'appelle " + this.prenom + " mais on m'appelle souvent " + this.surnom);
    }
    
    public void paye(double prix){
        this.porte_monnaie-=prix;
    }
    
    public void boire(double prix_boisson){
        paye(prix_boisson);
    }
    
    public void offrir_Verre(Humain camarade, double prix_boisson){
        paye(prix_boisson);
    }
}