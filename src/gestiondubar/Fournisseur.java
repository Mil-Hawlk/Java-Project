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
public class Fournisseur extends Humain{
    String nomFournisseur;
    Boisson nomBoissonCommande;
    int quantiteCommande = 0;
    
    public Fournisseur(String pprenom, String psurnom, float pporte_monnaie, String pcri)
    {
        super(pprenom,psurnom,pporte_monnaie,pcri);
        this.nomFournisseur = pprenom;
    }

    public void recevoirCommandeEtFacturer(Boisson pboisson, int pquantite, Bar pnomBar)
    {
        parler("Bien reçu, j'envoie la facture à la patronne !");
        this.nomBoissonCommande = pboisson;
        this.quantiteCommande = pquantite;
        double prixAPayer = pquantite * pboisson.prix_achat;
        parler("Il y en aura pour "+prixAPayer+"euros !");
        pnomBar.patron.payerCommande(prixAPayer,this,pnomBar);
    }
    
    public void livrerCommande(Bar pnomBar)
    {
        int i=0;
        for(i=0;i<pnomBar.boisson.length;i++)
        {
            if(pnomBar.boisson[i].name==this.nomBoissonCommande.name)
            {
                pnomBar.boisson[i].nombre+=this.quantiteCommande;
            }
        }
        this.nomBoissonCommande = null;
        this.quantiteCommande = 0;
    }
    
    public void annulerCommande()
    {
        this.nomBoissonCommande = null;
        this.quantiteCommande = 0;
    }
    
    @Override
    public void parler(String phrase){
        System.out.print("Fournisseur ");
        super.parler(phrase);
    }
    
    @Override
    public void parler(String phrase, Humain humain){
        System.out.print("Fournisseur ");
        super.parler(phrase);
    }
}
