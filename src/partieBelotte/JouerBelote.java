package partieBelotte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */
public class JouerBelote {
    private int nbPointsPartie = 1000;
    private String nomJ1 = "Joueur 1";
    private String nomJ2 = "Joueur 2";
    private String nomJ3 = "Joueur 3";
    private String nomJ4 = "Joueur 4";
    
    /**
     *
     * @param pNbPointsPartie
     * @param pNomJ1
     * @param pNomJ2
     * @param pNomJ3
     * @param pNomJ4
     */
    public JouerBelote(int pNbPointsPartie, String pNomJ1, String pNomJ2,
            String pNomJ3, String pNomJ4)
    {
        nbPointsPartie = pNbPointsPartie;
        nomJ1 = pNomJ1;
        nomJ2 = pNomJ2;
        nomJ3 = pNomJ3;
        nomJ4 = pNomJ4;
        
        this.lancerPartieBelotte();
    }
    
    private void lancerPartieBelotte()
    {
        System.out.println("Début de la partie de belotte\n");
        Joueur j1 = new Joueur(nomJ1,1);
        Joueur j2 = new Joueur(nomJ2,2);
        Joueur j3 = new Joueur(nomJ3,3);
        Joueur j4 = new Joueur(nomJ4,4);
        Manche manche1 = new Manche(j1, j2, j3, j4);
        while((manche1.donnerScoreEquipe1()<nbPointsPartie)&&(manche1.donnerScoreEquipe2()<nbPointsPartie))
        {
            manche1.jouerManche(j1, j2, j3, j4);
        }
        if(manche1.donnerScoreEquipe1()>manche1.donnerScoreEquipe2())
        {
            System.out.println("Victoire de l'Equipe 1 !!");
        }
        else
        {
            System.out.println("Victoire de l'Equipe 2 !!");
        }
        this.ecrirePartie(manche1,j1,j2,j3,j4);
    }
    
    private void ecrirePartie(Manche pManche, Joueur pJ1, Joueur pJ2, Joueur pJ3, Joueur pJ4)//Passer en private
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
                        pManche.donnerScoreEquipe2()+" points avec son partenaire";
                        break;
                case 1 : message += " le joueur "+pJi.getNomJoueur()+" a obtenu "+
                        pManche.donnerScoreEquipe1()+" points avec son partenaire";
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
     *
     */
    public void afficherDernierePartieJouee()
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
