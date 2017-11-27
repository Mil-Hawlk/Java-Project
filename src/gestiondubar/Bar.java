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
import java.util.LinkedList;
import java.util.List;


public class Bar {
    protected Boisson[] boisson;
    protected int nombre_Client = 0;
    protected List client = new LinkedList();
    protected Serveur[] serveur;
    protected Patron patron;
    protected Barman barman;
    protected String name;
    protected Table[] table ;
    private boolean impossible = true;
    
    public Bar(Boisson[] pboisson, Serveur[] pserveur, Patron ppatron, Barman pbarman, Table[] tables){
        try{
            if(ppatron.sexe.attribut.getClass() != Bijoux.class){
                throw new Exception("Erreur: La patronne n'est pas une femme ouverture du bar impossible");
            }
            else{
        this.boisson=pboisson;
        this.serveur=pserveur;
        this.patron=ppatron;
        this.barman=pbarman;
        this.table=tables;
        this.name="Chez" + this.patron.obtenir_Surnom();
        this.impossible = false;
        System.out.println("Ouverture du Bar " + this.name);         
            }
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    public void client_present(){
        try{
            if(this.impossible){
                System.out.println("Erreur: Bar impossible d'ouvrir mauvais patron");
            }
            else{
                    System.out.println("Le bar contient pour l'instant " + this.nombre_Client + " clients(s)");
                    for(int compteur= 0; compteur<this.client.size(); compteur++){
                        Client present = (Client)this.client.get(compteur);
                        System.out.println(present.obtenir_Prenom());
                    }
            }
        }
        catch(Exception e ){
        System.out.println(e.getMessage());
        }
    }
    
    protected void client_virer(Client pclient){ 
        try{
            if(this.impossible){
                throw new Exception("Erreur: Bar impossible d'ouvrir mauvais patron ");
            }
            else{
            int compteur=0;
            while(compteur<this.client.size() && this.client.get(compteur)!=pclient){
                compteur++;
            }
            if(this.client.get(compteur) == pclient){
                this.client.remove(compteur);
                pclient.est_interieur=0;
                this.nombre_Client--;
            }
            else {
               throw new Exception("Erreur: Le client n'est pas présent dans le bar"); 
            }
                }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void ajouter_client(Client pclient){
        try{
            if(this.impossible){
                throw new Exception("Erreur: Bar impossible d'ouvrir mauvais patron");
            }
            else{              
                if(pclient.sexe.attribut.getClass() != TShirt.class && pclient.sexe.attribut.getClass() != Bijoux.class){
                    throw new Exception("Erreur: Ceci n'est ni un homme ni une femme");
                }
                else{
                    if(pclient.est_interieur == 1){
                        throw new Exception("Erreur: Ce client est deja présent dans un bar");
                    }
                    else{
                        pclient.est_interieur=1;
                        this.nombre_Client +=1;
                        client.add(pclient);
                        System.out.println(pclient.obtenir_Prenom() + " dit le " + pclient.obtenir_Surnom() + " rentre dans le bar");
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    protected void quitter_client(Client pclient){
        try{
            if(this.impossible){
                throw new Exception("Erreur: Bar impossible d'ouvrir mauvais patron");
            }
            else{
                int compteur=0;
                while(compteur<this.client.size() && this.client.get(compteur)!=pclient){
                    compteur++;
                }
                if(this.client.get(compteur) == pclient){
                    this.client.remove(compteur);
                    pclient.est_interieur=0;
                    this.nombre_Client -=1;
                }
                else {
                   throw new Exception("Erreur: Le client n'est pas présent dans le bar"); 
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
    
}
