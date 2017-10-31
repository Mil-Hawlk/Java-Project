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
public class Barman extends Humain{
    private int caisse = 1000;
    private Patron patron;
    
    /* Accesseur*/
    public void obtenir_caisse(){
        System.out.println(this.caisse);
    }
    
    public Barman(String cprenom, String csurnom, float cporte_monnaie, String ccrie, Patron ppatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.patron=ppatron;
        parler("Je suis le nouveau barman " + this.obtenir_Prenom());
    }
    
    public void remplir_Stock(Boisson boisson, int nombre){
        boisson.nombre+=nombre;
        caisse -= nombre * boisson.prix_achat;
    }
    
    public void servir_Boisson(Boisson boisson){
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    public void servir_Boisson(Boisson boisson, Client client){
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    public void servir_Boisson(Boisson boisson, Patron patron){
        parler("Voici ton verre");
        boisson.nombre-=1;
    }
    
    @Override
    public void boire(Boisson boisson){
        /* Exeption si la boisson est alcoolisé*/
        super.boire(boisson);
        boisson.nombre-=1; // il ne paie pas juste la boisson diminue
    }
    
    @Override
    public void parler(String phrase){
        System.out.print("Barman ");
        super.parler(phrase + " Coco");
    }
    
    @Override
    public void parler(String phrase, Humain camarade){
        System.out.print("Barman ");
        super.parler(phrase + " Coco",camarade);
    }
    
    public void se_Faire_Offrir_un_Verre(Humain amis, Boisson boisson){
        amis.offrir_Verre(this,boisson);
        amis.paye(boisson);
        this.boire(boisson);
        
    }
    
    public void offrir_Verre(Client camarade, Boisson boisson, Barman barman){
        if(camarade.cote_popularite>100){
            this.parler("Tiens mon Loulou, je t'offre " + boisson.name, camarade);
            this.servir_Boisson(boisson,camarade);
            camarade.boire(boisson);
            this.paye(boisson);
        }
    }
    
    protected void vider_caisse(){
        if(this.caisse>10000){
            parler("Voici une partie de la caisse patron ", this.patron);
            this.patron.porte_monnaie+=this.caisse/3;
            this.caisse=this.caisse/3;
            this.patron.recuperer_Caisse(this);
        }
    }
       
}
