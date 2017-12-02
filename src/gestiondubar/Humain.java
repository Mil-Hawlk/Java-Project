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
public abstract class Humain {
    private final String prenom; // Private et final car impossible à changer
    private final String surnom; // Private et final car impossible à changer
    protected float porte_monnaie; 
    protected int cote_popularite = 10;
    protected String crie;
    
    /**
     * 
     */
    public Humain(){
        /*Constructeur d'humain*/
        this.prenom="";
        this.surnom="";
        this.porte_monnaie=0;
        this.crie="";
    }
    
    /**
     * 
     * @param cprenom
     * @param csurnom
     * @param cporte_monnaie
     * @param ccrie 
     */
    public Humain(String cprenom, String csurnom, float cporte_monnaie, 
            String ccrie){
        this.prenom=cprenom;
        this.surnom=csurnom;
        this.porte_monnaie=cporte_monnaie;
        this.crie=ccrie;
    }    
    
    /**
     * 
     * @return 
     */
    public String getPrenom(){
        // Accesseur au prenom
        return(this.prenom);
    }
    
    /**
     * 
     * @return 
     */
    public String getSurnom(){
        // Accesseur au surnom
        return(this.surnom);
    }
    
    /**
     * 
     * @return 
     */
    public float getPorteMonnaie(){
        return(this.porte_monnaie);
    }
    
    /**
     * 
     * @param phrase 
     */
    public void parler(String phrase){
        //Fonction pour parler
        System.out.println(this.prenom + ": " + phrase);
    }
    
    /**
     * 
     * @param phrase
     * @param humain 
     */
    public void parler(String phrase, Humain humain){
        // Fonction pour parler en interpelant quelqu'un
        System.out.println(this.prenom + ": " + phrase + " " + humain.prenom);
    }
    
    /**
     * 
     */
    public void se_Presenter(){
        // Fonction pour se présenter
        parler(this.crie + "!!! " +"Je m'appelle " + this.prenom + 
                " mais on m'appelle souvent " + this.surnom + 
                " il me reste encore " + this.porte_monnaie + "€.");
    }
    
    /**
     * 
     * @param boisson 
     */
    protected void paye(Boisson boisson){
        // Fonction pour payer une boisson
        this.porte_monnaie-=boisson.prix_vente;  
    }
    
    /**
     * 
     * @param boisson 
     */
    protected void boire(Boisson boisson){
        // Fonction pour boire une boisson
        parler("Hum GluGluGlu je viens de finir " + boisson.name);
    }
    
    /**
     * 
     * @param camarade 
     */
    protected void offrir_Verre(Humain camarade){
        // Fonction pour offrir un verre à quelqu'un 
        this.cote_popularite+=10;
    }
}