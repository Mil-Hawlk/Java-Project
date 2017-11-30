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

public class Client extends Humain implements Tournee_generale {
    
    protected Boisson boisson_favorite;
    protected Boisson boisson_secours;
    protected int niveau_alcool = 0;
    protected Sexe_Client sexe;
    protected int est_bourre = 0;
    protected int est_interieur = 0; 
    protected boolean est_Attablé = false;
    
    /**
     * 
     * @param cprenom
     * @param csurnom
     * @param cporte_monnaie
     * @param ccrie
     * @param pboisson_favorite
     * @param pboisson_secours
     * @param attribut 
     * @exception Exception
     */
    protected Client(String cprenom, String csurnom, int cporte_monnaie, 
            String ccrie, Boisson pboisson_favorite, 
            Boisson pboisson_secours, Object attribut){
        /*Constructeur des clients qui hérite des humains, exception si le 
        client n'est ni un homme ni une femme c'est à dire si l'objet place
         en attribut n'est ni de la classe TSHirt ni Bijoux*/
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        try{
            if(attribut.getClass()!= TShirt.class && attribut.getClass()!=
                    Bijoux.class){
                throw new Exception("Ceci n'est ni un homme ni une femme mais "
                        + "reste un humain");
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
    
    /**
     * 
     * @param new_attribut 
     * @exception Exception
     */
    public void changer_Sexe(Object  new_attribut){
        /*Fonction qui permet pour un client de changer de sexe seulement si l'
        attribut qu'il place en parametre correspond soit à un objet de la 
        classe TShirt ou Bijoux qui permette de distinguer les hommes des 
        femmes*/
        try{
            if(new_attribut.getClass()!= TShirt.class && 
                    new_attribut.getClass()!=Bijoux.class ){
                throw new Exception("Il ne peut pas changer de sexe mauvais "
                        + "attribut");
            }
            else{
                this.sexe = new Sexe_Client(new_attribut);  
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param phrase
     * @param serveur 
     * @exception Exception
     */
    public void parler(String phrase, Serveur serveur){
        /*Fonction permettant de parler au serveur, exception si le client n'est
        pas dans le bar*/
        try{
            while(this.est_interieur == 0){
                throw new Exception("Erreur: Vous n'êtes pas dans le bar");
            }
            if(this.niveau_alcool>45 ){ /* Si le client a un taux superieur
                il commence à parler bizarrement*/
                if(this.sexe.sexe == "Homme" && serveur.sexe.sexe=="Femme")
                {
                    super.parler(phrase + " Poupée");}
                else{
                    if(this.sexe.sexe == "Femme" && 
                            serveur.sexe.sexe=="Homme"){
                        super.parler(phrase+" Bo gosse");
                    }
                    else{
                        super.parler(phrase);
                        }
                    }
                }
            else{
                super.parler(phrase); // il parle normalement 
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * 
     * @param camarade
     * @param barman
     * @exception Exception
     */
    
    public void se_Faire_Offrir(Humain camarade, Barman barman){  
        /*Fonction permetant de se faire offrir un verre en demandant à un ami, 
        exception si le client n'est pas dans le bar*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'ête pas dans le bar");
                case(1):
                    if(this.cote_popularite>50){ /*il accepte uniquement si 
                        la popularité du client est superieur à 50*/
                        if(this.boisson_favorite.nombre>0){ /*il offre la 
                            boisson favorite s'il en reste*/
                            parler("Hey tu ne voudrais pas m'offrir un "
                                    + "verre",camarade);
                            camarade.offrir_Verre(this, 
                                    this.boisson_favorite,barman);
                            this.cote_popularite-=50; /* sa popularité 
                            change ensuite*/
                            }
                        else{
                            if(this.boisson_secours.nombre>0){ /*il offre 
                                la boisson de secour s'il n'a plus sa 
                                boisson favorite*/
                                parler("Hey tu ne voudrais pas m'offrir un"
                                        + "verre",camarade);
                                camarade.offrir_Verre(this, 
                                        this.boisson_secours,barman);
                                this.cote_popularite-=50;
                            } 
                            else {
                                camarade.parler("Non désolé mon pote",this);
                                /*s'il n'a pas l'une des deux boissons 
                                favorite ou qu'il n'est pas assez populaire 
                                il abandonne*/
                            }
                        }
                    } 
            }
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param boisson
     * @param barman 
     * @exception Exception
     */
    public void commander(Boisson boisson, Barman barman){
        /*Fonction qui permet de commander une boisson en demandant au barman
        exception si le client est à l'interieur, s'il n'a pas assez d'argent,
        s'il n'a plus de cette boisson*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
                case(1):
                    if(this.porte_monnaie<boisson.prix_vente){
                            throw new Exception ("Vous n'avez pas assez "
                                    + "d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception ("Erreur: Il n'a plus cette "
                                    + "boisson"); 
                        }
                        else{
                            if(this.est_bourre == 1){
                                barman.parler("Désolé je ne peux plus te servir"
                                        ,this); /*Le patron a interdit au 
                                serveur et au barman de laisser boire ce client
                                */
                            }
                            else{
                                this.parler("J'aimerai commander " + 
                                        boisson.name,barman);
                                this.paye(boisson);
                                barman.servir_Boisson(boisson,this);
                                this.boire(boisson);
                            }
                        }
                    }
                    break;               
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  

    /**
     * 
     * @param boisson
     * @param serveur 
     * @exception Exception
     */
public void commander(Boisson boisson, Serveur serveur){
        /*Fonction permettant de commander une boisson au serveur, exception si
        le client n'est pas à l'interieur ou s'il n'a pas assez d'argent ou s'il
        n'a plus assez de cette boisson*/
        try{
            while(this.est_interieur==0){
                throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
            }
            while(this.porte_monnaie<boisson.prix_vente){
                throw new Exception("Erreur: Vous n'avez pas assez d'argent");
            }
            while(boisson.nombre<1){
                throw new Exception("Erreur: Il n'a plus assez de boisson");
            }                /*Si le client est bourre il ne peut pas le servir*/

            if(this.est_bourre==0){
                serveur.parler("Décolé je ne peux plus te servir", this);
            }
            else{
                 if(this.sexe.sexe!=serveur.sexe.sexe && this.niveau_alcool/10>
                         serveur.sexe.coefficient){
                    /*Si le client et le serveur ont des sexes opposés et que le
                     rapport entre le niveau d'alccol et leur coefficient est de
                     10 il paie un verre*/
                    this.paye(boisson);
                    serveur.commander(this,boisson);  
                    this.boire(boisson);
                    this.offrir_Verre(serveur, boisson);
                } 
                else{
                    if(this.sexe.sexe==serveur.sexe.sexe && 
                            serveur.sexe.coefficient < this.niveau_alcool/20){
                        /*Si le client et le serveur sont de même sexe opposés 
                        et que le rapport entre le niveau d'alcool et leur 
                        coefficient est de 20 il annule sa commande */
                        parler("En fait je vais annuler ma commande je pense", 
                                serveur);
                    }
                    else{
                        this.paye(boisson);
                        serveur.commander(this,boisson);  
                        this.boire(boisson);
                    }
                }
            }    
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }     
    
    /**
     * 
     * @param mon_Bar 
     */
        public void quitter_Bar(Bar mon_Bar){
            /*Fonction qui permet de quitter le bar*/
            mon_Bar.client_virer(this);
        }
    
    /**
     * 
     */
    @Override
    public void se_Presenter(){
        super.se_Presenter();
        parler("Ma boisson favorite est " + this.boisson_favorite.name);
    }
    
    /**
     * 
     * @param boisson 
     * @exception Exception
     */
    @Override
    protected void boire(Boisson boisson){
        /*exception si le client n'est pas dans le bar*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                case(1):
                    super.boire(boisson);
                    this.niveau_alcool+=boisson.degre_alcool;
                    break;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param camarade
     * @param boisson
     * @param barman 
     * @exception Exception
     */
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        /*Fonction pour offrir un verre à un ami en demandant au barman, 
        exception s'il n'est pas dans le bar ou s'il n'a pas asez d'argent ou 
        s'il n'a plus de boisson*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                
                case(1):
                    if(camarade.getClass()==Barman.class){ /*Appel de la 
                        fonction avec le Barman si le camarade est le Barman*/
                        this.offrir_Verre((Barman)camarade, boisson);}
                    else{
                        if(camarade.getClass()== Serveur.class){ /*Appel de la 
                            fonction avec le Serveur si le camarade est un 
                            Serveur*/
                            this.offrir_Verre((Serveur)camarade, boisson);} 
                        else{
                            if(boisson.nombre<1){
                                throw new Exception("Erreur: Plus assez de "
                                        + "boisson");
                            }
                            else{
                                parler("J'aimerai commander " + boisson.name +
                                        " pour " + camarade.obtenir_Prenom(), 
                                        barman);    
                                super.offrir_Verre(camarade, boisson, barman);
                                this.paye(boisson);
                                barman.servir_Boisson(boisson,this);
                                camarade.parler("Merci beaucoup", this);
                                camarade.boire(boisson);
                            }
                        }
                    }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param camarade
     * @param boisson
     * @param serveur 
     * @exception Exception
     */
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Serveur serveur){
        /*Fonction qui permet d'offrir un verre en demandant au serveur, tres 
        ressemblante àn celle précédemment */
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                case(1):
                    if(camarade.getClass()==Barman.class){
                        this.offrir_Verre((Barman)camarade, boisson);
                    }
                    else{
                        if(camarade.getClass()== Serveur.class){
                            this.offrir_Verre((Serveur)camarade, boisson);
                        }
                        else{
                            if(boisson.prix_vente>this.porte_monnaie){
                                throw new Exception("Erreur: Vous n'avez pas "
                                        + "assez d'argent");
                            }
                            else{
                                if(boisson.nombre<1){
                                    throw new Exception("Erreur: Il n'a plus de"
                                            + " cette boissons");
                                }
                                else{
                                    parler("J'aimerai commander " + 
                                            boisson.name, serveur);    
                                    super.offrir_Verre(camarade, boisson , 
                                            serveur);
                                    this.paye(boisson);
                                    serveur.commander(this,boisson);
                                    camarade.parler("Merci beaucoup", this);
                                    camarade.boire(boisson);
                                }
                            }
                        }
                    }
                break;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param barman
     * @param boisson 
     * @exception Exception
     */
    @Override
    public void offrir_Verre (Barman barman, Boisson boisson){
        /*Fonction permettant d'offrir un verre au Barman, exception si le 
        client n'est pas à l'interieur ou s'il n'a pas assez d'argent ou s'il 
        n'a plus cette boisson ou encore si la boisson est alcoolisée*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception("Erreur: Vous n'êtes pas dans le bar");
                case(1):
                    if(boisson.prix_vente>this.porte_monnaie){
                        throw new Exception("Erreur: Vous n'avez pas assez "
                                + "d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus cette "
                                    + "boisson");
                        } 
                        else{
                            if(boisson.degre_alcool>0){
                                throw new Exception("Erreur: Il ne peut pas "
                                        + "boire de l'alcool");
                            }
                            else {
                            parler("J'aimerai commander " + boisson.name + 
                                    " mon pote");
                            super.offrir_Verre(barman, boisson);
                            this.paye(boisson);
                            barman.servir_Boisson(boisson, this);
                            this.parler("Vas-y je t'en pris c'est pour toi", 
                                    barman);
                            barman.parler("Merci beaucoup",this);
                            barman.boire(boisson);
                            }
                        }
                    }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param serveur
     * @param boisson 
     * @exception Exception
     */
    @Override
    public void offrir_Verre (Serveur serveur, Boisson boisson){
        /*Fonction permettant d'offrir un Verre au serveur, identique à celle 
        pour offrir un verre au barman*/
        try{
            switch(this.est_interieur){
                case(0):
                    throw new Exception ("Erreur: Vous n'êtes pas dans le bar");
                case(1):
                    if(boisson.prix_vente>this.porte_monnaie){
                        throw new Exception("Erreur: Vous n'avez pas assez "
                                + "d'argent");
                    }
                    else{
                        if(boisson.nombre<1){
                            throw new Exception("Erreur: Il n'a plus cette "
                                    + "boisson");
                        }
                        else {
                            if(boisson.degre_alcool>0){
                                throw new Exception("Erreur: Boisson "
                                        + "alcoolisée");
                            }
                            else {
                            super.offrir_Verre(serveur, boisson);
                            parler("J'aimerai commander " + boisson.name 
                                    +" mon pote");
                            this.paye(boisson);
                            serveur.commander(this, boisson);
                            this.parler("c'est pour toi mon pote",serveur);
                            serveur.parler("Merci beaucoup",this);
                            serveur.boire(boisson);
                            }
                        }
                            
                    }
                    break;
        
            }     
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param mon_Bar
     * @param boisson 
     * @exception Exception
     */
    @Override
    public void tournee_generale(Bar mon_Bar, Boisson boisson){
        /*Fonction permettant d'offrir un tournee generale sauf s'il n'a pas 
        assez de boissons ou que le nombre de boisson qu'il souhaite offrir 
        n'est pas disponible en stock*/
        try{
            if(mon_Bar.nombre_Client * boisson.prix_vente>this.porte_monnaie){
                throw new Exception("Erreur: Vous n'avez pas assez d'argent");
            }
            else{
                if(mon_Bar.nombre_Client>boisson.prix_vente){
                    throw new Exception("Erreur: Il n'a pas assez de boisson");
                }
                else{
                    for(int compteur=0;compteur<mon_Bar.nombre_Client+1;
                            compteur++){ // ¨Il récupere l'ensemble des clients
                        this.offrir_Verre((Client)mon_Bar.client.get(compteur), 
                                boisson, mon_Bar.barman); /*il offre le verre a 
                        son ami*/
                        Client servi = (Client)mon_Bar.client.get(compteur); 
                        //On copie le client pour qu'il puisse crier
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
    
    /**
     * 
     * @param table 
     * @exception Exception
     */
    public void s_attabler(Table table){
        /*Fonction qui permet à un client de s'attabler afin de jouer à la 
        Belote, exception s'il n'est pas dans le bar*/
        try{
            if(this.est_interieur == 0){
                throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
            }
            else{
                if(this.est_Attablé){
                    throw new Exception("Erreur: Vous êtes deja attablé");
                }
                else{
                    this.est_Attablé = true; 
                table.client.add(this); /* on garde également en mémoire que le 
                client s'est attablé*/
                table.place--;
                }  
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param table
     * @exception Exception
     */
    public void quitter_Table(Table table){
        /*Fonction qui permet à un client de quitter sa table deBelote, 
        exception s'il n'est pas dans le bar*/
        try{
            if(this.est_interieur == 0){
                throw new Exception("Erreur: Vous n'êtes pas à l'interieur");
            }
            else{
                if(!this.est_Attablé){
                    throw new Exception("Erreur: Vous n'êtes pas attablé");
                }
                else{
                    this.est_Attablé = true; 
                table.quitter(this); /*fonctio qui permet de quitter la table*/
                table.place++;
                }  
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

