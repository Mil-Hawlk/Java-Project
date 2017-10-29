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
    protected Barman patron;
    
    public Serveur (String cprenom, String csurnom, float cporte_monnaie, String ccrie,Barman cpatron){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.patron=cpatron;
    }
    
    @Override
    public void boire(Boisson boisson){
    /*Exeption si c'est ce n'est pas de l'eau*/
    super.boire(boisson);
    }

    
    
}
