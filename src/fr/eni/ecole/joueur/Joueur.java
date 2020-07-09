package fr.eni.ecole.joueur;

import fr.eni.ecole.reversi.Pion;
import fr.eni.ecole.reversi.PlateauDeReversi;

public interface Joueur {

    int[] jouer(PlateauDeReversi reversi, Pion couleur);

    String getNom();
}
