package fr.eni.ecole.reversi;


public class JoueurHumain implements Joueur {
    String nom;

    /**
     * Constructeur
     *
     * @param nom String | Nom du joueur.
     */
    public JoueurHumain(String nom) {
        setNom(nom);
    }

    /**
     * @return int[] | Tableau comprenant la ligne et la colonne sélectionnées par le joueur.
     */
    public int[] jouer() {
        int[] positions = new int[2];
        positions[0] = Outils.saisirPosition("Ligne = ", PlateauDeReversi.COTE);
        positions[1] = Outils.saisirPosition("Colonne = ", PlateauDeReversi.COTE);
        return positions;
    }

    /**
     * @param reversi PlateauDeReversi | Paramètre inutile.
     * @param couleur Pion | Paramètre inutile.
     * @return int[] | Tableau comprenant la ligne et la colonne sélectionnées par le joueur.
     */
    @Override
    public int[] jouer(PlateauDeReversi reversi, Pion couleur) {
        return this.jouer();
    }





    // GETTERS AND SETTERS

    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
