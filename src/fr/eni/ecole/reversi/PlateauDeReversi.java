package fr.eni.ecole.reversi;

import fr.eni.ecole.outils.Outils;
import fr.eni.ecole.plateau.Plateau;

public class PlateauDeReversi extends Plateau<Pion> {
    static final int CELLULES =  64;
    public static final int COTE = (int) Math.sqrt(CELLULES);


    public static void main(String[] args) {
        PlateauDeReversi reversi = new PlateauDeReversi();
        reversi.jouer();
    }

    /**
     * Constructeur.
     */
    private PlateauDeReversi() {
        super(COTE, COTE, Pion.LIBRE);
        this.setCell(COTE / 2, COTE / 2, Pion.BLANC);
        this.setCell(COTE / 2 - 1, COTE / 2, Pion.NOIR);
        this.setCell(COTE / 2, COTE / 2 - 1, Pion.NOIR);
        this.setCell(COTE / 2 - 1, COTE / 2 - 1, Pion.BLANC);
    }

    private void jouer() {
        Pion.NOIR.setJoueur();
        Pion.BLANC.setJoueur();
        Pion joueurActif = Pion.NOIR;
        System.out.println();
        System.out.println("REVERSI - " + Pion.NOIR.getJoueur().getNom() + " VS " + Pion.BLANC.getJoueur().getNom());
        this.afficher();
        System.out.println();
        do {
            if (!peutJouer(joueurActif)) {
                if (!peutJouer(joueurActif.autrePion())) { continue; }
                System.out.println(joueurActif.getSymbole() + " n'a aucune position où poser ses pions. Il passe son tour.");
                joueurActif = joueurActif.autrePion();
            }
            System.out.println("Joueur actif : " + joueurActif.getJoueur().getNom()  + " | " + joueurActif.getSymbole());
            boolean aJouer = poser(joueurActif);
            while (!aJouer) { aJouer = poser(joueurActif); }
            this.afficher();
            joueurActif = joueurActif.autrePion();
            System.out.println();
            } while (peutJouer(joueurActif) && peutJouer(joueurActif.autrePion()));
        Outils.clearScreen();
        System.out.println("Aucune position n'est disponible. La partie est terminée.");
        System.out.println((Pion.NOIR.getNombre() > Pion.BLANC.getNombre() ? Pion.NOIR.getJoueur().getNom() : Pion.BLANC.getJoueur().getNom()) + " a gagné.");
    }

    /**
     * Affiche les scores et le plateau de jeu.
     */
    @Override
    protected void afficher() {
        System.out.println(Pion.NOIR.getNombre() + " " + Pion.NOIR.getSymbole());
        System.out.println(Pion.BLANC.getNombre() + " " + Pion.BLANC.getSymbole());
        super.afficher();
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
    protected int inverser(Pion couleur, int ligne, int colonne, boolean inverser) {
        int inversions = 0;
        int axeInversions;
        if (this.getCell(ligne, colonne) == Pion.LIBRE) {
            for (int axeLigne = ligne > 0 ? -1 : 0; axeLigne < (ligne + 1 == COTE ? 1 : 2); axeLigne ++) {
                for (int axeColonne = colonne > 0 ? - 1: 0; axeColonne < (colonne + 1 == COTE ? 1 : 2); axeColonne ++) {
                    if (axeLigne == 0 && axeColonne == 0) { continue; } // Correspond à la case actuelle.
                    axeInversions = testerAxe(couleur, ligne, colonne, axeLigne, axeColonne);
                    inversions += axeInversions;
                    if (inverser && axeInversions != 0) {
                        this.setCell(ligne, colonne, couleur);
                        for (int inversion = 1; inversion < axeInversions + 1; inversion++) {
                            this.setCell(ligne + axeLigne * inversion, colonne + axeColonne * inversion, couleur);
                        }
                    }
                }
            }
        }
        if (inverser) { couleur.gagne(inversions); }
        return inversions;
    }

    public int inverser(Pion couleur, int ligne, int colonne) {
        return inverser(couleur, ligne, colonne, false);
    }

    int inverser(Pion couleur, int cellule) {
        int ligne = cellule / COTE;
        int colonne = cellule % COTE;
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
        if (ligne < 0 || ligne > COTE - 1 || colonne < 0 || colonne > COTE - 1) { return 0; }
        Pion voisin = this.getCell(ligne, colonne);
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
        for (int cellule = 0; cellule < CELLULES; cellule ++) {
                if (inverser(couleur, cellule) > 0) { return true; }
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
        if (this.getCell(ligne, colonne) != Pion.LIBRE || inverser(couleur, ligne, colonne) == 0) {
            System.out.println("La position actuelle est invalide. Veuillez saisir une autre position.");
            return false;
        }
        inverser(couleur, ligne, colonne, true);
        return true;
    }

    private boolean poser(Pion couleur) {
        int[] positions = couleur.getJoueur().jouer(this, couleur);
        System.out.println(
                couleur.getJoueur().getNom() +
                " a sélectionné la position (ligne | colonne) suivante: " +
                (positions[0] + 1) +
                " | " +
                (positions[1] +1)
        );
        return poser(couleur, positions[0], positions[1]);
    }
}
