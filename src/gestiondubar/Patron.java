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
public class Patron extends Client {
    
    
    public Patron(String cprenom, String csurnom, int cporte_monnaie, String ccrie, Boisson pboisson_favorite, Boisson pboisson_secours, Object attribut)
    {
        /*Catch l'exeption si attribut ne fais pas partie de l'énumération pour les femmes*/
        super(cprenom,csurnom,cporte_monnaie,ccrie,pboisson_favorite,pboisson_secours,attribut);
    }
    
    @Override
    public void parler(String phrase){
        System.out.print("Patronne ");
        super.parler(phrase);
    }
    
    @Override
    public void parler(String phrase, Humain humain){
        System.out.print("Patronne ");
        super.parler(phrase);
    }
    
    public void recuperer_Caisse(Barman barman){
        super.parler("Merci bon boulot", barman);
    }
    
    public void changer_Sexe(Object  new_attribut){
        /* Faire une exeption si l'attribut est différent de l'énumeration pour les femme*/
        super.changer_Sexe(new_attribut);
    }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        this.parler("J'aimerai commander " + boisson.name, barman);
        barman.servir_Boisson(boisson,this);
        camarade.boire(boisson); 
    }
    
    @Override
    public void commander(Boisson boisson, Barman barman){
        this.parler("J'aimerai commander " + boisson.name, barman);
        barman.servir_Boisson(boisson,this);
        this.boire(boisson);
    }
    
    @Override
    public void commander(Boisson boisson, Serveur serveur){
        this.parler("j'aimerai commander " + boisson.name,serveur);
        serveur.barman.servir_Boisson(boisson, this);
        this.boire(boisson);
    }
    
    public void ordonner(Serveur serveur, Client client){
        this.parler("Tu ne dois plus servir",serveur);
        client.est_bourre = true;
    }
    
    public void exclure(Client client){
        client.est_vire=true;
    }
    
    public void ordonner(Barman barman, Client client){
        this.parler("Tu ne dois plus servir",barman);
        client.est_bourre = true;
    }
    
    /* Ne pas oublier qu'elle peut exclure quelqu'un*/
}
