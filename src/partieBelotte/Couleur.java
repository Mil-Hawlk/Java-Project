package partieBelotte;

/**
 Une couleur est soit Pique, soit Carreau, soit Trèfle ou Coeur
 */
public enum Couleur {
    Pique("Pique"),
    Carreau("Carreau"),
    Trefle("Trèfle"),
    Coeur("Coeur");
    
    String nom;
    
    private Couleur(String nomCouleur)
    {
        this.nom = nomCouleur;
    }
}
