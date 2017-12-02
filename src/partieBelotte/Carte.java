package partieBelotte;

/**
 * L'objet Carte représente une carte d'un jeu de carte,
 * représenté par une couleur (Pique, Trèfle, Carreau, coeur),
 * et par une figure (entre 7 et As pour la belotte)
 * @author pierre
 * @author david
 * @version 1.0
 */
public class Carte {
    /**
     * Définit la couleur d'une carte
     * (Pique, Trèfle, Carreau, ou coeur)
     */
    private Couleur couleur;
    /**
     * Définit la figure d'une carte
     * (entre 7 et As)
     */
    private CarteValeur figure;
    
    /**
     * Constructeur de Carte : on peut créer une nouvelle
     * carte à partir d'une couleur et d'une figure
     * @param pFigure
     * @param pCouleur
     */
    protected Carte(CarteValeur pFigure, Couleur pCouleur)
    {
        couleur = pCouleur;
        figure = pFigure;
    }
    
    /**
     * Accesseur de la couleur de la carte
     * Retourne un objet String
     * @return couleurCarte
     */
    public String getCouleur()
    {
        return couleur.getCouleur();
    }
    
    /**
     * Accesseur du nom de la figure de la carte
     * Retourne un objet String
     * @return nomFigureCarte
     */
    public String getFigureNom()
    {
        return figure.getNom();
    }
    
    /**
     * Accesseur de la figure de la carte
     * Retourne un objet CarteValeur
     * @return figureCarte
     */
    public CarteValeur getFigure()
    {
        return figure;
    }
    
    /**
     * Accesseur de la valeur en atout de la carte
     * Retourne un objet integer
     * @return valeurEnAtout
     */
    public int getValeurAtout()
    {
        return figure.getValeurAtout();
    }
    
    /**
     * Accesseur de la valeur en non-atout de la carte
     * Retourne un objet integer
     * @return valeurNonAtout
     */
    public int getValeurNormale()
    {
        return figure.getValeurNormale();
    }
    
}
