package fr.eni.ecole.outils;

import java.util.Scanner;

public class Outils {
    public static Scanner console = new Scanner(System.in);

    /**
     * Permet la saisie d'une position (ligne ou colonne).
     * @param string String | Message à afficher.
     * @return int | Index de la position.
     */
    public static int saisirPosition(String string, int maximum) {
        boolean invalidite = true;
        int position = 0;
        do {
            try {
                System.out.print(string);
                position = console.nextInt();
                invalidite = position < 1 || position > maximum;
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
