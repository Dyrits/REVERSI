package fr.eni.ecole.plateau;

import java.util.ArrayList;
import java.util.List;

public class Plateau <T extends Affichage> {
    private int lignes, colonnes;
    private List<T> plateau;

    /**
     * Constructeur.
     * @param lignes int | Nombre de lignes
     * @param colonnes int | Nombre de colonnes
     * @param initialisation T | Valeur par défaut pour chaque cases du tableau.
     */
    public Plateau(int lignes, int colonnes, T initialisation) {
        setLignes(lignes);
        setColonnes(colonnes);
        setPlateau(lignes, colonnes, initialisation);
    }

    /**
     * Affiche le plateau.
     */
    protected void afficher() {
        System.out.print("   ");
        for (int colonne = 1; colonne < this.colonnes + 1; colonne ++) {
            System.out.print(colonne + "  ");
        }
        System.out.println();
        int numerotation = 0;
        for (int ligne = 0; ligne < this.lignes; ligne ++) {
            System.out.print(++ numerotation);
            System.out.print("  ");
            for (int colonne = 0; colonne < this.colonnes; colonne ++) {
                System.out.print(this.getCell(ligne, colonne).getSymbole());
                System.out.print("  ");
            }
            System.out.println();
        }
    }


    // GETTERS & SETTERS

    /**
     * @param ligne int | Numéro de ligne.
     * @param colonne int | Numéro de colonne.
     * @return int | Numéro de la case correspondante à la ligne et à la colonne (à partir de 0).
     */
    public int getCellNumber(int ligne, int colonne) {
        return ligne * this.colonnes + colonne;
    }

    /**
     * @param ligne int | Numéro de ligne.
     * @param colonne int | Numéro de colonne.
     * @return T | Valeur de la case correspondante.
     */
    public T getCell(int ligne, int colonne) {
        return this.plateau.get(this.getCellNumber(ligne, colonne));
    }

    /**
     * @param ligne int | Numéro de ligne.
     * @param colonne int | Numéro de colonne.
     * @param valeur T | Valeur à assigner à la case correspondante.
     */
    public void setCell(int ligne, int colonne, T valeur) {
        plateau.set(this.getCellNumber(ligne, colonne), valeur);
    }

    /**
     * Initialise le nombre de lignes à 1 ou à la valeur entrée en paramètre.
     * @param lignes int | Nombre de lignes.
     */
    public void setLignes(int lignes) {
        this.lignes = Math.max(lignes, 1);
    }

    /**
     * Initialise le nombre de colonnes à 1 ou à la valeur entrée en paramètre.
     * @param colonnes int | Nombre de colonnes.
     */
    public void setColonnes(int colonnes) {
        this.colonnes = Math.max(colonnes, 1);
    }

    /**
     * Initialise le plateau et la valeur de ses cases par défaut.
     * @param lignes int | Nombre de lignes.
     * @param colonnes int | Nombre de colonnes.
     * @param initialisation T | Valeur par défaut.
     */
    public void setPlateau(int lignes, int colonnes, T initialisation) {
        int cellules = lignes * colonnes;
        this.plateau = new ArrayList<>(cellules);
        for (int cellule = 0; cellule < cellules; cellule ++) {
            this.plateau.add(initialisation);
        }
    }
}
