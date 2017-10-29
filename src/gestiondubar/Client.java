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
public class Client extends Humain {
    
    protected Boisson boisson_favorite;
    protected Boisson boisson_secours;
    protected int niveau_alcool = 0;
    protected Sexe sexe;
    
    public Client(String cprenom, String csurnom, int cporte_monnaie, String ccrie, Boisson pboisson_favorite, Boisson pboisson_secours, Object attribut){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.boisson_favorite=pboisson_favorite;
        this.boisson_secours=pboisson_secours;
        this.sexe= new Sexe(attribut);
    }   
   
    public void se_Faire_Offrir_un_Verre(Humain amis, Barman barman){

    } // tu te fais offrir ta boisson favorite ou celle de secour ou bien tu ne prends rien
      
    public void changer_Sexe(Object  new_attribut){
        this.sexe = new Sexe(new_attribut);
    }
    
    @Override
    public void se_Presenter(){
        super.se_Presenter();
        parler("Ma boisson favorite est " + this.boisson_favorite.name);
    }
    
    @Override
    protected void boire(Boisson boisson){
        super.boire(boisson);
        this.niveau_alcool+=boisson.degre_alcool;
    }
    
    @Override
    public void parler(String phrase, Humain humain){
        if(this.niveau_alcool> 20 && humain.getClass()==Serveur.class){
                /* Vérifier que les deux sexes sont opposer et il parlera de manière différente */
            }
        }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        super.offrir_Verre(camarade, boisson, barman);
        this.parler("J'aimerai commander " + boisson.name, barman);
        this.paye(boisson);
        barman.servir_Boisson(boisson);
        camarade.boire(boisson);
    }
    
    public void commander(Boisson boisson, Barman barman){
        this.parler("J'aimerai commander " + boisson.name, barman);
        this.paye(boisson);
        barman.servir_Boisson(boisson);
        this.boire(boisson);
    }
    
    public void commander(Boisson boisson, Serveur serveur){
        this.parler("j'aimerai commander " + boisson.name,serveur);
        this.paye(boisson);
        serveur.patron.servir_Boisson(boisson);   
        this.boire(boisson);
    }
}

