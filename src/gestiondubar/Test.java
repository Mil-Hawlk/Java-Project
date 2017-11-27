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
public class Test {
    
    
    public void creation_bar(){
        /*Définition des boissons*/
        Boisson cuvee = new Boisson("Cuvée des Troll",50,8,2,5); /* Cuvée des trolls*50 à 8.7° acheté 2€ et revendu 5€*/
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
        Patron philippine = new Patron("philippine","Coquette",20,"Yeahh",vodka,coca,Bijoux.collier);
        /*Essai si le patron est un homme*/
        Patron philippe = new Patron("pilippe","Coquet",20,"Yeahh",vodka,coca,TShirt.jaune); 
        
        /* Définir le barman*/
        Barman anthony = new Barman("anthony","El Barman",100,"HoHoHo",philippine);
        
        /*Définir le fournisseur*/
        Fournisseur plouvier = new Fournisseur("Plouvier","Plouplou",50,"YAAAAAAAAJAJAJJAA");
        
        /*Création des serveurs*/
        Sexe_Serveur sexeLuc = new Sexe_Serveur(true);
        Serveur luc = new Serveur("luc","Krokro",20,"Oh punaise",sexeLuc,anthony); /* Serveur garçon*/
        Sexe_Serveur sexePierre = new Sexe_Serveur(true,10);
        Serveur pierre = new Serveur("pierre","Olaf",2,"Yeahh",sexePierre,anthony);
        Sexe_Serveur sexeValentine = new Sexe_Serveur(false,9);
        Serveur valentine = new Serveur("valentine","Val",5,"Yolo",sexeValentine,anthony); /* Serveur fille*/
        Serveur[] serveur = {luc,pierre,valentine};
        
        /* On définit un nouveau client*/
        Client david = new Client( "David","Brakmar", 20 , "Youhou",cuvee,
                delirium,(Object)TShirt.jaune);
        Client kilianette = new Client( "Kiliannette","brise-frontal", 50 , "MEC!!",delirium,
                cuvee,(Object)Bijoux.collier);
        /*On ouvre le Bar */
        Bar mon_Bar = new Bar(boissons,serveur,philippine,anthony,tables); 
        /* On essaie d'ouvrir un bar avec un patron homme (Erreur)*/
        Bar mon_Bar1 = new Bar(boissons,serveur,philippe,anthony,tables); 
        /* De ce fait aucun moyen de rajouter des clients dans ce bar*/
        mon_Bar1.ajouter_client(kilianette);
        /*Contrairement au premier bar*/
        mon_Bar.ajouter_client(kilianette);
        mon_Bar.ajouter_client(kilianette); /* Erreur car deja dans le bar */
        /* Etat du bar */
        mon_Bar.client_present();
        mon_Bar.ajouter_client(david); /* David rentre dans le bar */
        mon_Bar.client_present();
        mon_Bar1.client_present(); /* Erreur car mauvais bar a cause de patron*/
        david.s_attabler(bleu); /* David s'attable à la table bleu*/
        kilianette.s_attabler(bleu);/* Kilianette le rejoind */
        System.out.println(bleu.place); /* On vérifie bien qu'il ne reste que deux places*/
        /*Impossible de rajouter un client dans le bar s'il ce n'est ni un homme ni une femme*/
        Client erreur = new Client( "Erreur","Erreur", 20 , "Erreur",cuvee,
                delirium,(Object)cuvee);
        mon_Bar.ajouter_client(erreur);
        mon_Bar.client_present();
        /* Si David décide de quitter le bar*/
        david.quitter_Bar(mon_Bar);
        mon_Bar.client_present();
        /*Il ne peut plus faire certaine chose comme commander*/
        david.commander(vodka, anthony);
    }
    
    public void test_changement_sexe(){
    /* Test pour les fonctions de changement de sexe (Pour cele nous ne sommes 
        pas obligé d'être dans un bar pour changer de sexe) */
    
    /* Si c'est un changement de sexe d'un patron*/
    Boisson vodka = new Boisson("Vodka", 2 , 40, 3,7); /* Dans le constructeur 
    de la patronne*/
    
    Patron philippine = new Patron("philippine","Coquette",20,"Yeahh",
            vodka,vodka,Bijoux.collier);
    
    /* Si philippine change d'attribut mais reste une femme*/
    System.out.println(philippine.sexe.attribut);
    philippine.changer_Sexe(Bijoux.pendentif); // Aucun probleme
    System.out.println(philippine.sexe.attribut);
    /* Si philippine change d'attribut mais devient un homme Erreur*/
    philippine.changer_Sexe(TShirt.rose);
    System.out.println(philippine.sexe.attribut);
    /* Si philippine n'est ni un homme ni une femme*/
    philippine.changer_Sexe(vodka);
    System.out.println(philippine.sexe.attribut);
    
    /* Faisons de meme pour un client*/
    Client david = new Client( "David","Brakmar", 20 , "Youhou",vodka,
                vodka,(Object)TShirt.jaune);
    System.out.println(david.sexe.sexe + " avec un TShirt " + david.sexe.attribut);
    david.changer_Sexe(Bijoux.collier);
    System.out.println(david.sexe.sexe + " avec un " + david.sexe.attribut);
    /* Il change bien de sexe*/
    /* Erreur lorsque ce n'est ni un bijoux, ni un TShirt*/
    david.changer_Sexe(vodka);
    }
    
    public void test_tournee_generale(){
        /*Définition des boissons*/
        Boisson cuvee = new Boisson("Cuvée des Troll",10,8,2,5); /* Cuvée des trolls*50 à 8.7° acheté 2€ et revendu 5€*/
        Boisson vodka = new Boisson("Vodka", 2 , 40, 3,7);
        Boisson[] boissons = {vodka,cuvee};
        
        /* Création des tables*/
        Table jaune = new Table();
        Table rouge = new Table();
        Table bleu = new Table();
        Table vert = new Table();
        Table[] tables = {jaune,rouge,bleu,vert};
        
        /* Création du patron */
        Patron philippine = new Patron("philippine","Coquette",20,"Yeahh",vodka,cuvee,Bijoux.collier);

        /* Définir le barman*/
        Barman anthony = new Barman("anthony","El Barman",100,"HoHoHo",philippine);
        
        /*Création des serveurs*/
        Sexe_Serveur sexeLuc = new Sexe_Serveur(true);
        Serveur luc = new Serveur("luc","Krokro",20,"Oh punaise",sexeLuc,anthony); /* Serveur garçon*/
        Serveur[] serveurs = {luc};
        /* On définit un nouveau client*/
        Client david = new Client( "David","Brakmar", 10 , "Youhou",cuvee,
                vodka,(Object)TShirt.jaune);
        Client kilianette = new Client( "Kiliannette","brise-frontal", 50 , "MEC!!",cuvee,
                vodka,(Object)Bijoux.collier);
        /*On ouvre le Bar */
        Bar mon_Bar = new Bar(boissons,serveurs,philippine,anthony,tables); 
        
        /* Ajout des clients*/
        mon_Bar.ajouter_client(kilianette);
        mon_Bar.ajouter_client(david);
        /* Status du bar */
        mon_Bar.client_present();
        /* Si le barman offre une tournée générale*/
        System.out.println("Il reste" + vodka.nombre + " vodka");
        System.out.println("Il reste dans la caisse:" + anthony.obtenir_caisse());
        System.out.println("Reste dans le portefeuille du barman:" + anthony.porte_monnaie);
        anthony.tournee_generale(mon_Bar, vodka); /* Il paie tout de même sa tournée */
        System.out.println("Reste dans le portefeuille du barman:" + anthony.porte_monnaie);
        System.out.println("Il reste dans la caisse:" + anthony.obtenir_caisse());
        /* Cependant il ne reste plus de vodka*/
        System.out.println("Il reste " + vodka.nombre + " vodka");
        anthony.tournee_generale(mon_Bar, vodka); /* Erreur il n' plus de boisson */
        
        /* Si un client offre une tournée générale*/
        System.out.println("Il reste" + cuvee.nombre + " cuvee");
        System.out.println("David argent: " + david.porte_monnaie);
        david.tournee_generale(mon_Bar, cuvee);
        System.out.println("Il reste" + cuvee.nombre + " cuvee");
        System.out.println("David argent: " + david.porte_monnaie);
        david.tournee_generale(mon_Bar, cuvee); /* Erreur il n'a pas assez d'argent*/
    }
}
