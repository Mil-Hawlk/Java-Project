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
public class Barman extends Humain{
    private int caisse = 1000;
    private Patron patron;
    
    /* Accesseur*/
    public int obtenir_caisse(){
        return this.caisse;
    }
    
    public Barman(String cprenom, String csurnom, float cporte_monnaie, String ccrie, Patron ppatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.patron=ppatron;
        parler("Je suis le nouveau barman " + this.obtenir_Prenom());
    }
    
    /*public void remplir_Stock(Boisson boisson, int nombre){
        boisson.nombre+=nombre;
        caisse -= nombre * boisson.prix_achat;
    }*/
    
    public void passerCommande(Boisson pboisson, int pquantite, Fournisseur pnomFournisseur, Bar pnomBar)
    {
        parler("Salut mon cher "+pnomFournisseur+", Je voudrais te commander "+pquantite+pboisson+" pour le bar "+pnomBar+" , merci !");
        pnomFournisseur.recevoirCommandeEtFacturer(pboisson,pquantite,pnomBar);
    }
    
    public void servir_Boisson(Boisson boisson, Serveur serveur){
        parler("Rapporte ça au client");
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    public void servir_Boisson(Boisson boisson, Client client){
        parler("Voici ton verre");
        boisson.nombre-=1;
        caisse+=boisson.prix_vente;
        vider_caisse();
    }
    
    public void servir_Boisson(Boisson boisson, Patron patron){
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
        if(camarade.cote_popularite>100){
            this.parler("Tiens mon Loulou, je t'offre " + boisson.name, camarade);
            this.servir_Boisson(boisson,camarade);
            camarade.boire(boisson);
            this.paye(boisson);
        }
    }
    
    protected void vider_caisse(){
        if(this.caisse>100){
            parler("Voici une partie de la caisse patron ", this.patron);
            this.patron.porte_monnaie+=(this.caisse)-100;
            this.caisse=(this.caisse)-100;
            this.patron.recuperer_Caisse(this);
        }
    }
       
}
