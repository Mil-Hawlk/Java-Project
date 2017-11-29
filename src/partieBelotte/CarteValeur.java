/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partieBelotte;

/**
Cette énumération contient toutes les valeurs possibles pour un jeu de cartes,
* ainsi que la valeur des cartes en non-atout et en atout à la belotte
 */
public enum CarteValeur {
    As("As",11,11),
    Roi("Roi",4,4),
    Dame("Dame",3,3),
    Valet("Valet",2,20),
    dix("10",10,10),
    neuf("9",0,14),
    huit("8",0,0),
    sept("7",0,0);
    
    private String nom;
    private int valNorm;
    private int valAtout;
    
    private CarteValeur(String pNom, int pValNorm, int pValAtout)
    {
        nom = pNom;
        valNorm = pValNorm;
        valAtout = pValAtout;
    }
    
        public String getNom()
    {
        return nom;
    }
    
    public int getValeurNormale()
    {
        return valNorm;
    }
    
    public int getValeurAtout()
    {
        return valAtout;
    }

}
