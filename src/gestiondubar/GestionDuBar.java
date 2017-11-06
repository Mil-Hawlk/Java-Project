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
public class GestionDuBar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Définir toutes les boissons*/
        Boisson cuvee = new Boisson("Cuvée des Troll",50,8,2,5); /* Cuvée des trolls*50 à 8.7° acheté 2€ et revendu 5€*/
        Boisson karmeliete = new Boisson("Triple Karmeliete", 50, 7.2, 2,5);
        Boisson delirium = new Boisson("Delirium", 25, 7.8, 2,4.5);
        Boisson eau = new Boisson("eau", 100,0,1,2);
        Boisson coca = new Boisson("coca-cola", 50, 0, 2,4);
        Boisson vodka = new Boisson("Vodka", 20 , 40, 3,7);
        Boisson[] boisson = {cuvee,karmeliete,delirium,eau,coca,vodka};
        
        /* Définir la patronne*/
        Patron Philippine = new Patron("Philippine","Coquette",50,"Yeahh",vodka,coca,Bijoux.collier);
        /*Faire essai si le patron est un homme*/
        /* Définir le barman*/
        Barman Anthony = new Barman("Anthony","El Barman",10,"HoHoHo",Philippine);
        /* Définir les serveurs*/
        Sexe_Serveur sexeLuc = new Sexe_Serveur(true);
        Serveur Luc = new Serveur("Luc","Handicarpar",20,"Oh punaise",sexeLuc,Anthony); /* Serveur garçon*/
        Sexe_Serveur sexePierre = new Sexe_Serveur(true,10);
        Serveur Pierre = new Serveur("Pierre","Olaferme",2,"Yeahh",sexePierre,Anthony);
        Sexe_Serveur sexeValentine = new Sexe_Serveur(false,9);
        Serveur Valentine = new Serveur("Valentine","Val",5,"Yolo",sexeValentine,Anthony); /* Serveur fille*/
        Serveur[] serveur = {Luc,Pierre,Valentine};
        /* Ouverture du bar*/
        Bar mon_Bar = new Bar(boisson,serveur,Philippine,Anthony);
        /* On définit un nouveau client*/
        Client David = new Client("David","Brakmar", 20 , "Youhou",cuvee,delirium,TShirt.jaune);
        /*David prend un verre à serveur Pierre */
        System.out.println();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        David.commander(cuvee, Pierre);
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        System.out.println();
        /*David prend un verre au barman Anthony*/
        System.out.println();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        David.commander(vodka, Anthony);
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        System.out.println();
        /*Philippine prend un verre au barman Anthony*/
        System.out.println();
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        Anthony.obtenir_caisse();
        Philippine.commander(vodka, Anthony);
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        Anthony.obtenir_caisse();
        System.out.println();
        
        /* David a un taux d'alcoolémie énorme donc il va parler chelou au serveur*/
        System.out.println();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        David.commander(karmeliete, Valentine);
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        System.out.println();
        /* David change de sexe */
        System.out.println(David.sexe.sexe);
        David.changer_Sexe(Bijoux.boucle_d_oreille);
        System.out.println(David.sexe.sexe);
        System.out.println();
        /* David a un taux d'alcoolémie énorme donc il va parler chelou au serveur*/
        System.out.println();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        David.porte_monnaie+=20; // il est partit retiré
        System.out.println(David.porte_monnaie);
        David.commander(karmeliete, Luc);
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        Anthony.obtenir_caisse();
        System.out.println();
        /*David offre un verre à la patronne (qui est un client) en demandant au barman*/
        System.out.println();
        Anthony.obtenir_caisse();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        System.out.println(David.cote_popularite);
        David.offrir_Verre(Philippine, vodka, Anthony);
        Anthony.obtenir_caisse();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        System.out.println(David.cote_popularite);
        System.out.println();
        /*David offre un verre à la patronne mais en demandant au serveur*/
        System.out.println();
        Anthony.obtenir_caisse();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        System.out.println(David.cote_popularite);
        David.offrir_Verre(Philippine, vodka, Luc);
        Anthony.obtenir_caisse();
        System.out.println(David.niveau_alcool);
        System.out.println(David.porte_monnaie);
        System.out.println(Philippine.niveau_alcool);
        System.out.println(Philippine.porte_monnaie);
        System.out.println(David.cote_popularite);
        System.out.println();
        
        while(Philippine.niveau_alcool<1000)
        {
            David.offrir_Verre(Philippine, vodka, Luc);
            David.porte_monnaie=David.porte_monnaie+7;
            System.out.print("Le niveau d'alcolémie de philippine est de : ");
            System.out.println(Philippine.niveau_alcool);
        }
        System.out.println("Philippine est tombée par terre, complètement raide");
        System.out.println("Philippine part aux urgences pour un lavage d'estomac");
    }
    
}
