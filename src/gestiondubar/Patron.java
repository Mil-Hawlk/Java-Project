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
public class Patron extends Client {
    
    public Patron(String cprenom, String csurnom, int cporte_monnaie, String ccrie, Boisson pboisson_favorite, Boisson pboisson_secours, Object attribut)
    {
        /*Catch l'exeption si attribut ne fais pas partie de l'énumération pour les femmes*/
        super(cprenom,csurnom,cporte_monnaie,ccrie,pboisson_favorite,pboisson_secours,attribut);
    }
}
