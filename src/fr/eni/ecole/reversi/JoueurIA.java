package fr.eni.ecole.reversi;

import java.util.Random;

public class JoueurIA implements Joueur {
    private static final String[] noms = {"Jarvis", "Bot", "IA", "Asimov", "Claptrap"};
    String nom;

    public JoueurIA() {
        setNom();
    }

    /**
     * @return int[] | Tableau comprenant la ligne et la colonne fournissant le nombre d'inversion le plus élevée.
     */
    @Override
    public int[] jouer(PlateauDeReversi reversi, Pion couleur) {
        int[] positions = {0, 0};
        int maximum = 0;
        for (int ligne = 0; ligne < PlateauDeReversi.COTE; ligne ++) {
            for (int colonne = 0; colonne < PlateauDeReversi.COTE; colonne++) {
                if (reversi.plateau[ligne][colonne] != Pion.LIBRE) { continue; }
                int inversions = reversi.inverser(couleur, ligne, colonne);
                if (inversions > maximum) {
                    maximum = inversions;
                    positions[0] = ligne;
                    positions[1] = colonne;
                }
            }
        }
        return positions;
    }


    // GETTERS AND SETTERS

    public void setNom() {
        this.nom = noms[new Random().nextInt(noms.length)];
    }

    @Override
    public String getNom() {
        return nom;
    }
}
