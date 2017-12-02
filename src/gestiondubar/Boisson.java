/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondubar;

/**
 *
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */
public class Boisson {
    protected String name; //Le nom de la boisson
    protected int nombre; //Le nombre de cette boisson disponible
    protected double degre_alcool; // Le degre d'alcool de la boisson
    protected double prix_achat; // Le prix d'achat au fournisseur
    protected double prix_vente; // Le prix de vente aux clients
    
    /**
     * 
     * @param pname
     * @param pnombre
     * @param pdegre_alcool
     * @param pprix_achat
     * @param pprix_vente 
     */
    public Boisson(String pname, int pnombre,double pdegre_alcool, double pprix_achat, double pprix_vente){
        /*Constructeur permettant de créer une boisson*/
        this.name=pname;
        this.nombre=pnombre;
        this.degre_alcool=pdegre_alcool;
        this.prix_vente=pprix_vente;
        this.prix_achat=pprix_achat;
    }
    public int getNombre(){
        return(this.nombre);
    }
}
