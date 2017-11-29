package partieBelotte;

/**
 Une couleur est soit Pique, soit Carreau, soit Trèfle ou Coeur
 */
public enum Couleur {
    Carreau("Carreau"),
    Coeur("Coeur"),
    Pique("Pique"),
    Trefle("Trèfle");

    
    private String nom;
    
    Couleur(String pNom)
    {
        nom = pNom;
    }

    public String getCouleur()
    {
        return nom;
    }
}
