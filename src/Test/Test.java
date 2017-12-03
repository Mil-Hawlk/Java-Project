/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import gestiondubar.Bar;
import gestiondubar.Barman;
import gestiondubar.Bijoux;
import gestiondubar.Boisson;
import gestiondubar.Client;
import gestiondubar.Fournisseur;
import gestiondubar.Humain;
import gestiondubar.Patron;
import gestiondubar.Serveur;
import gestiondubar.Sexe_Client;
import gestiondubar.Sexe_Serveur;
import gestiondubar.TShirt;
import gestiondubar.Table;
import gestiondubar.Tournee_generale;

/**
 *
 * @author pierr
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Définition des boissons*/
        Boisson cuvee = new Boisson("Cuvée des Troll",50,8,2,5); /* Cuvée des 
        trolls*50 à 8.7° acheté 2€ et revendu 5€*/
        Boisson karmeliete = new Boisson("Triple Karmeliete", 50, 7.2, 2,5);
        Boisson delirium = new Boisson("Delirium", 25, 7.8, 2,4.5);
        Boisson eau = new Boisson("eau", 100,0,1,2);
        Boisson coca = new Boisson("coca-cola", 50, 0, 2,4);
        Boisson vodka = new Boisson("Vodka", 2 , 40, 3,7);
        Boisson[] boissons = {cuvee,karmeliete,delirium,eau,coca,vodka};
        /* Création des tables*/
        Table jaune = new Table();
        Table rouge = new Table();
        Table bleu = new Table();
        Table vert = new Table();
        Table[] tables = {jaune,rouge,bleu,vert};
        
        /* Création du patron */
        System.out.println("************** Définition des protagonistes *******"
                + "***************");
        Patron philippine = new Patron("philippine","Coquette",20,"Yeahh",
                vodka,coca,Bijoux.collier);
        
        /* Définir le barman*/
        Barman anthony = new Barman("anthony","El Barman",100,"HoHoHo",
                philippine);
        
        /*Définir le fournisseur*/
        Fournisseur plouvier = new Fournisseur("Plouvier","Plouplou",50,
                "YAAAAAAAAJAJAJJAA");
        
        /*Création des serveurs*/
        Sexe_Serveur sexeLuc = new Sexe_Serveur(true);
        Serveur luc = new Serveur("luc","Krokro",20,"Oh punaise",sexeLuc,
                anthony); /* Serveur garçon*/
        Sexe_Serveur sexePierre = new Sexe_Serveur(true,10);
        Serveur pierre = new Serveur("pierre","Olaf",2,"Yeahh",sexePierre,
                anthony);
        Sexe_Serveur sexeValentine = new Sexe_Serveur(false,20);
        Serveur valentine = new Serveur("Valentine", "hey" , 40, "Yolo", 
                sexeValentine,anthony);
        Serveur[] serveurs = {pierre,luc,valentine};
        System.out.println("***************************************************"
                + "Ouverture du bar *****************************************");
        Bar mon_Bar = new Bar(boissons,serveurs,philippine,anthony,tables);
        System.out.println("***************************************************"
                + "Création des clients");
        // Définition de 4 clients
        Client david = new Client("David","Brakmar",50,"YOOOHH",cuvee,delirium
        ,(Object)TShirt.jaune);
        Client kilian = new Client("Kilian","BriseFrontal",40,"YEAHH",vodka,
                eau,(Object)TShirt.bleu);
        Client gatoune = new Client("Agathe","gatoune",30,"Moh !!!",delirium,
                coca,(Object)Bijoux.pendentif);
        Client anais = new Client("Anais","Ananas",27,"Genre !!",vodka,cuvee,
        (Object)Bijoux.boucle_d_oreille);
        System.out.println("***************************************************"
                + "Entrée de 3 clients dans le bar **************************");
        david.entrer_bar(mon_Bar);
        gatoune.entrer_bar(mon_Bar);
        kilian.entrer_bar(mon_Bar);
        System.out.println("***************************************************"
                + "Tournée générale de biere de david ***********************");
        mon_Bar.client_present();
        System.out.println("David porte monnaie: " + david.getPorteMonnaie());
        david.tournee_generale(mon_Bar, cuvee);
        System.out.println("David porte monnaie: " + david.getPorteMonnaie());
        System.out.println("***************************************************"
                + "Kilian offre à boire à un serveur ************************");
        kilian.offrir_Verre(pierre, vodka, anthony);
        kilian.offrir_Verre(pierre, coca);
        System.out.println("***************************************************"
                + "Kilian commande à boire **********************************");
        kilian.commander(vodka, anthony);
        System.out.println("***************************************************"
                + "Kilian commande à boire au serveur********************");
        kilian.commander(vodka,valentine);
        System.out.println(kilian.getPorteMonnaie() + "€");
        
        System.out.println("***************************************************"
                + "La patronne interdit kilian de boire *********************");
        philippine.ordonner(kilian);
        kilian.commander(coca, anthony);
        System.out.println("**************************************************"
                +"comme kilian ne paux plus boire il s'en va ****************");
        kilian.quitter_Bar(mon_Bar);
        mon_Bar.client_present();
        System.out.println("**************************************************"
                + "David se fait virer ensuite ******************************");
        philippine.exclure(david,mon_Bar);
        david.offrir_Verre(gatoune, cuvee,anthony); /*Erreur puisque David n'est
        plus dans le bar*/
        System.out.println("**************************************************"
                + "David et philippine change de sexe ****************");
        david.changer_Sexe(delirium); 
        david.changer_Sexe(Bijoux.collier);
        philippine.changer_Sexe(TShirt.bleu);
        philippine.changer_Sexe(Bijoux.boucle_d_oreille);
        System.out.println("***************************************************"
        + "Lancement de la belote *******************************************");
        kilian.entrer_bar(mon_Bar);
        david.entrer_bar(mon_Bar);
        anais.entrer_bar(mon_Bar);
        mon_Bar.client_present();
        kilian.s_attabler(bleu);
        anais.s_attabler(bleu);
        gatoune.s_attabler(bleu);
        david.s_attabler(bleu);
        bleu.lancee_belote();
    }
    
}
