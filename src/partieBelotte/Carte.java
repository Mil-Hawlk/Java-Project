package partieBelotte;

/**
Une carte se définit par une figure représentée par la classe CarteValeur et par une couleur
 */
public class Carte {
    private Couleur couleur;
    private CarteValeur figure;
    
    protected Carte(CarteValeur pFigure, Couleur pCouleur)
    {
        couleur = pCouleur;
        figure = pFigure;
    }
    
    public String getCouleur()
    {
        return couleur.getCouleur();
    }
    
    public String getFigureNom()
    {
        return figure.getNom();
    }
    
    public CarteValeur getFigure()
    {
        return figure;
    }
    
    public int getValeurAtout()
    {
        return figure.getValeurAtout();
    }
    
    public int getValeurNormale()
    {
        return figure.getValeurNormale();
    }
    
}
