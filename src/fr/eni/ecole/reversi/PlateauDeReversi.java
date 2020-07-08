package fr.eni.ecole.reversi;

import java.util.Arrays;
import java.util.Scanner;

public class PlateauDeReversi {
    private static final int CASES =  64;
    private static final int COTE = (int) Math.sqrt(CASES);
    private Scanner console = new Scanner(System.in);
    private Pion[][] plateau;

    public static void main(String[] args) {
        PlateauDeReversi reversi = new PlateauDeReversi();
        reversi.jouer();
    }

    /**
     * Constructeur du plateau de jeu.
     */
    private PlateauDeReversi() {
        this.plateau = new Pion[COTE][COTE];
        for (Pion[] ligne : plateau) {
            Arrays.fill(ligne, Pion.LIBRE);
        }
        plateau[4][4] = Pion.BLANC;
        plateau[3][4] = Pion.NOIR;
        plateau[4][3] = Pion.NOIR;
        plateau[3][3] = Pion.BLANC;
    }

    private void jouer() {
        Pion joueurActif = Pion.NOIR;
        do {
            clearScreen();
            this.afficher();
            if (!peutJouer(joueurActif)) {
                if (!peutJouer(joueurActif.autrePion())) { continue; }
                System.out.println(joueurActif.getSymbol() + " n'a aucune position où poser ses pions. Il passe son tour.");
                joueurActif = joueurActif.autrePion();
            }
            System.out.println("Joueur actif : " + joueurActif.getSymbol());
            boolean aJouer = poser(joueurActif);
            while (!aJouer) { aJouer = poser(joueurActif); }
            joueurActif = joueurActif.autrePion();
            } while (peutJouer(joueurActif) && peutJouer(joueurActif.autrePion()));
        clearScreen();
        System.out.println("Aucune position n'est disponible. La partie est terminée.");
        this.afficher();
    }

    /**
     * Affiche les scores et le plateau de jeu.
     */
    private void afficher() {
        System.out.println(Pion.NOIR.getNombre() + " " + Pion.NOIR.getSymbol());
        System.out.println(Pion.BLANC.getNombre() + " " + Pion.BLANC.getSymbol());
        System.out.println("   1  2  3  4  5  6  7  8");
        int numerotation = 0;
        for (Pion[] ligne : this.plateau) {
            System.out.print(++ numerotation);
            System.out.print("  ");
            for (int colonne = 0; colonne < COTE; colonne ++) {
                System.out.print(ligne[colonne].getSymbol());
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    /**
     * Permet d'obtenir le nombre de cases inversées en posant un pion d'une couleur à des coordonnées spécifiées.
     *
     * @param couleur Pion | Pion de la couleur testée.
     * @param ligne int | Numéro de la ligne (0 à COTE).
     * @param colonne int | Numéro de la colonne (0 à COTE).
     * @param inverser boolean | Inverse les pions identifiés si "true".
     * @return int | Nombre d'inversions possibles.
     */
    private int inverser(Pion couleur, int ligne, int colonne, boolean inverser) {
        int inversions = 0;
        int axeInversions;
        if (this.plateau[ligne][colonne] == Pion.LIBRE) {
            for (int axeLigne = ligne > 0 ? -1 : 0; axeLigne < (ligne + 1 == COTE ? 1 : 2); axeLigne ++) {
                for (int axeColonne = colonne > 0 ? - 1: 0; axeColonne < (colonne + 1 == COTE ? 1 : 2); axeColonne ++) {
                    if (axeLigne == 0 && axeColonne == 0) { continue; } // Correspond à la case actuelle.
                    axeInversions = testerAxe(couleur, ligne, colonne, axeLigne, axeColonne);
                    inversions += axeInversions;
                    if (inverser && axeInversions != 0) {
                        this.plateau[ligne][colonne] = couleur;
                        for (int inversion = 1; inversion < axeInversions + 1; inversion++) {
                            this.plateau[ligne + axeLigne * inversion][colonne + axeColonne * inversion] = couleur;
                        }
                        couleur.gagne(axeInversions);
                    }
                }
            }
        }
        return inversions;
    }

    private int inverser(Pion couleur, int ligne, int colonne) {
        return inverser(couleur, ligne, colonne, false);
    }

    /**
     * Permet de parcourir une directions selon les axes spécifiées afin d'obtenir le nombre de cases inversées en posant un pion à des coordonnées spécifiées.
     *
     * @param couleur Pion | Pion de couleur étant testé.
     * @param ligne int | Numéro de la ligne (0 à COTE).
     * @param colonne int | Numéro de la colonne (0 à COTE).
     * @param axeLigne int | Direction de l'axe sur les lignes (-1, 0, (+)1).
     * @param axeColonne int | Direction de l'axe sur les colonnes (-1, 0, (+)1).
     * @param inversions int | Nombre d'inversions accumulées, récursivement.
     * @return int | Nombres d'inversions accumulées, récursivement.
     */
    private int testerAxe(Pion couleur, int ligne, int colonne, int axeLigne, int axeColonne, int inversions) {
        ligne += axeLigne;
        colonne += axeColonne;
        Pion voisin = this.plateau[ligne][colonne];
        if (voisin == Pion.LIBRE) { return 0; }
        if (voisin == couleur.autrePion()) {
            inversions = testerAxe(couleur, ligne, colonne, axeLigne, axeColonne, inversions + 1);
        }
        return inversions;
    }

    private int testerAxe(Pion couleur, int ligne, int colonne, int axeLigne, int axeColonne) {
        return testerAxe(couleur, ligne, colonne, axeLigne, axeColonne, 0);
    }

    /**
     * Permet de tester, pour un pion d'une couleur, s'il est toujours possible de jouer.
     *
     * @param couleur Pion | Pion de la couleur testée.
     * @return boolean | "true" s'il reste des mouvements possible, sinon "false".
     */
    private boolean peutJouer(Pion couleur) {
        System.out.println("Test");
        for (int ligne = 0; ligne < COTE; ligne ++) {
            for (int colonne = 0; colonne < COTE; colonne ++) {
                if (inverser(couleur, ligne, colonne) > 0) { return true; }
            }
        }
        return false;
    }

    /**
     *
     * @param couleur Pion | Pion de la couleur jouée.
     * @param ligne int | Numéro de la ligne (0 à COTE).
     * @param colonne int | Numéro de la colonne (0 à COTE).
     * @return boolean | "true" si la pose du pion a été effectuée correctement, sinon, "false".
     */
    private boolean poser(Pion couleur, int ligne, int colonne) {
        if (this.plateau[ligne][colonne] != Pion.LIBRE || inverser(couleur, ligne, colonne) == 0) {
            System.out.println("La position actuelle est invalide. Veuillez saisir une autre position.");
            return false;
        }
        inverser(couleur, ligne, colonne, true);
        return true;
    }

    private boolean poser(Pion couleur) {
        int ligne = saisirPosition("Ligne : ");
        int colonne = saisirPosition("Colonne : ");
        return poser(couleur, ligne, colonne);
    }

    /**
     * Permet la saisie d'une position (ligne ou colonne).
     *
     * @param string String | Message à afficher.
     * @return int | Index de la position.
     */
    private int saisirPosition(String string) {
        boolean invalidite = true;
        int position = 0;
        do {
            try {
                System.out.print(string);
                position = console.nextInt();
                invalidite = position < 1 || position > COTE;
                if (invalidite) {
                    System.out.println("Le numéro saisie est invalide. Veuillez-réessayer.");
                }
            } catch (Exception exception) {
                System.out.println("La saisie est invalide. Veuillez-ré-essayez.");
            } finally {
                console.nextLine();
            }
        } while (invalidite);
        return position - 1;
    }

    /**
     * Permet de remonter le contenu de la console, pour la vider.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
