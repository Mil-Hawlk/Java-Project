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
public class Serveur extends Humain{
    /*Faire le sexe*/
    protected Barman barman;
    protected Sexe_Serveur sexe;
    
    public Serveur (String cprenom, String csurnom, float cporte_monnaie, String ccrie,Sexe_Serveur psexe,Barman cpatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.barman=cpatron;
        this.sexe=psexe;
        this.parler("Je suis le nouveau serveur " + this.obtenir_Prenom());
    }
    
    public void commander(Client client, Boisson boisson){
        parler("Je vais te chercher ça " + client.obtenir_Prenom());
        this.barman.servir_Boisson(boisson, this);
        parler("Voici ton verre");
    }
    
    @Override
    public void boire(Boisson boisson){
    /*Exeption si c'est ce n'est pas de l'eau*/
    super.boire(boisson);
    }
    
    @Override
    public void parler(String phrase){
        System.out.print("Serveur ");
        super.parler(phrase);
    }
    
    @Override
    public void parler(String phrase , Humain humain){
        System.out.print("Serveur ");
        super.parler(phrase);
    }
}
