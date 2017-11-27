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
public class Barman extends Humain implements Tournee_generale{
    private int caisse = 80;
    private Patron patron;
    
    /* Accesseur*/
    protected int obtenir_caisse(){
        return this.caisse;
    }
    
    public Barman(String cprenom, String csurnom, float cporte_monnaie, String ccrie, Patron ppatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.patron=ppatron;
        parler("Je suis le nouveau barman " + this.obtenir_Prenom());
    }
    
    public void passerCommande(Boisson pboisson, int pquantite, Fournisseur pnomFournisseur, Bar pnomBar)
    {
        parler("Salut mon cher "+pnomFournisseur.nomFournisseur+", Je voudrais te commander "+pquantite+pboisson.name+" pour le bar "+pnomBar.name+" , merci !");
        pnomFournisseur.recevoirCommandeEtFacturer(pboisson,pquantite,pnomBar);
    }
    
    protected void servir_Boisson(Boisson boisson, Serveur serveur){
        parler("Rapporte ça au client");
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    protected void servir_Boisson(Boisson boisson, Client client){
        parler("Voici ton verre");
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    protected void servir_Boisson(Boisson boisson, Patron patron){
        parler("Voici ton verre " + patron.obtenir_Prenom());
        boisson.nombre-=1;
    }
    
    @Override
    public void boire(Boisson boisson){
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
    
    @Override
    public void parler(String phrase){
        System.out.print("Barman ");
        super.parler(phrase + " Coco");
    }
    
    @Override
    public void parler(String phrase, Humain camarade){
        System.out.print("Barman ");
        super.parler(phrase + " Coco");
    }
    
    public void offrir_Verre(Client camarade, Boisson boisson, Barman barman){
        try{
            if(camarade.cote_popularite<100 || boisson.nombre<1 ){
                throw new Exception("Impossibilité de servir un verre");
            }
            else{
                this.parler("Tiens mon Loulou, je t'offre " + boisson.name, camarade);
                this.servir_Boisson(boisson,camarade);
                camarade.boire(boisson);
                this.paye(boisson);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /*A chaque tour, le barman vérifie le trop plein de la caisse*/
    protected void vider_caisse(){
        if(this.caisse>500){
            parler("Voici une partie de la caisse patron ", this.patron);
            this.patron.porte_monnaie+=(this.caisse)-100;
            this.caisse=(this.caisse)-100;
            this.patron.recuperer_Caisse(this);
        }
    }
    
    protected void vider_caisse_pour_commande(){
        
        parler("Voici une partie de la caisse patron", this.patron);
        this.patron.recuperer_Caisse(this);
        while(this.caisse>100){
        this.patron.porte_monnaie+=100;
        this.caisse=(this.caisse)-100;   
        }      
    }
    
     @Override
    public void tournee_generale(Bar mon_Bar, Boisson boisson){
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
                    for(int compteur=0;compteur<mon_Bar.nombre_Client+1;compteur++){
                        this.servir_Boisson(boisson,(Client)mon_Bar.client.get(compteur));
                        Client a_servir= (Client)mon_Bar.client.get(compteur);
                        a_servir.boire(boisson);
                        this.paye(boisson);
                        Client servi = (Client)mon_Bar.client.get(compteur);
                        servi.parler(servi.crie);
                        mon_Bar.patron.parler(mon_Bar.patron.crie + " Les affaires reprennent");
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
       
}
