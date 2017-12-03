package partieBelotte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * JouerBelote est une classe permettant de lancer une partie de belote
 * @author pierre
 * @author david
 * @version 1.0
 */
public class JouerBelote {
    /**
     * Nombre de points que vous voulez jouer pendant la partie
     */
    private int nbPointsPartie = 501;
    /**
     * Nom joueur 1
     */
    private String nomJ1 = "Joueur 1";
    /**
     * Nom joueur 2
     */
    private String nomJ2 = "Joueur 2";
    /**
     * Nom joueur 3
     */
    private String nomJ3 = "Joueur 3";
    /**
     * Nom joueur 4
     */
    private String nomJ4 = "Joueur 4";
    
    /**
     * Constructeur de l'objet JouerBelote. On lance une partie de Belote à
     * partir des noms des 4 joueurs, et du nombre de points que l'on
     * souhaite pour la partie (501, 1001, etc..)
     * @param pNbPointsPartie nombre de points pour la durée de la partie
     * @param pNomJ1 Nom du joueur 1
     * @param pNomJ2 Nom du Joueur 2
     * @param pNomJ3 Nom du joueur 3
     * @param pNomJ4 Nom du joueur 4
     */
    public JouerBelote(int pNbPointsPartie, String pNomJ1, String pNomJ2,
            String pNomJ3, String pNomJ4)
    {
        nbPointsPartie = pNbPointsPartie;
        nomJ1 = pNomJ1;
        nomJ2 = pNomJ2;
        nomJ3 = pNomJ3;
        nomJ4 = pNomJ4;
        
        this.lancerPartieBelote();
    }
    
    /**
     * La méthode lancerPartieBelote initialise les 4 objets joueurs à partir
     * des noms que l'on a donné.Eensuite, elle lance des manches jusqu'à ce
     * qu'une équipe atteigne le score requis pour gagner la partie
     */
    private void lancerPartieBelote()
    {
        System.out.println("Début de la partie de belotte\n");
        Joueur j1 = new Joueur(nomJ1,1);
        Joueur j2 = new Joueur(nomJ2,2);
        Joueur j3 = new Joueur(nomJ3,3);
        Joueur j4 = new Joueur(nomJ4,4);
        Manche manche1 = new Manche(j1, j2, j3, j4);
        while((manche1.getScoreEquipe1()<nbPointsPartie)&&(manche1.getScoreEquipe2()<nbPointsPartie))
        {
            manche1.jouerManche(j1, j2, j3, j4);
            this.ecrireManche(manche1,j1,j2,j3,j4);
        }
        if(manche1.getScoreEquipe1()>manche1.getScoreEquipe2())
        {
            System.out.println("Victoire de l'Equipe 1 !!");
        }
        else
        {
            System.out.println("Victoire de l'Equipe 2 !!");
        }
    }
    
    /**
     * La méthode ecrireManche permet d'inscrire les résultats d'une manche dans
     * un fichier txt, à l'issue de celle-ci
     * @param pManche Objet Manche
     * @param pJ1 Joueur 1
     * @param pJ2 Joueur 2
     * @param pJ3 Joueur 3
     * @param pJ4 Joueur 4
     */
    private void ecrireManche(Manche pManche, Joueur pJ1, Joueur pJ2, Joueur pJ3, Joueur pJ4)//Passer en private
    {
        //Etape 1 : Création du fichier
        File monFichier = new File("./src/partieBelotte/histoireParties.txt");
        try{
            if(monFichier.exists()==false)
            {
                monFichier.createNewFile();
            }
        }
        catch(IOException e){
            System.err.println("Problème de création de fichier");
        }
        //Etape 2 : préparation du message
        String message = "Lors de la partie, ";
        message += String.format("%n");
        Joueur[] listeJoueurs = {pJ1,pJ2,pJ3,pJ4};
        for(Joueur pJi : listeJoueurs)
        {
            switch(pJi.getNumeroEquipe()){
                case 0 : message += " le joueur "+pJi.getNomJoueur()+" a obtenu "+
                        pManche.getScoreEquipe2()+" points avec son partenaire";
                        break;
                case 1 : message += " le joueur "+pJi.getNomJoueur()+" a obtenu "+
                        pManche.getScoreEquipe1()+" points avec son partenaire";
                        break;
            }
            message += String.format("%n");
        }
        //Etape 3 : placement du curseur à la fin + écriture
        FileWriter fichier;
        try{
            fichier = new FileWriter(monFichier,true);
            fichier.write(message);
            fichier.write(String.format("%n"));// Pour sauter une ligne
            try{
                fichier.close();
            }
            catch(IOException e){
                System.err.println("Problème lors de la fermeture du fichier");
            }
        }
        catch(IOException e){
            System.err.println("Problème de lecture du fichier");
        }
    }
    
    /**
     * cette méthode permet d'aller lire dans le fichier histoireParties.txt
     * afin d'afficher le résultat de la dernière manche de belote jouée
     */
    public void afficherDerniereMancheJouee()
    {
        File monFichier = new File("./src/partieBelotte/histoireParties.txt");
        FileReader fichier;
        char[] recup = new char[8];
        String recupString = "\n";
        try{
            fichier = new FileReader(monFichier);
            //On place le curseur sur la dernière partie <-> fin - 5 \n
            int z = 0;
            while(fichier.read(recup)!=-1){
                for(int i=0; i<8; i++)
                {
                    if(recup[i]==13)
                    {
                        z++;
                    }//z devient le nombre de retours chariots total du fichier
                }
            }
            z -= 7;// On retranche à z les 5 lignes intéressantes et 2 les <-|
            fichier.close();// On ferme pour repartir au début du fichier
            fichier = new FileReader(monFichier);
            //On récupère ce qui nous intéresse
            int y = 0;
            while(fichier.read(recup)!=-1)
            {
                for(int i=0; i<8; i++)
                {
                    if(recup[i]==13)
                    {
                        y++; //y compte les retours chariots du 2e passage
                    }
                    if(y>=z)
                    {
                        recupString += recup[i];
                    }
                }
            }
            try{
                fichier.close();
            }
            catch(IOException e){
                System.err.println("Problème lors de la fermeture du fichier");
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Fichier non trouvé, impossible de lire la source");
        }
        catch(IOException e){
            System.out.println("Erreur lors de la lecture du fichier");
        }
        System.out.println(recupString);
    }
    
}
