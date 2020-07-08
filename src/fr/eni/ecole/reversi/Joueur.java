package fr.eni.ecole.reversi;

public interface Joueur {

    public int[] jouer(PlateauDeReversi reversi, Pion couleur);

    public String getNom();
}
