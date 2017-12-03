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
public class Patron extends Client {
    
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
    public Patron(String cprenom, String csurnom, int cporte_monnaie,
            String ccrie, Boisson pboisson_favorite,
            Boisson pboisson_secours, Object attribut)
    {
        // Constructeur pour le patron
        super(cprenom,csurnom,cporte_monnaie,ccrie,pboisson_favorite,
                pboisson_secours,attribut);
        try{
            if(attribut.getClass()!=Bijoux.class){
                throw new Exception("Erreur: Mauvais sexe pour le patron");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        super.est_interieur = 1;
        
    }
    
    /**
     * 
     * @param pprixCommande
     * @param pnomFournisseur
     * @param bar 
     * @Exception
     */
    protected void payerCommande(double pprixCommande, 
            Fournisseur pnomFournisseur,Bar bar)
            /*Fonction permettant de régler la commande demander au fournisseur,
            elle est protégée car toute cette partie se fait sans interférence 
            de l'utilisateur, exception si la patronne n'a pas assez d'argent*/
    {
        try{
            if(this.porte_monnaie>=pprixCommande)
        {
            parler("Voilà ton argent "+pnomFournisseur.getPrenom());
            this.porte_monnaie-=pprixCommande; /* diminution de l'argent de la 
            patronne */
            pnomFournisseur.porte_monnaie+=pprixCommande; /*Augmentation du 
            porte feuille du fournisseur*/
            pnomFournisseur.livrerCommande(bar); /*Le fournisseur livre apres 
            avoir été réglé*/
        }
            else
        {
            parler("Je n'ai pas assez d'argent pour payer le fournisseur, "
                    + "je pique dans la caisse"); /*Si la patronne n'a pas assez
            elle regarde si elle a assez dans la caisse*/
            bar.barman.vider_caisse_pour_commande();
            if(this.porte_monnaie>pprixCommande) /*Si apres avoir vider la 
                caisse la patronne a assez d'argent elle regle la facture*/
            {
                this.porte_monnaie-=pprixCommande;
                pnomFournisseur.porte_monnaie+=pprixCommande;
                pnomFournisseur.livrerCommande(bar);
            }
            else /*Sinon exception elle n'a pas assez d'argent*/
            {
                pnomFournisseur.annulerCommande();
                throw new Exception("Erreur: Annulation de la commande, manque "
                        + "d'argent");
            }
        }
    }
        catch(Exception e) {
        System.out.println(e.getMessage());
        }
    
   }
    /**
     * 
     * @param barman 
     */
    protected void recuperer_Caisse(Barman barman){
        /*Fonciton permettant de remercier le barman apres avoir reçu la caisse
        */
        super.parler("Merci bon boulot", barman);
    }
    
    /**
     * 
     * @param client 
     * @exception Exception
     */
    public void exclure(Client client, Bar mon_Bar){
        /*Fonction permettant d'exclure un client qui serait trop énervé, 
        exception si le client est deja viré*/
        try{
            if(client.est_interieur == 0){
                throw new Exception("Erreur: Le client est deja viré");
            }
            else{
                this.parler("Tu es viré de ce bar", client);
                client.est_interieur=0;
                client.quitter_Bar(mon_Bar);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param client 
     * @exception Exception
     */
    public void ordonner(Client client){
        /*Fonction permettant d'empecher un client d'être servi, exception s'il 
        ne peut deja plus boire*/
        try{
            if(client.est_bourre== 1){
                throw new Exception("Erreur: Le client ne peut déja plus etre "
                        + "servi");
            }
            else{
                this.parler("Vous ne devez plus servir " + client.getPrenom());
                client.est_bourre = 1;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }  
    
    /**
     * 
     * @param client 
     * @exception Exception
     */
    public void pardonner(Client client){
        /*Fonction authorisant de nouveau un client à boire, exception s'il n'a 
        pas été empéché préalablement*/
        try{
            if(client.est_bourre==0){
                throw new Exception("Erreur: Le client n'est pas interdit de "
                        + "boisson");
            }
            else{
                client.est_bourre=0;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param phrase 
     */
    @Override
    public void parler(String phrase){
        System.out.print("Patronne ");
        super.parler(phrase);
    }
    
    /**
     * 
     * @param phrase
     * @param humain 
     */
    @Override
    public void parler(String phrase, Humain humain){
        System.out.print("Patronne ");
        super.parler(phrase);
    }
    
    /**
     * 
     * @param new_attribut 
     * @exception Exception
     */
    @Override
    public void changer_Sexe(Object  new_attribut){
        /*Fonction permettant de changer de sexe, exceptino si son attribut 
        n'est pas un bijoux*/
        try{
            if(new_attribut.getClass() != Bijoux.class){
                throw new Exception("Erreur: Ceci n'est pas une femme");
            }
            else {
                super.changer_Sexe(new_attribut);
            }
        }
        catch (Exception e){
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
        /*Fonction permettant d'offrir un verre à un ami en demandant au barman
        , exception s'il n'a plus assez de cette boisson*/
        if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade,boisson);
        } else {
            if(camarade.getClass()==Serveur.class){
                this.offrir_Verre((Serveur)camarade,boisson);
            } else {
                try{
                    if(boisson.nombre<1){
                        throw new Exception("Erreur: Il n'a plus de cette "
                                + "boisson");
                    } else {
                        this.parler("J'aimerai commander " + boisson.name, 
                                barman);
                        barman.servir_Boisson(boisson,this);
                        camarade.boire(boisson); 
                    }                 
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
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
        /*Fonction identique à celle précédemment mais en demandant au serveur*/
        if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade,boisson);
        } else {
            if(camarade.getClass()==Serveur.class){
                this.offrir_Verre((Serveur)camarade,boisson);
            } else {
                try{
                    if(boisson.nombre<1){
                        throw new Exception("Erreur: il n'a plus de cette "
                                + "boisson");
                    } else {
                        this.parler("J'aimerai commander " + boisson.name, 
                                serveur.barman);
                        serveur.barman.servir_Boisson(boisson,this);
                        camarade.boire(boisson); 
                    }                 
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        } 
    }
    
    /**
     * 
     * @param serveur
     * @param boisson 
     * @exception Exception
     */
    @Override
    public void offrir_Verre(Serveur serveur, Boisson boisson){
        /*Fonction permettant d'offrir un verre mais à un serveur maintenant,
        exception s'il n'a plus assez de boisson ou si celle-ci est alcoolisée*/
        try{
            if(boisson.nombre<1){
                throw new Exception("Erreur: Il n'a plus de cette boisson");
            }
            else{
               if(boisson.degre_alcool>0){
                   throw new Exception("Erreur: Il ne peut pas boire de "
                           + "l'alcool");
               }
               else{
                    this.parler("J'aimerai commander" + boisson.name, serveur);
                    serveur.parler("Je vais vous chercher ça patron");
                    serveur.barman.servir_Boisson(boisson,this);
                    this.parler("Aller je te l'offre mon petiot", serveur);
                    serveur.parler("Merci beaucoup patron");
                    serveur.boire(boisson);
               }
            }
        }
        catch( Exception e){
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
    public void offrir_Verre(Barman barman, Boisson boisson){
        /*Fonction identique à précédemment mais en offrant au barman*/
        try{
            if(boisson.nombre<1){
                throw new Exception("Erreur: Il n'a plus de cette boisson");
            }
            else{
                if(boisson.degre_alcool>0){
                    throw new Exception("Erreur: Il ne peut pas boire de "
                            + "l'alcool");
                }
                else{
                    this.parler("J'aimerai commander" + boisson.name, barman);
                    barman.servir_Boisson(boisson, this);
                    this.parler("Aller prend la je te l'offre mon petiot");
                    barman.parler(crie + " Merci beaucoup", this);
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
    @Override
    public void commander(Boisson boisson, Barman barman){
        /*Fonction permettant de commander un verre au barman sans payer, 
        exception s'il n'a plus assez de boissons*/
        try{
            if(boisson.nombre<1){
                throw new Exception("Impossibilité de servir cette boisson");
            } else {
                this.parler("J'aimerai commander " + boisson.name, barman);
                barman.servir_Boisson(boisson,this);
                this.boire(boisson);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param boisson
     * @param serveur 
     * @exception Exception
     */
    @Override
    public void commander(Boisson boisson, Serveur serveur){
        /*Fonction identique à précédemment mais en demandant au serveur*/
        try{
            if(boisson.nombre<1){
                throw new Exception("Erreur: Il n'a plus de boisson");
            } else {
                this.parler("j'aimerai commander " + boisson.name,serveur);
                serveur.barman.servir_Boisson(boisson, this);
                this.boire(boisson);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * 
     * @param mon_Bar 
     * @exception Exceptino
     */
    @Override
    public void quitter_Bar(Bar mon_Bar){
        /*Fonction interdisant la patronne à quitter son bar*/
        try{
            throw new Exception("Erreur: la patronne ne quitte jamais le bar");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
