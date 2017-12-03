package partieBelotte;

/**
 * L'objet CarteValeur représente la figure qui définit
 * une carte du jeu
 * @author pierre
 * @author david
 * @version 1.0
 */
public enum CarteValeur {

    /**
     * La figure As, de valeur 11 en atout et en non-atout
     */
    As("As",11,11),

    /**
     * La figure Roi, de valeur 4 en atout et en non-atout
     */
    Roi("Roi",4,4),

    /**
     * La figure Dame, de valeur 3 en atout et en non-atout
     */
    Dame("Dame",3,3),

    /**
     * La figure Valet, de valeur 20 en atout et 2 en non-atout
     */
    Valet("Valet",2,20),

    /**
     * La figure 10, de valeur 10 en atout et non-atout
     */
    Dix("10",10,10),

    /**
     * La figure 9, de valeur 14 en atout et 0 en non-atout
     */
    Neuf("9",0,14),

    /**
     * La figure 8, de valeur 0 en atout et non-atout
     */
    Huit("8",0,0),

    /**
     * La figure 7, de valeur 0 en atout et non-atout
     */
    Sept("7",0,0);
    
    /**
     * Représente le nom de la figure
     */
    private String nom;
    /**
     * Représente la valeur normale de la figure
     */
    private int valNorm;
    /**
     * Représente la valeur en atout de la figure
     */
    private int valAtout;
    
    /**
     * Constructeur de l'objet enum CarteValeur
     * Une figure est représentée par un nom, une valeur
     * en atout et une valeur en non-atout
     * @param pNom
     * @param pValNorm
     * @param pValAtout 
     */
    private CarteValeur(String pNom, int pValNorm, int pValAtout)
    {
        nom = pNom;
        valNorm = pValNorm;
        valAtout = pValAtout;
    }
    
    /**
     * Accesseur du nom de la figure
     * Retourne un objet de type String
     * @return nomFigure
     */
    public String getNom()
    {
        return nom;
    }
    
    /**
     * Accesseur de la valeur normale de la figure
     * Retourne un objet de type integer
     * @return valeurNormale
     */
    public int getValeurNormale()
    {
        return valNorm;
    }
    
    /**
     * Accesseur de la valeur en atout de la figure
     * Retourne un objet de type integer
     * @return valeurAtout
     */
    public int getValeurAtout()
    {
        return valAtout;
    }

}
