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
    protected Sexe_Client sexe;
    protected boolean est_bourre = false;
    protected boolean est_vire = false; 
    
    public Client(String cprenom, String csurnom, int cporte_monnaie, String ccrie, Boisson pboisson_favorite, Boisson pboisson_secours, Object attribut){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        try{
            if(attribut.getClass()!= TShirt.class && attribut.getClass()!=Bijoux.class ){
                throw new Exception("Ceci n'est ni un homme ni une femme");
            }
            else{
                this.boisson_favorite=pboisson_favorite;
                this.boisson_secours=pboisson_secours;
                this.sexe= new Sexe_Client(attribut);
                this.se_Presenter();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }   
   
    public void se_Faire_Offrir_un_Verre(Humain amis, Barman barman){
    } // tu te fais offrir ta boisson favorite ou celle de secour ou bien tu ne prends rien
      
    public void changer_Sexe(Object  new_attribut){
        try{
            if(new_attribut.getClass()!= TShirt.class && new_attribut.getClass()!=Bijoux.class ){
                throw new Exception("Il ne peut pas changer de sexe mauvais attribut");
            }
            else{
                this.sexe = new Sexe_Client(new_attribut);  
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
    
    public void parler(String phrase, Serveur serveur){
        if(this.niveau_alcool>45 ){
                if(this.sexe.sexe == "Homme" && serveur.sexe.sexe=="Femme")
                {
                    super.parler(phrase + " Poupée");}
                else{
                    if(this.sexe.sexe == "Femme" && serveur.sexe.sexe=="Homme"){
                        super.parler(phrase+" Bo gosse");
                    }
                    else{
                        super.parler(phrase);
                    }
                }
            }
        else{
            super.parler(phrase);
        }
        }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        
        super.offrir_Verre(camarade, boisson, barman);
        parler("J'aimerai commander " + boisson.name + " "+ " pour " + camarade.obtenir_Prenom(), barman);
        this.paye(boisson);
        barman.servir_Boisson(boisson,this);
        camarade.parler("Merci beaucoup", this);
        camarade.boire(boisson);
    }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Serveur serveur){
        super.offrir_Verre(camarade, boisson, serveur);
        parler("J'aimerai commander " + boisson.name + " "+ " pour " + camarade.obtenir_Prenom(), serveur);
        this.paye(boisson);
        serveur.commander(this,boisson);
        camarade.parler("Merci beaucoup", this);
        camarade.boire(boisson);
    }
    
    public void commander(Boisson boisson, Barman barman){
        this.parler("J'aimerai commander " + boisson.name , barman);
        if(this.est_bourre == true){
            barman.parler("Désolé je ne peux plus te servir",this);
        }
        else{
            this.paye(boisson);
            barman.servir_Boisson(boisson,this);
            this.boire(boisson);
        }
    }
    
    public void commander(Boisson boisson, Serveur serveur){
        /* Si les clients sont de sexes opposés et que le coeficient de charmes est supérieur à 8 il leur paie un verre*/
        /* Si les clients sont de meme sexe et que leur coefficietn est superieur a 8 il ne commande pas s'ils leur coefficient de popularite est inferieur a leur degre alcool*/
        this.parler("J'aimerai commander " + boisson.name + " " + serveur.obtenir_Prenom(),serveur);
        if(this.est_bourre == true){
            serveur.parler("Désolé je ne peux plus te servir", this);
        }
        else{
            this.paye(boisson);
            serveur.commander(this,boisson);  
            this.boire(boisson);
        }
        
    }
}

