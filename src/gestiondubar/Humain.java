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
    protected int cote_popularite = 10;
    protected String crie;
    
    // Constructeur
    public Humain(){
        this.prenom="";
        this.surnom="";
        this.porte_monnaie=0;
        this.crie="";
    }
    
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
    
    public void parler(String phrase, Humain humain){
        System.out.println(this.prenom + ": " + phrase + " " + humain.prenom);
    }
    
    public void se_Presenter(){
        parler(this.crie + "!!! " +"Je m'appelle " + this.prenom + " mais on m'appelle souvent " + this.surnom + " il me reste encore " + this.porte_monnaie + "€.");
    }
    
    protected void paye(Boisson boisson){
        this.porte_monnaie-=boisson.prix_vente;  
    }
    
    protected void boire(Boisson boisson){
        parler("Hum GluGluGlu je viens de finir " + boisson.name);
    }
    
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        this.cote_popularite+=5;
    }
    
    public void offrir_Verre(Humain camarade, Boisson boisson,Serveur serveur){
        this.cote_popularite+=5;
    }
    
    public void offrir_Verre (Barman barman, Boisson boisson){
        this.cote_popularite+=10;
    }
    
    public void offrir_Verre (Serveur serveur, Boisson boisson){
        this.cote_popularite+=10;
    }
}