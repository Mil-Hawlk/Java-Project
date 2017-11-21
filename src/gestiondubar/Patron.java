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
    
    public Patron(String cprenom, String csurnom, int cporte_monnaie,
            String ccrie, Boisson pboisson_favorite,
            Boisson pboisson_secours, Object attribut)
    {
        /*Catch l'exeption si attribut ne fais pas partie de l'énumération pour les femmes*/
        super(cprenom,csurnom,cporte_monnaie,ccrie,pboisson_favorite,pboisson_secours,attribut);
    }
    
    public void payerCommande(double pprixCommande, Fournisseur pnomFournisseur,
            Bar bar)
    {
        try{
            if(this.porte_monnaie>=pprixCommande)
        {
            parler("Voilà ton argent "+pnomFournisseur.nomFournisseur);
            this.porte_monnaie-=pprixCommande;
            pnomFournisseur.porte_monnaie+=pprixCommande;
            pnomFournisseur.livrerCommande(bar);
        }
            else
        {
            parler("Je n'ai pas assez d'argent pour payer le fournisseur, je pique dans la caisse");
            bar.barman.vider_caisse_pour_commande();
            if(this.porte_monnaie>pprixCommande)//100 euros de marge
            {
                this.porte_monnaie-=pprixCommande;
                pnomFournisseur.porte_monnaie+=pprixCommande;
                pnomFournisseur.livrerCommande(bar);
            }
            else
            {
                throw new Exception("Annulation de la commande, manque d'argent");
            }
        }
    }
        catch(Exception e) {
        System.out.println(e.getMessage());
        pnomFournisseur.annulerCommande();
        }
    
   }
    
    public void recuperer_Caisse(Barman barman){
        super.parler("Merci bon boulot", barman);
    }
    
    public void exclure(Client client){
        try{
            if(client.est_vire){
                throw new Exception("Le client est deja viré");
            }
            else{
                this.parler("Tu es viré de ce bar", client);
                client.est_vire=true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void ordonner(Client client){
        try{
            if(client.est_bourre==1){
                throw new Exception("Le client ne peut déja plus etre servi");
            }
            else{
                this.parler("Vous ne devez plus servir " + client.obtenir_Prenom());
                client.est_bourre = 1;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }  
    
    public void pardonner(Client client){
        try{
            if(client.est_bourre==0){
                throw new Exception("Le client n'est pas interdit de boisson");
            }
            else{
                client.est_bourre=0;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
    
    
    @Override
    public void changer_Sexe(Object  new_attribut){
        try{
            if(new_attribut.getClass() != Bijoux.class){
                throw new Exception("Ceci n'est pas une femme");
            }
            else {
                super.changer_Sexe(new_attribut);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Barman barman){
        if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade,boisson);
        } else {
            if(camarade.getClass()==Serveur.class){
                this.offrir_Verre((Serveur)camarade,boisson);
            } else {
                try{
                    if(boisson.nombre<1){
                        throw new Exception("Impossibilité de vous servir");
                    } else {
                        this.parler("J'aimerai commander " + boisson.name, barman);
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
    
    @Override
    public void offrir_Verre(Humain camarade, Boisson boisson, Serveur serveur){
        if(camarade.getClass()==Barman.class){
            this.offrir_Verre((Barman)camarade,boisson);
        } else {
            if(camarade.getClass()==Serveur.class){
                this.offrir_Verre((Serveur)camarade,boisson);
            } else {
                try{
                    if(boisson.nombre<1){
                        throw new Exception("Impossibilité de vous servir");
                    } else {
                        this.parler("J'aimerai commander " + boisson.name, serveur.barman);
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
    
    @Override
    public void offrir_Verre(Serveur serveur, Boisson boisson){
        try{
            if(boisson.nombre<1 || boisson.degre_alcool>0){
                throw new Exception("Impossibilité de servir ce verre");
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
        catch( Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void offrir_Verre(Barman barman, Boisson boisson){
        try{
            if(boisson.degre_alcool>0 || boisson.nombre<1){
                throw new Exception("Impossibilité de servir ce verre");
            }
            else{
                this.parler("J'aimerai commander" + boisson.name, barman);
                barman.servir_Boisson(boisson, this);
                this.parler("Aller prend la je te l'offre mon petiot");
                barman.parler(crie + " Merci beaucoup", this);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void commander(Boisson boisson, Barman barman){
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
    
    @Override
    public void commander(Boisson boisson, Serveur serveur){
        try{
            if(boisson.nombre<1){
                throw new Exception("Impossibilité de servir cette boisson");
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
    
}
