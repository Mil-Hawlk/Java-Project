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
    
    public void payerCommande(double pprixCommande, Fournisseur pnomFournisseur,Bar bar)
    {
        if(this.porte_monnaie>=pprixCommande)
        {
            parler("Voilà ton argent "+pnomFournisseur.nomFournisseur);
            this.porte_monnaie-=pprixCommande;
            pnomFournisseur.porte_monnaie+=pprixCommande;
            pnomFournisseur.livrerCommande(bar);
        }
        else
        {
            parler("Je n'ai pas assez d'argent sur moi pour payer le fournisseur, je pique dans la caisse");
            if(this.porte_monnaie+(bar.barman.obtenir_caisse()-100)>=pprixCommande)//100€ de marge
            {
                bar.barman.vider_caisse_pour_commande();
                this.porte_monnaie-=pprixCommande;
                pnomFournisseur.porte_monnaie+=pprixCommande;
                pnomFournisseur.livrerCommande(bar);
            }
            else
            {
                parler("Je ne peux pas payer le fournisseur sans taper dans les 100€ de marge de la caisse, j'annule la commande");
                pnomFournisseur.annulerCommande();
            }
        }
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
    public void offrir_Verre(Humain camarade, Boisson boisson, Serveur serveur){
        this.parler("J'aimerai commander " + boisson.name, serveur.barman);
        serveur.barman.servir_Boisson(boisson,this);
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
    
    public void exclure(Client client){
        client.est_vire=true;
    }
    
    public void ordonner(Client client){
        this.parler("Vous ne devez plus servir " + client.obtenir_Prenom());
        client.est_bourre = true;
    }

    
    /* Ne pas oublier qu'elle peut exclure quelqu'un*/
}
