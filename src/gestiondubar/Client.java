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

public class Client extends Humain implements Tournee_generale {
    
    protected Boisson boisson_favorite;
    protected Boisson boisson_secours;
    protected int niveau_alcool = 0;
    protected Sexe_Client sexe;
    protected int est_bourre = 0;
    protected int est_interieur = 0; 
    protected boolean est_Attablé = false;
    
    protected Client(String cprenom, String csurnom, int cporte_monnaie, 
            String ccrie, Boisson pboisson_favorite, 
            Boisson pboisson_secours, Object attribut){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        try{
            if(attribut.getClass()!= TShirt.class && attribut.getClass()!=Bijoux.class){
                throw new Exception("Ceci n'est ni un homme ni une femme mais reste un humain");
            }
            else {
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
    
    public void parler(String phrase, Serveur serveur){
        switch(this.est_interieur){
            case(0):
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
                break;
            case(1):
                System.out.println(this.obtenir_Prenom() + ". Vous n'êtes pas dans le bar");
                break;
        }        
    }
    
    public void se_Faire_Offrir(Humain camarade, Barman barman){        
        switch(this.est_interieur){
            case(0):
                if(this.cote_popularite>50){
                    if(this.boisson_favorite.nombre>0){
                    parler("Hey tu ne voudrais pas m'offrir un verre", camarade);
                    camarade.offrir_Verre(this, this.boisson_favorite, barman);
                    this.cote_popularite-=50;
                } else {
                    if(this.boisson_secours.nombre>0){
                    parler("Hey tu ne voudrais pas m'offrir un verre", camarade);
                    camarade.offrir_Verre(this, this.boisson_secours, barman);
                    this.cote_popularite-=50;
                } else {
                    camarade.parler("Non désolé mon pote",this);
                       }
                        }
                }
            break;
            case(1):
                System.out.println(this.obtenir_Prenom() + ". Vous n'êtes pas dans le bar");
            break;
                        
        }
    }
    
    public void se_Faire_Offrir(Humain camarade, Serveur serveur){
        switch(this.est_interieur){
            case(0):
               if(this.cote_popularite>50){
            if(this.boisson_favorite.nombre>0){
                parler("Hey tu ne voudrais pas m'offrir un verre", camarade);
                camarade.offrir_Verre(this, this.boisson_favorite, serveur);
                this.cote_popularite-=50;
            } else {
                if(this.boisson_secours.nombre>0){
                    parler("Hey tu ne voudrais pas m'offrir un verre", camarade);
                    camarade.offrir_Verre(this, this.boisson_secours, serveur);
                    this.cote_popularite-=50;
                }
                else {
                    camarade.parler("Non désolé mon pote",this);
                }
            }
        }
               break;
               case(1):
                   System.out.println("Vous n'êtes pas dans le bar");
        }
    }
    
    public void commander(Boisson boisson, Barman barman){
            switch(this.est_interieur){
                case(0):
                    try{
                        throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case(1):
                    try{
                        if(this.porte_monnaie<boisson.prix_vente){
                            throw new Exception ("Vous n'avez pas assez d'argent");
                        }
                        else{
                            try{
                                if(boisson.nombre<1){
                                    throw new Exception ("Il n'a plus cette boisson");
                                }
                                else{
                                    if(this.est_bourre == 1){
                                        barman.parler("Désolé je ne peux plus te servir",this);
                                    }
                                    else{
                                        this.parler("J'aimerai commander " + boisson.name,barman);
                                        this.paye(boisson);
                                        barman.servir_Boisson(boisson,this);
                                        this.boire(boisson);
                                    }
                                }
                            }
                            catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
    }  

    public void commander(Boisson boisson, Serveur serveur){
        switch(this.est_interieur){
            case(0):
                try{
                    throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case(1):
                try{
                    if(this.porte_monnaie<boisson.prix_vente){
                        throw new Exception("Erreur: Vous n'avez pas assez d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus de cette boisson");
                        }
                        else{
                                            this.parler("J'aimerai commander " + boisson.name + " " + serveur.obtenir_Prenom(),serveur);
                switch(this.est_bourre){ // mise en place d'un switch afin de ne pas dépasser le quota de 3 if - else par fonction
                case(1):
                    serveur.parler("Désolé je ne peux plus te servir", this);
                    break;
                case(0):
                    if(this.sexe.sexe!=serveur.sexe.sexe && this.niveau_alcool/10>serveur.sexe.coefficient){
                            this.paye(boisson);
                            serveur.commander(this,boisson);  
                            this.boire(boisson);
                            this.offrir_Verre(serveur, boisson);
                        }
                        else{
                            if(this.sexe.sexe==serveur.sexe.sexe && serveur.sexe.coefficient < this.niveau_alcool/20){
                                parler("En fait je vais annuler ma commande je pense", serveur);
                            }
                            else{
                                this.paye(boisson);
                                serveur.commander(this,boisson);  
                                this.boire(boisson);
                            }
                        }
                    break;
                    } 
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            break;            
        }
    }  
    
    public void quitter_Bar(Bar mon_Bar){
        mon_Bar.quitter_client(this);
    }
    
    @Override
    public void se_Presenter(){
        super.se_Presenter();
        parler("Ma boisson favorite est " + this.boisson_favorite.name);
    }
    
    @Override
    protected void boire(Boisson boisson){
        switch(this.est_interieur){
            case(0):
                try {
                    throw new Exception("Vous n'êtes pas dans le bar");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case(1):
                super.boire(boisson);
                this.niveau_alcool+=boisson.degre_alcool;
                break;
        }
    }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        switch(this.est_interieur){
            case(0):
                try{
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case(1):
                if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade, boisson);
        } else {
            if(camarade.getClass()== Serveur.class){
                this.offrir_Verre((Serveur)camarade, boisson);
            } else {
                try{
                    if(boisson.prix_vente>this.porte_monnaie){
                        throw new Exception("Erreur: Vous n'avez pas assez d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Plus assez de boisson");
                        }
                        else{
                        parler("J'aimerai commander " + boisson.name + " "+ " pour " + camarade.obtenir_Prenom(), barman);    
                        super.offrir_Verre(camarade, boisson, barman);
                        this.paye(boisson);
                        barman.servir_Boisson(boisson,this);
                        camarade.parler("Merci beaucoup", this);
                        camarade.boire(boisson);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
                break;
        }
    }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Serveur serveur){
        switch(this.est_interieur){
            case(0):
                try{
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case (1):
             if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade, boisson);
        } else {
            if(camarade.getClass()== Serveur.class){
                this.offrir_Verre((Serveur)camarade, boisson);
            } else {
                try{
                    if(boisson.prix_vente>this.porte_monnaie){
                        throw new Exception("Erreur: Vous n'avez pas assez d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus de cette boissons");
                        }
                    else{
                        parler("J'aimerai commander " + boisson.name, serveur);    
                        super.offrir_Verre(camarade, boisson , serveur);
                        this.paye(boisson);
                        serveur.commander(this,boisson);
                        camarade.parler("Merci beaucoup", this);
                        camarade.boire(boisson);
                    }
                }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
                break;
        }
    }
    
    @Override
    public void offrir_Verre (Barman barman, Boisson boisson){
        switch(this.est_interieur){
            case(0):
                try{
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case(1):
                try{
                    if(boisson.prix_vente>this.porte_monnaie){
                throw new Exception("Erreur: Vous n'avez pas assez d'argent");
                    }
                    else {
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus cette boisson");
                        } else {
                            if(boisson.degre_alcool>0){
                                throw new Exception("Erreur: Il ne peut pas boire de l'alcool");
                            }
                            else {
                            parler("J'aimerai commander " + boisson.name + " mon pote");
                            super.offrir_Verre(barman, boisson);
                            this.paye(boisson);
                            barman.servir_Boisson(boisson, this);
                            this.parler("Vas-y je t'en pris c'est pour toi", barman);
                            barman.parler("Merci beaucoup",this);
                            barman.boire(boisson);
                            }
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
    
    @Override
    public void offrir_Verre (Serveur serveur, Boisson boisson){
        switch(this.est_interieur){
            case(0):
                try{
                    throw new Exception("Erreur:Vous n'êtes pas dans le bar");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case(1):
                try{
                    if(boisson.prix_vente>this.porte_monnaie){
                        throw new Exception("Erreur: Vous n'avez pas assez d'argent");
                    }
                    else {
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus cette boisson");
                        }
                        else {
                            if(boisson.degre_alcool>0){
                                throw new Exception("Erreur: Boisson alcoolisée");
                            }
                            else {
                            super.offrir_Verre(serveur, boisson);
                            parler("J'aimerai commander " + boisson.name + " "+ "mon pote");
                            this.paye(boisson);
                            serveur.commander(this, boisson);
                            this.parler("c'est pour toi mon pote",serveur);
                            serveur.parler("Merci beaucoup",this);
                            serveur.boire(boisson);
                            }
                        }
                            
                    }
                }
                catch(Exception e){
                    System.out.println(e.getClass());
                }
                break;
        }
    }
    
    @Override
    public void tournee_generale(Bar mon_Bar, Boisson boisson){
        try{
            if(mon_Bar.nombre_Client * boisson.prix_vente>this.porte_monnaie){
                throw new Exception("Erreur: Vous n'avez pas assez d'argent");
            }
            else{
                if(mon_Bar.nombre_Client>boisson.prix_vente){
                    throw new Exception("Erreur: Il n'a pas assez de boisson");
                }
                else{
                    for(int compteur=0;compteur<mon_Bar.nombre_Client+1;compteur++){
                        this.offrir_Verre((Client)mon_Bar.client.get(compteur), boisson, mon_Bar.barman);
                        Client servi = (Client)mon_Bar.client.get(compteur);
                        servi.parler(crie);
                        mon_Bar.patron.parler(mon_Bar.patron.crie + " Les affaires reprennent");
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void s_attabler(Table table){
        try{
            if(this.est_interieur == 0){
                throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
            }
            else{
                this.est_Attablé = true;
                table.client.add(this);
                table.place--;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

