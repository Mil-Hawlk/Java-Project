package partieBelotte;

import java.util.Random;
import java.util.Scanner;

/**
 * Here is a JavaDoc comment in plain HTML for a class 
 * @author pierre
 * @author david
 * @version 1.0
 */
public class Manche {
    private Joueur j1;
    private Joueur j2;
    private Joueur j3;
    private Joueur j4;
    private Couleur atout;
    private int scoreEquipe1 = 0;
    private int scoreEquipe2 = 0;
    
    /**
     *
     * @param pJ1
     * @param pJ2
     * @param pJ3
     * @param pJ4
     */
    protected Manche(Joueur pJ1, Joueur pJ2,
            Joueur pJ3, Joueur pJ4)
    {
        j1 = pJ1;
        j2 = pJ2;
        j3 = pJ3;
        j4 = pJ4;
    }
    
    /* La fonction jouerManche permet de lancer une manche de belotte. Elle 
    modifiera les scores de la manche de chaque joueur */

    /**
     *
     * @param j1
     * @param j2
     * @param j3
     * @param j4
     */

    protected void jouerManche(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
        JeuDeCartes monJeuDeCartes = new JeuDeCartes();
        int joueurQuiCommence = 1; //Au début, le joueur 1 commence, puis on tourne
        int joueurQuiPrend = -1;
        while(joueurQuiPrend==-1)
        {
            monJeuDeCartes = this.donneCartes(monJeuDeCartes);
            // Pour le premier tour, l'atout est la couleur de la carte tournante
            switch(monJeuDeCartes.getCouleurCarteTournante()){
                case "Carreau": atout = Couleur.Carreau;
                    break;
                case "Coeur": atout = Couleur.Coeur;
                    break;
                case "Pique": atout = Couleur.Pique;
                    break;
                case "Trèfle": atout = Couleur.Trefle;
            }
            joueurQuiPrend = this.decisionQuiPrend(joueurQuiCommence);
        }
        System.out.println("L'atout est "+atout.getCouleur());
        monJeuDeCartes.deuxiemeDistribution(j1,j2,j3,j4,joueurQuiPrend);
        Joueur[] listeJoueurs = {j1,j2,j3,j4};
        for(Joueur ji : listeJoueurs)
        {
            //Chaque joueur trie ses cartes
            ji.triPaquetCartes(atout);
            //Verifier les annonces !
            ji.changerScore(this.checkAnnonce(ji));
        }
        //On est partis pour les 8 plis !
        for(int i=1; i<=8; i++)
        {
            System.out.println("\n--------Tour n°"+i+"-------");
            joueurQuiCommence = this.jouerPli(joueurQuiCommence,i);
        }
        this.verifEtMajScoresEquipes(joueurQuiPrend);
        this.afficherScoreEquipes();
        this.mettreAZeroScoresJoueurs();
    }
    
    private JeuDeCartes donneCartes(JeuDeCartes pMonJeuDeCartes)
    {
        // On commence par mélanger, couper puis donner 5 cartes pour les 4 joueurs
        Random alea = new Random();
        pMonJeuDeCartes.melangerJeuDeCartes();
        pMonJeuDeCartes.couper(alea.nextInt(32));
        pMonJeuDeCartes.premiereDistribution(j1, j2, j3, j4);
        pMonJeuDeCartes.afficherCarteTournante();
        return pMonJeuDeCartes;
    }
    
    private int decisionQuiPrend(int pQuiCommence)
    {
        int quiPrend = 0;
        Scanner sc = new Scanner(System.in);
        String choixHumain = "n";
        Joueur[] listeJoueurs = {j1,j2,j3,j4};
        try{
        // 1er tour de choix, avec l'atout qui est la carte au milieu
        for(Joueur ji : listeJoueurs)
        {
            System.out.println("Voici votre jeu, "+ji.getNomJoueur());
            ji.afficherPaquet();
            System.out.println(ji.getNomJoueur()+" voulez-vous prendre en "+
                    atout.getCouleur()+" ? (y/n)");
            choixHumain = sc.nextLine();
            if(choixHumain.compareTo("y")==0)
            {
                quiPrend = ji.getNumeroJoueur();
                System.out.println(ji.getNomJoueur()+" décide de prendre en "
                +atout.getCouleur());
                return quiPrend;
            }
        }
        // 2e tour de choix
        for(Joueur ji : listeJoueurs)
        {
            System.out.println(ji.getNomJoueur()+
                    ", voulez-vous prendre dans une autre couleur ? (y/n)");
            choixHumain = sc.nextLine();
            if(choixHumain.compareTo("y")==0)
            {
                quiPrend = ji.getNumeroJoueur();
                System.out.println("Quelle couleur souhaitez-vous choisir ?");
                System.out.println("1: Carreau\n2: Coeur\n3: Pique\n4: Trèfle");
                choixHumain = sc.nextLine();
                switch(choixHumain){
                    case "1" : atout = Couleur.Carreau; break;
                    case "2" : atout = Couleur.Coeur; break;
                    case "3" : atout = Couleur.Pique; break;
                    case "4" : atout = Couleur.Trefle; break;
                }
                System.out.println(ji.getNomJoueur()+"décide de prendre en "
                +atout.getCouleur());
                return quiPrend;
            }
        }
        // Si le dernier refuse, on doit lancer la redistribution
        if(choixHumain.compareTo("n")==0)
        {
            return -1;
        }
        }
        catch(Exception e){
            System.err.println("Problème lors de la saisie de vos informations");
        }
        return -1;
    }
    
    /* La fonction jouer un pli permettra de jouer un pli, sachant quel est l'atout
    et quelles sont les cartes jetées par les js. (Sortie relative au joueur qui commence, 
    score positif si victoire de son équipe, score négatif sinon?)*/
    private int jouerPli(int pJoueurQuiCommence, int pNumeroPli)
    {
        //On retient le numéro du joueur qui démarre pour compter les points
        int memoireJoueurQuiCommence = pJoueurQuiCommence;
        Carte tourDeJeu[] = new Carte[4];//Représente le tas du milieu (pli)
        Joueur[] listeJoueurs = {j1,j2,j3,j4};
        for(int i=0; i<4; i++)//Une carte par joueur
        {
            for(Joueur ji : listeJoueurs)
            {
                if(pJoueurQuiCommence==ji.getNumeroJoueur())
                {
                    tourDeJeu[i] = this.choisirUneCarte(ji,i,tourDeJeu);
                    while(tourDeJeu[i]==null) // Si les règles ne passent pas
                    {
                        tourDeJeu[i] = this.choisirUneCarte(ji,i,tourDeJeu);
                    }
                    System.out.println(ji.getNomJoueur()+" joue son "+
                           tourDeJeu[i].getFigure()+" de "
                            +tourDeJeu[i].getCouleur()+"\n");
                }
            }
            switch(pJoueurQuiCommence){
                case 1 : pJoueurQuiCommence = 2; break;
                case 2 : pJoueurQuiCommence = 3; break;
                case 3 : pJoueurQuiCommence = 4; break;
                case 4 : pJoueurQuiCommence = 1; break;
            }
        }
        //Arbitrage : Quelle est la carte gagnante, et à qui est-elle?
        pJoueurQuiCommence = this.arbitrage(tourDeJeu, memoireJoueurQuiCommence, pNumeroPli);
        return pJoueurQuiCommence;
    }
    
    private Carte choisirUneCarte(Joueur pJoueur, int tourDuJoueur, Carte[] pTapis)
    {
        switch (tourDuJoueur)
        {
            case 0: System.out.println(pJoueur.getNomJoueur()+
                    ", tu est le premier à jouer !");
                    break;
            case 1: System.out.println(pJoueur.getNomJoueur()+
                    ", tu est le deuxieme à jouer !");
                    break;
            case 2: System.out.println(pJoueur.getNomJoueur()+
                    ", tu est le troisième à jouer !");
                    break;
            case 3: System.out.println(pJoueur.getNomJoueur()+
                    ", tu est le quatrième à jouer !");
                    break;
        }
        System.out.println("Voici ton jeu :");
        pJoueur.afficherPaquet();
        System.out.println("Quelle carte veut-tu jouer ?");
        Scanner sc = new Scanner(System.in);
        String choixHumain;
        choixHumain = sc.nextLine();
        int choixHumainInteger=-1;
        switch (choixHumain)
            {
                case "1": choixHumainInteger = 0;
                    break;
                case "2": choixHumainInteger = 1;
                    break;
                case "3": choixHumainInteger = 2;
                    break;
                case "4": choixHumainInteger = 3;
                    break;
                case "5": choixHumainInteger = 4;
                    break;
                case "6": choixHumainInteger = 5;
                    break;
                case "7": choixHumainInteger = 6;
                    break;
                case "8": choixHumainInteger = 7;
                    break;
                default: break;
            }
        try{
            Carte etapeVerifCarte = pJoueur.getCarte(choixHumainInteger);
            if(this.verificationReglesCarte(etapeVerifCarte, pJoueur, pTapis))
            {
                return etapeVerifCarte;
            }
            else
            {
                pJoueur.setCarte(etapeVerifCarte, choixHumainInteger);
                return null;
            }
        }
        catch (Exception e){
            System.err.println("Choix mal établi, un chiffre aléatoire va être saisi");
            Random rd = new Random();
            boolean valid = false;
            while(valid==false)
            {
                choixHumainInteger=rd.nextInt(8);
                if(pJoueur.estUneCarte(choixHumainInteger))
                {
                    valid=true;
                }
            }
            Carte etapeVerifCarte = pJoueur.getCarte(choixHumainInteger);
            if(this.verificationReglesCarte(etapeVerifCarte, pJoueur, pTapis))
            {
                return etapeVerifCarte;
            }
            else
            {
                pJoueur.setCarte(etapeVerifCarte, choixHumainInteger);
                return null;
            }
        }
    }
    
    private boolean verificationReglesCarte(Carte pCarteValider, Joueur pJoueur, Carte[] pTapis)
    {
        boolean resultat = true;
        Carte verification;
        int compteurMainVide = 0;
        if(pTapis[0]!=null)// Si un joueur a déjà joué
        {
            if(pTapis[0].getCouleur().compareTo(pCarteValider.getCouleur())!=0)
            {//Si on ne joue pas la même couleur que le premier joueur
                if(pCarteValider.getCouleur().compareTo(atout.getCouleur())!=0)
                {//si on ne joue pas atout
                    for(int i=0; i<8; i++) 
                    {// On vérifie qu'aucune des cartes n'est atout ou couleur maître
                        verification = pJoueur.getCarte(i);
                        if(verification==null)
                        {
                            compteurMainVide += 1;
                        }
                        if(verification!=null) // Si il y a une carte dans la main
                        {
                            if(((verification.getCouleur().compareTo(atout.getCouleur())==0)
                                ||(verification.getCouleur().compareTo(pTapis[0].getCouleur())==0))
                                &&(compteurMainVide<8))
                            {//La carte a la 1ere couleur ou la couleur de l'atout
                                System.out.println("\nLes règles officielles de"+
                                        " la belote ne vous permettent pas de"+
                                        " jouer cette carte !!");
                                System.out.println("Rappel : atout "+atout.getCouleur()+"\n");
                                resultat = false;
                            }
                        }
                        pJoueur.setCarte(verification, i);
                    }
                }
            }
        }
        return resultat;
    }
    
    private int arbitrage(Carte[] pTourDeJeu, int pMemoireJoueurQuiCommence, int pNumeroPli)
    {
        int i = 0;
        int rangCarteForte = 0;
        CarteValeur valeurCarteForte = CarteValeur.sept;
        boolean victory = false;
        // Calcul de la carte gagnante
        /* Premier cas de figure : Il y a un atout */
        for(i=0; i<4; i++) //S'il y en a, le plus gros atout gagne
        {
            if((pTourDeJeu[i].getCouleur().matches(atout.getCouleur()))
                    &&(((pTourDeJeu[i].getFigure().compareTo(valeurCarteForte))<0)
                    ||(pTourDeJeu[i].getValeurAtout()>valeurCarteForte.getValeurAtout())))
            {
                rangCarteForte = i;
                valeurCarteForte = pTourDeJeu[i].getFigure();
                victory = true;
            }
        }
        /* Deuxième cas de figure, il n'y a pas d'atout */
        if(victory == false)
            {
            valeurCarteForte = pTourDeJeu[0].getFigure();
            for(i=1; i<4; i++) //On se réfère à la première carte jouée
            {
                if((pTourDeJeu[i].getCouleur().matches(pTourDeJeu[0].getCouleur()))
                    &&(pTourDeJeu[i].getFigure().compareTo(valeurCarteForte))<0)
                {
                    rangCarteForte = i;
                    valeurCarteForte = pTourDeJeu[i].getFigure();
                    victory = true;
                }
            }
        }
        //Calcul des points et attribution de ceux-ci au joueur
        int totalPoints = this.calculPointsSurTable(pTourDeJeu);
        /* La carte gagnante est au rang i, sachant que le rang 0 appartient au
        joueur pMemoireJoueurQuiCommence */
        int rangGagnant = ((rangCarteForte)%4)+pMemoireJoueurQuiCommence;
        switch (rangCarteForte)
        {
            case 0: 
                rangGagnant = pMemoireJoueurQuiCommence%4;
                break;
            case 1: 
                rangGagnant = (pMemoireJoueurQuiCommence+1)%4;
                break;
            case 2: 
                rangGagnant = (pMemoireJoueurQuiCommence+2)%4;
                break;
            case 3: 
                rangGagnant = (pMemoireJoueurQuiCommence+3)%4;
                break;
        }
        if(rangGagnant==0)
        {
            rangGagnant=4;
        }
        //Dix de der
        if(pNumeroPli==8)
        {
            System.out.println("Il remporte le dix de der");
            totalPoints = totalPoints + 10;
        }
        this.attributionPoints(rangGagnant, totalPoints);
        return rangGagnant;
    }
    
    private void attributionPoints(int pRangGagnant, int pNbPoints)
    {
        Joueur[] listeJoueurs = {j1,j2,j3,j4};
        for(Joueur ji : listeJoueurs)
        {
            if(pRangGagnant == ji.getNumeroJoueur())
            {
                ji.changerScore(pNbPoints);
                System.out.println(ji.getNomJoueur()+" a rapporté "+
                        pNbPoints+" à son équipe !");
            }
        }
    }
    
    private int calculPointsSurTable(Carte[] pJeu)
    {
        int totalPoints = 0;
        for(int i=0; i<4; i++)
        {
            if(pJeu[i].getCouleur().matches(atout.getCouleur()))
            {
                totalPoints += pJeu[i].getValeurAtout();
            }
            else
            {
                totalPoints += pJeu[i].getValeurNormale();
            }
        }
        return totalPoints;
    }
    
    /**
     *
     */
    public void afficherScoreEquipes()
    {
        int score = 0;
        if(j1.getNumeroEquipe()==j2.getNumeroEquipe())
        {
            System.out.println("Score de l'équipe de "+j1.getNomJoueur()+
                    " et de "+j2.getNomJoueur()+" : "+scoreEquipe1);
            System.out.println("Score de l'équipe de "+j3.getNomJoueur()+
                    " et de "+j4.getNomJoueur()+" : "+scoreEquipe2);
        }
        if(j1.getNumeroEquipe()==j3.getNumeroEquipe())
        {
            System.out.println("Score de l'équipe de "+j1.getNomJoueur()+
                    " et de "+j3.getNomJoueur()+" : "+scoreEquipe1);
            System.out.println("Score de l'équipe de "+j2.getNomJoueur()+
                    " et de "+j4.getNomJoueur()+" : "+scoreEquipe2);
        }
        if(j1.getNumeroEquipe()==j4.getNumeroEquipe())
        {
            System.out.println("Score de l'équipe de "+j1.getNomJoueur()+
                    " et de "+j4.getNomJoueur()+" : "+scoreEquipe1);
            System.out.println("Score de l'équipe de "+j3.getNomJoueur()+
                    " et de "+j2.getNomJoueur()+" : "+scoreEquipe2);
        }
    }
    
    private void verifEtMajScoresEquipes(int pJoueurQuiPrend)
    {
        int score1 = 0;
        int score2 = 0;
        if(j1.getNumeroEquipe()==j2.getNumeroEquipe())
        {
            //On additionne les scores des joueurs
            score1 = j1.getScore() + j2.getScore();
            score2 = j3.getScore() + j4.getScore();
            //Règle du KAPO
            if(score1==0)
            {
                System.out.println("Kapo équipe 1 !!");
                score2 = 252;
            }
            if(score2==0)
            {
                System.out.println("Kapo équipe 2 !!");
                score1 = 252;
            }
            // On vérifie que le preneur n'est pas dedans
            if((pJoueurQuiPrend==j1.getNumeroEquipe()
                ||pJoueurQuiPrend==j2.getNumeroEquipe())
                &&(score1<82))
            {
                score1=0;
                score2=162;
                System.out.println("Equipe 1 dedans !!");
            }
            if((pJoueurQuiPrend==j3.getNumeroEquipe()
                ||pJoueurQuiPrend==j4.getNumeroEquipe())
                &&(score1<82))
            {
                score1=162;
                score2=0;
                System.out.println("Equipe 2 dedans !!");
            }
        }
        if(j1.getNumeroEquipe()==j3.getNumeroEquipe())
        {
            score1 = j1.getScore() + j3.getScore();
            score2 = j2.getScore() + j4.getScore();
            //Règle du KAPO
            if(score1==0)
            {
                System.out.println("Kapo équipe 1 !!");
                score2 = 252;
            }
            if(score2==0)
            {
                System.out.println("Kapo équipe 2 !!");
                score1 = 252;
            }
            if((pJoueurQuiPrend==j1.getNumeroEquipe()
                ||pJoueurQuiPrend==j3.getNumeroEquipe())
                &&(score1<82))
            {
                score1=0;
                score2=162;
                System.out.println("Equipe 1 dedans !!");
            }
            if((pJoueurQuiPrend==j2.getNumeroEquipe()
                ||pJoueurQuiPrend==j4.getNumeroEquipe())
                &&(score1<82))
            {
                score1=162;
                score2=0;
                System.out.println("Equipe 2 dedans !!");
            }
        }
        if(j1.getNumeroEquipe()==j4.getNumeroEquipe())
        {
            score1 = j1.getScore() + j4.getScore();
            score2 = j3.getScore() + j2.getScore();
            //Règle du KAPO
            if(score1==0)
            {
                System.out.println("Kapo équipe 1 !!");
                score2 = 252;
            }
            if(score2==0)
            {
                System.out.println("Kapo équipe 2 !!");
                score1 = 252;
            }
            if((pJoueurQuiPrend==j1.getNumeroEquipe()
                ||pJoueurQuiPrend==j4.getNumeroEquipe())
                &&(score1<82))
            {
                score1=0;
                score2=162;
                System.out.println("Equipe 1 dedans !!");
            }
            if((pJoueurQuiPrend==j2.getNumeroEquipe()
                ||pJoueurQuiPrend==j3.getNumeroEquipe())
                &&(score1<82))
            {
                score1=162;
                score2=0;
                System.out.println("Equipe 2 dedans !!");
            }
        }
        scoreEquipe1 += score1;
        scoreEquipe2 += score2;
    }
    
    private void mettreAZeroScoresJoueurs()
    {
        j1.changerScore(-j1.getScore());
        j2.changerScore(-j2.getScore());
        j3.changerScore(-j3.getScore());
        j4.changerScore(-j4.getScore());
    }
    
    private int checkAnnonce(Joueur pJoueur)
    {
        //On verra à la fin, on se fait pas chier
        return 0;
    }
    
    /**
     *
     * @return
     */
    public int donnerScoreEquipe1()
    {
        return scoreEquipe1;
    }
    
    /**
     *
     * @return
     */
    public int donnerScoreEquipe2()
    {
        return scoreEquipe2;
    }
}

