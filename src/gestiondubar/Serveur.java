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
    protected Barman barman;
    protected Sexe_Serveur sexe;
    
    public Serveur (String cprenom, String csurnom, float cporte_monnaie, String ccrie,Sexe_Serveur psexe,Barman cpatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.barman=cpatron;
        this.sexe=psexe;
        this.parler("Je suis le nouveau serveur " + this.obtenir_Prenom());
    }
    
    protected void commander(Client client, Boisson boisson){
        parler("Je vais te chercher ça " + client.obtenir_Prenom());
        this.barman.servir_Boisson(boisson, this);
        parler("Voici ton verre");
    }
    
    @Override
    public void boire(Boisson boisson){
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
