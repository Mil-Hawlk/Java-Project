package partieBelotte;

/**
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */
public enum Couleur {

    /**
     * Représente la couleur de carte carreau
     */
    Carreau("Carreau"),

    /**
     * Représente la couleur de carte coeur
     */
    Coeur("Coeur"),

    /**
     * Représente la couleur de carte pique
     */
    Pique("Pique"),

    /**
     * Représente la couleur de carte trèfle
     */
    Trefle("Trèfle");

    /**
     * Représente le nom de la couleur
     */
    private String nom;
    
    /**
     * Constructeur de l'objet enum Couleur
     * Une couleur est représentée par son nom
     * @param pNom 
     */
    Couleur(String pNom)
    {
        nom = pNom;
    }

    /**
     * Accesseur du nom de la couleur
     * Retourne un objet de type String
     * @return nomCouleur
     */
    public String getCouleur()
    {
        return nom;
    }
}
