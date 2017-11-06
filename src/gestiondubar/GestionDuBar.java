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
        Patron philippine = new Patron("philippine","Coquette",50,"Yeahh",vodka,coca,Bijoux.collier);
        /*Faire essai si le patron est un homme*/
        /* Définir le barman*/
        Barman anthony = new Barman("anthony","El Barman",10,"HoHoHo",philippine);
        /* Définir les serveurs*/
        Sexe_Serveur sexeLuc = new Sexe_Serveur(true);
        Serveur luc = new Serveur("luc","Handicarpar",20,"Oh punaise",sexeLuc,anthony); /* Serveur garçon*/
        Sexe_Serveur sexePierre = new Sexe_Serveur(true,10);
        Serveur pierre = new Serveur("pierre","Olaferme",2,"Yeahh",sexePierre,anthony);
        Sexe_Serveur sexeValentine = new Sexe_Serveur(false,9);
        Serveur valentine = new Serveur("valentine","Val",5,"Yolo",sexeValentine,anthony); /* Serveur fille*/
        Serveur[] serveur = {luc,pierre,valentine};
        /* Ouverture du bar*/
        Bar mon_Bar = new Bar(boisson,serveur,philippine,anthony);
        /* On définit un nouveau client*/
        Client david = new Client("david","Brakmar", 20 , "Youhou",cuvee,delirium,TShirt.jaune);
        /*david prend un verre à serveur pierre */
        System.out.println();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        david.commander(cuvee, pierre);
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        System.out.println();
        /*david prend un verre au barman anthony*/
        System.out.println();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        david.commander(vodka, anthony);
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        System.out.println();
        /*philippine prend un verre au barman anthony*/
        System.out.println();
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        anthony.obtenir_caisse();
        philippine.commander(vodka, anthony);
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        anthony.obtenir_caisse();
        System.out.println();
        
        /* david a un taux d'alcoolémie énorme donc il va parler chelou au serveur*/
        System.out.println();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        david.commander(karmeliete, valentine);
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        System.out.println();
        /* david change de sexe */
        System.out.println(david.sexe.sexe);
        david.changer_Sexe(Bijoux.boucle_d_oreille);
        System.out.println(david.sexe.sexe);
        System.out.println();
        /* david a un taux d'alcoolémie énorme donc il va parler chelou au serveur*/
        System.out.println();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        david.porte_monnaie+=20; // il est partit retiré
        System.out.println(david.porte_monnaie);
        david.commander(karmeliete, luc);
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        anthony.obtenir_caisse();
        System.out.println();
        /*david offre un verre à la patronne (qui est un client) en demandant au barman*/
        System.out.println();
        anthony.obtenir_caisse();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        System.out.println(david.cote_popularite);
        david.offrir_Verre(philippine, vodka, anthony);
        anthony.obtenir_caisse();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        System.out.println(david.cote_popularite);
        System.out.println();
        /*david offre un verre à la patronne mais en demandant au serveur*/
        System.out.println();
        anthony.obtenir_caisse();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        System.out.println(david.cote_popularite);
        david.offrir_Verre(philippine, vodka, luc);
        anthony.obtenir_caisse();
        System.out.println(david.niveau_alcool);
        System.out.println(david.porte_monnaie);
        System.out.println(philippine.niveau_alcool);
        System.out.println(philippine.porte_monnaie);
        System.out.println(david.cote_popularite);
        System.out.println();
        
        while(philippine.niveau_alcool<1000)
        {
            david.offrir_Verre(philippine, vodka, luc);
            david.porte_monnaie=david.porte_monnaie+7;
            System.out.print("Le niveau d'alcolémie de philippine est de : ");
            System.out.println(philippine.niveau_alcool);
        }
        System.out.println("philippine est tombée par terre, complètement raide");
        System.out.println("philippine part aux urgences pour un lavage d'estomac");
    }
    
}
