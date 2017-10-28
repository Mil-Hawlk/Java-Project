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
public class Boisson {
    protected String name;
    protected double degre_alcool;
    protected double prix_achat;
    protected double prix_vente;
    
    public Boisson(String pname, double pdegre_alcool, double pprix_achat, double pprix_vente){
        this.name=pname;
        this.degre_alcool=pdegre_alcool;
        this.prix_vente=pprix_vente;
        this.prix_achat=pprix_achat;
    }
}
