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

public class Serveur extends Humain{
    protected Barman barman;
    protected Sexe_Serveur sexe;
    
    /**
     * 
     * @param cprenom
     * @param csurnom
     * @param cporte_monnaie
     * @param ccrie
     * @param psexe
     * @param cpatron 
     */
    public Serveur (String cprenom, String csurnom, float cporte_monnaie, 
            String ccrie,Sexe_Serveur psexe,Barman cpatron){
        // Constructeur d'un serveur
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.barman=cpatron;
        this.sexe=psexe;
        this.parler("Je suis le nouveau serveur " + this.getPrenom());
    }
    
    /**
     * 
     * @param client
     * @param boisson 
     */
    protected void commander(Client client, Boisson boisson){
        /*Commande du serveur au barman pour avoir la boisson pour le client*/
        parler("Je vais te chercher ça " + client.getPrenom());
        this.barman.servir_Boisson(boisson, this);
        parler("Voici ton verre");
    }
    
    /**
     * 
     * @param boisson 
     * @exception Exception
     */
    @Override
    public void boire(Boisson boisson){
        /*Fonction afin qu'il puisse boire une boisson qu'il ne paie pas de plus
        il ne la commande il va directement la cherchée, exception s'il n'a plus
         de cette boisson ou si elle est alcoolisée*/
    try{
        if(boisson.nombre<1){
            throw new Exception("Erreur: Il n'a plus cette boisson");
        }
        else{
            if(boisson.degre_alcool>0){
                throw new Exception("Erreur: Il ne peut pas boire d'alcool");
            }
            else{
                super.boire(boisson);
            }
        }
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    }
    
    /**
     * 
     * @param phrase 
     */
    @Override
    public void parler(String phrase){
        System.out.print("Serveur ");
        super.parler(phrase);
    }
    
    /**
     * 
     * @param phrase
     * @param humain 
     */
    @Override
    public void parler(String phrase , Humain humain){
        System.out.print("Serveur ");
        super.parler(phrase);
    }
}
