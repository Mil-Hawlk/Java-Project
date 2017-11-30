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
public class Barman extends Humain implements Tournee_generale{
    private int caisse = 80; // 80€ initialement dans la caisse
    private final Patron patron; // Son patron qui ne peut pas changer
   

    /**
     * 
     * @param cprenom
     * @param csurnom
     * @param cporte_monnaie
     * @param ccrie
     * @param ppatron 
     */
    public Barman(String cprenom, String csurnom, float cporte_monnaie, 
            String ccrie, Patron ppatron){
        /*Constructeur permettant de créer un barman*/
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.patron=ppatron;
        parler("Je suis le nouveau barman " + this.obtenir_Prenom());
    }
    
     /**
     * 
     * @return int 
     */
    protected int obtenir_caisse(){
        /* Accesseur afin d'obtenir la caisse qui est confidentielle*/
        return this.caisse;
    }
    
    /**
     * 
     * @param pboisson
     * @param pquantite
     * @param pnomFournisseur
     * @param pnomBar 
     */
    public void passerCommande(Boisson pboisson, int pquantite, Fournisseur 
            pnomFournisseur, Bar pnomBar)         
    {
        /*Fonction permettant de passer une commande de boissons au fournisseur
        */
        parler("Salut mon cher "+pnomFournisseur.nomFournisseur+", Je voudrais "
                + "te commander "+pquantite+pboisson.name+" pour le bar "
                +pnomBar.name+" , merci !");
        pnomFournisseur.recevoirCommandeEtFacturer(pboisson,pquantite,pnomBar);
    }
    
    /**
     * 
     * @param boisson
     * @param serveur 
     */
    protected void servir_Boisson(Boisson boisson, Serveur serveur){
        /*Fonction permettant de servir une boisson à un serveur*/
        parler("Rapporte ça au client s'il te plait " + serveur.obtenir_Surnom()
        );
        boisson.nombre-=1; // le nombre de boissons du stock diminue
        caisse+=boisson.prix_vente; // la caisse augmente du prix des boissons
        vider_caisse(); /* il appelle cette fonction qui permet de vider une 
        partie de la caisse s'il a trop d'argent dedans*/
    }
    
    /**
     * 
     * @param boisson
     * @param client 
     */
    protected void servir_Boisson(Boisson boisson, Client client){
        /*Fonction permettant de servir une boisson directement au client*/
        parler("Voici ton verre");
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    /**
     * 
     * @param boisson
     * @param patron 
     */
    protected void servir_Boisson(Boisson boisson, Patron patron){
        /*Fonction permettant de servir une boissonau patron*/
        parler("Voici ton verre " + patron.obtenir_Prenom());
        boisson.nombre-=1; // la patronne ne paie pas, juste la boisson diminue
    }
    
    /**
     * 
     * @param boisson 
     * @exception Exception 
     */
    @Override
    public void boire(Boisson boisson){
        /*Exception si la boisson que le barman boit est alcoolisée*/
        try{
            if(boisson.degre_alcool>0){
                throw new Exception("Le barman ne boit pas d'alcool");
            }
            else{
                super.boire(boisson);
                boisson.nombre-=1; // il ne paie pas juste la boisson diminue
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param phrase 
     */
    @Override
    public void parler(String phrase){
        System.out.print("Barman ");
        super.parler(phrase + " Coco"); /* On rajoute coco à la fin de toutes
        les phrases du barman*/
    }
    
    /**
     * 
     * @param phrase
     * @param camarade 
     */
    @Override
    public void parler(String phrase, Humain camarade){
        System.out.print("Barman ");
        super.parler(phrase + " Coco"); /* On rajoute coco à la fin de toutes
        les phrases du barman*/
    }
    
    /**
     * 
     * @param camarade
     * @param boisson
     * @param barman 
     * @exception Exception
     */
    public void offrir_Verre(Client camarade, Boisson boisson, Barman barman){
        /*Exception si le client n'est pas assez populaire ou bien s'il n'a plus
        de cette boisson*/
        try{
            if(camarade.cote_popularite<100){
                throw new Exception("Erreur: Cote de popularité insuffisante");
            }
            else{
                if(boisson.nombre<1){
                    throw new Exception("Erreur: Il n'a plus cette boisson");
                }
                else{
                    this.parler("Tiens mon Loulou, je t'offre " + boisson.name, 
                        camarade);
                    this.servir_Boisson(boisson,camarade); 
                    camarade.boire(boisson);
                    this.paye(boisson); /* il paie cependant la boisson qu'il a
                    offerte à son ami*/
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     */
    protected void vider_caisse(){
        /*Fonction permettant de vider la caisse de 100€ si elle est superieur
        à 500€*/
        if(this.caisse>500){
            parler("Voici une partie de la caisse patron ", this.patron);
            this.patron.porte_monnaie+=(this.caisse)-100;
            this.caisse=(this.caisse)-100;
            this.patron.recuperer_Caisse(this);
        }
    }
    
    /**
     * 
     * @exception Exception
     */
    protected void vider_caisse_pour_commande(){
        /*Fonction permettant de vider la caisse afin de ne laisser que 100€
        pour que le patron ait assez d'argent dans son portefeuille*/
        try{
            if(this.obtenir_caisse()<100){
                throw new Exception("Erreur: Caisse inferieur à 100€");
            }
            else{
                parler("Voici une partie de la caisse patron", this.patron);
                this.patron.recuperer_Caisse(this);
                while(this.caisse>100){
                this.patron.porte_monnaie+=100;
                this.caisse=(this.caisse)-100; 
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
     * @param boisson 
     * @exception Exception
     * @exception Exception
     */
    @Override
    public void tournee_generale(Bar mon_Bar, Boisson boisson){
        /*Fonction permettant d'offrir une tournée générale à tous les clients 
        sauf si le barman n'a pas assez de boissons ou que le nombre de boisson 
        qu'il souhaite offrir n'est pas disponible en stock*/
        try{
            if(mon_Bar.nombre_Client * boisson.prix_vente>this.porte_monnaie){
                throw new Exception("Erreur: Vous n'avez pas assez d'argent");
            }
            else{
                if(mon_Bar.nombre_Client>boisson.nombre){
                    throw new Exception("Erreur: Il n'a pas assez de boisson");
                }
                else{
                    this.parler("Tournée générale !!!!!!!");
                    for(int compteur=0;compteur<mon_Bar.nombre_Client+1;
                            compteur++){ /* compteur allant de 0 jusqu'au nombre
                        de client*/
                        this.servir_Boisson(boisson,
                                (Client)mon_Bar.client.get(compteur));/*le 
                        barman sert le client*/
                        /*Ensuite nous créons une copie du client*/
                        Client a_servir= (Client)mon_Bar.client.get(compteur);
                        a_servir.boire(boisson); // cette copie boit
                        this.paye(boisson); // le barman paie
                        a_servir.parler(a_servir.crie);
                    }
                    mon_Bar.patron.parler(mon_Bar.patron.crie + " Les affaires "
                            + "reprennent");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
       
}
