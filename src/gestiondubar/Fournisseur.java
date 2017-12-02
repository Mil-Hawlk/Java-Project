/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondubar;

/**
 *
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */

public class Fournisseur extends Humain{
    Boisson nomBoissonCommande;
    int quantiteCommande = 0;
    
    /**
     * 
     * @param pprenom
     * @param psurnom
     * @param pporte_monnaie
     * @param pcri 
     */
    public Fournisseur(String pprenom, String psurnom, float pporte_monnaie, String pcri)
    {
        super(pprenom,psurnom,pporte_monnaie,pcri);
    }

    /**
     * 
     * @param pboisson
     * @param pquantite
     * @param pnomBar 
     */
    public void recevoirCommandeEtFacturer(Boisson pboisson, int pquantite, 
            Bar pnomBar){
        /*Fonction qui permet de recevoir une commande et de la facturer 
            automatiquement*/
        parler("Bien reçu, j'envoie la facture à la patronne !");
        this.nomBoissonCommande = pboisson;
        this.quantiteCommande = pquantite;
        double prixAPayer = pquantite * pboisson.prix_achat; /* prix de la 
        commande*/
        parler("Il y en aura pour "+prixAPayer+"€");
        pnomBar.patron.payerCommande(prixAPayer,this,pnomBar);
    }
    
    /**
     * 
     * @param pnomBar 
     */
    public void livrerCommande(Bar pnomBar){
        /*Fonction qui permet de livrer la commande lorsque celle ci a été 
        réglé par la patronne*/
        for(int i=0;i<pnomBar.boisson.length;i++) /*il parcourt le tableau afin 
                de trouver la boisson dans le bar*/
        {
            if(pnomBar.boisson[i].name==this.nomBoissonCommande.name)/*Lorsqu'il
                trouve bien la boisson*/
            {
                pnomBar.boisson[i].nombre+=this.quantiteCommande;
                /*Incrémentation du nombre de boissons*/
            }
        }
        this.nomBoissonCommande = null; // reinitialisation de la commande
        this.quantiteCommande = 0;
    }
    
    /**
     * 
     */
    public void annulerCommande(){
        /*Fonction qui permet d'annuler la commande*/
        this.nomBoissonCommande = null;
        this.quantiteCommande = 0;
    }
    
    /**
     * 
     * @param phrase 
     */
    @Override
    public void parler(String phrase){
        /*Fonction pour parler tout seul*/ 
        System.out.print("Fournisseur ");
        super.parler(phrase);
    }
    
    /**
     * 
     * @param phrase
     * @param humain 
     */
    @Override
    public void parler(String phrase, Humain humain){
        /*Fonction pour parler à quelqu'un*/
        System.out.print("Fournisseur ");
        super.parler(phrase);
    }
}
