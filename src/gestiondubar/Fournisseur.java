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
    float argentRecupere = 0;
    
    public Fournisseur(String pprenom, String psurnom, float pporte_monnaie, String pcri)
    {
        super(pprenom,psurnom,pporte_monnaie,pcri);
        this.nomFournisseur = pprenom;
        this.argentRecupere = pporte_monnaie;
    }
    
    @Override
    public void parler(String phrase){
        System.out.print("Serveur ");
        super.parler(phrase);
    }
    
    public void recevoirCommandeEtFacturer(Boisson pboisson, int pquantite, Bar pnomBar)
    {
        parler("Bien reçu, j'envoie la facture à la patronne !");
        double prixAPayer = pquantite * pboisson.prix_achat;
        pnomBar.patron.payerCommande(prixAPayer);
    }
}
