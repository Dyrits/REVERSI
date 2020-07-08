package fr.eni.ecole.reversi;

public enum Pion {
    LIBRE,
    BLANC,
    NOIR;

    private int nombre = 2; // Valeur par défaut.

    public int getNombre() { return nombre; }

    /**
     * @return char | Symbole associé à la couleur.
     */
    public char getSymbol() {
        return this == LIBRE ? '·' : this == NOIR ? '●' : 'o';
    }

    /**
     * @return Pion | Couleur du pion de la couleur opposée.
     */
    public Pion autrePion() {
        return this == LIBRE ? LIBRE : this == NOIR ? BLANC : NOIR;
    }

    /**
     * @param inversions int | Nombre de pions changeant de couleurs.
     */
    public void gagne(int inversions) {
        this.nombre += inversions + 1;
        this.autrePion().nombre -= inversions;
    }
}
