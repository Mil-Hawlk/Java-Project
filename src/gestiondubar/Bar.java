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
import java.util.LinkedList;
import java.util.List;


public class Bar {
    protected Boisson[] boisson; // Toutes les boissons diponibles dans le bar
    protected int nombre_Client = 0; // Le nombre de clients dans le bar
    protected List client = new LinkedList(); // Liste des clients
    protected Serveur[] serveur; // Tous les serveurs disponibles dans le bar
    protected Patron patron; // La patronne du bar
    protected Barman barman; // Le barman du bar
    protected String name; // Le nom du bar
    protected Table[] table ; // les tables disponibles dans le bar
    private boolean impossible = true; /* Variable pour voir si l'ouverture du 
    bar est possible, c'est à dire si le patron est bien une femme*/
    
    /**
     * 
     * @param pboisson
     * @param pserveur
     * @param ppatron
     * @param pbarman
     * @param tables 
     * @exception Exception 
     */
    public Bar(Boisson[] pboisson, Serveur[] pserveur, Patron ppatron, 
            Barman pbarman, Table[] tables){
            /*Constructeur permettant de créer un bar exception si le patron n'
        est pas une femme*/
        try{
            if(ppatron.sexe.attribut.getClass() != Bijoux.class){
                throw new Exception("Erreur: La patronne n'est pas une femme "
                        + "ouverture du bar impossible");
            }
            else{
        this.boisson=pboisson;
        this.serveur=pserveur;
        this.patron=ppatron;
        this.barman=pbarman;
        this.table=tables;
        this.name="Chez" + this.patron.getPrenom();
        this.impossible = false;
        System.out.println("Ouverture du Bar " + this.name);         
            }
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    /**
     * @exception Exception 
     */
    public void client_present(){
    /*Fonction utilisant la liste chainée des clients afin d'afficher tous les 
    clients présents dans le bar, exception si le bar ne peut pas etre ouvert*/
        try{
            if(this.impossible){
                System.out.println("Erreur: Bar impossible d'ouvrir mauvais "
                        + "sexe pour le patron");
            }
            else{
                    System.out.println("Le bar contient pour l'instant " + 
                            this.nombre_Client + " clients(s)");
                    for(int compteur= 0; compteur<this.client.size(); 
                            compteur++){
                        Client present = (Client)this.client.get(compteur);
                        System.out.println(present.getPrenom());
                    }
            }
        }
        catch(Exception e ){
        System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param pclient 
     * @exception Exception
     * @exception Exception
     */
    protected void client_virer(Client pclient){ 
        /*Fonction utilisant la liste chainée des clients afin d'excluer un 
        client ou permettre à celui-ci de quitter le bar, exception si le bar ne
        peut pas être ouvert ou si le client n'est pas présent dans le bar*/
        try{
            if(this.impossible){
                throw new Exception("Erreur: Bar impossible d'ouvrir "
                        + "mauvais patron ");
            }
            else{
            int compteur=0;
            while(compteur<this.client.size() && 
                    this.client.get(compteur)!=pclient){
                compteur++;
            }
            if(this.client.get(compteur) == pclient){
                this.client.remove(compteur);
                pclient.est_interieur=0;
                this.nombre_Client--;
            }
            else {
               throw new Exception("Erreur: Le client n'est pas présent dans le "
                       + "bar"); 
            }
                }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param pclient 
     * @exception Exception
     * @exception Exception
     * @exception Exception
     */
    protected void ajouter_client(Client pclient){
        /*Fonction utilisant la liste chainée des clients afin d'ajouter un 
        client, exception si le bar ne peut pas ouvrir ou si le client n'est ni
        un homme ni une femme ou s'il est deja présent dans le bar*/
        try{
            if(this.impossible){
                throw new Exception("Erreur: Bar impossible d'ouvrir mauvais "
                        + "patron");
            }
            else{              
                if(pclient.sexe.attribut.getClass() != TShirt.class && 
                        pclient.sexe.attribut.getClass() != Bijoux.class){
                    throw new Exception("Erreur: Ceci n'est ni un homme ni une "
                            + "femme");
                }
                else{
                    if(pclient.est_interieur == 1){
                        throw new Exception("Erreur: Ce client est deja présent"
                                + " dans un bar");
                    }
                    else{
                        pclient.est_interieur=1;
                        this.nombre_Client +=1;
                        client.add(pclient);
                        System.out.println(pclient.getPrenom() + " dit le "
                                + pclient.getSurnom()+ 
                                " rentre dans le bar");
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
}
