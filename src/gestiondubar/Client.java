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
public class Client extends Humain{
    
    protected Boisson boisson_favorite;
    protected Boisson boisson_secours;
    protected int niveau_alcool = 0;
    
    public Client(String cprenom, String csurnom, int cporte_monnaie, String ccrie, Boisson pboisson_favorite, Boisson pboisson_secours){
        super(cprenom,csurnom,cporte_monnaie,ccrie);
        this.boisson_favorite=pboisson_favorite;
        this.boisson_secours=pboisson_secours;
    }   
    
    @Override
    public void se_Presenter(){
        super.se_Presenter();
        parler("Ma boisson favorite est " + this.boisson_favorite.name);
    }
    
}
