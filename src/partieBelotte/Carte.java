package partieBelotte;

/**
Une carte se définit par une figure représentée par la classe CarteValeur et par une couleur
 */
public class Carte {
    Couleur couleur;
    CarteValeur figure;
    
    public Carte(CarteValeur figureCarte, Couleur couleurCarte)
    {
        this.couleur = couleurCarte;
        this.figure = figureCarte;
    }
    
    int getValeurNorm()
    {
        return this.figure.valNorm;
    }
    
    int getValeurAtout()
    {
        return this.figure.valAtout;
    }

    Couleur getCouleur()
    {
        return this.couleur;
    }
    
    String getNom()
    {
        return this.figure.nom;
    }
}
