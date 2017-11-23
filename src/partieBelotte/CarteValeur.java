/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partieBelotte;

/**
Cette énumération contient toutes les valeurs possibles pour un jeu de cartes
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
    
    String nom;
    int valNorm;
    int valAtout;
    
    private CarteValeur(String nomCarte, int valNormCarte, int valAtoutCarte)
    {
        this.nom = nomCarte;
        this.valNorm = valNormCarte;
        this.valAtout = valAtoutCarte;
    }
}
