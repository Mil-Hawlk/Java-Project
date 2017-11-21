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
public class Bar {
    protected Boisson[] boisson;
    protected Client[] client;
    protected int nombre_Client = 0;
    protected Serveur[] serveur;
    protected Patron patron;
    protected Barman barman;
    protected String name;
    protected int chaise = 0;
    
    public Bar(Boisson[] pboisson, Serveur[] pserveur, Patron ppatron, Barman pbarman){
        this.boisson=pboisson;
        this.serveur=pserveur;
        this.patron=ppatron;
        this.barman=pbarman;
        this.name="Chez" + this.patron.obtenir_Surnom();
        System.out.println("Ouverture du Bar " + this.name);
    }
    
    public void nouveau_client(Client pclient){
        System.out.print("Un nouveau client vient d'arriver" + pclient.obtenir_Prenom());
        nombre_Client+=1;
        this.client[nombre_Client]=pclient;
        System.out.println("Un nouveau client vient d'arriver, le bar en contient pour l'instant " + this.nombre_Client );
    }
    
    public void get_Info(){
    System.out.println("Ce bar s'appelle " + this.name +
            ".\n La patronne s'appelle " + this.patron.obtenir_Prenom() + 
            " et il dispose de " + this.nombre_Client + 
            " clients pour le moments");
    }
}
