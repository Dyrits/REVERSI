package fr.eni.ecole.reversi;

public enum Pion {
    LIBRE,
    BLANC,
    NOIR;
    private int nombre = 2; // Valeur par défaut.
    private Joueur joueur;

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

    /**
     * Permet de sélectionner et identifier un joueur pour un pion.
     */
    public void setJoueur() {
        System.out.println("Quel type de joueur souhaitez-vous pour les " + this.getSymbol() + " ?");
        System.out.println("Entrez un nom pour le joueur si vous souhaitez un joueur humain. Sinon, validez.");
        String nom = Outils.console.nextLine();
        if (nom.length() > 0) { this.joueur = new JoueurHumain(nom); }
        else { this.joueur = new JoueurIA(); }
    }


    // GETTERS & SETTERS

    public int getNombre() { return nombre; }

    /**
     * @return char | Symbole associé à la couleur.
     */
    public char getSymbol() {
        return this == LIBRE ? '·' : this == NOIR ? '●' : 'o';
    }

    public Joueur getJoueur() {
        return this.joueur;
    }
}
