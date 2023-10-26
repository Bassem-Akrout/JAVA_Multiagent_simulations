import gui.GUISimulator;
import java.util.Scanner;
import java.awt.Color;

public class TestCellsSimulator {
    public static void main(String[] args) {


        int modelType;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                // Demande à l'utilisateur de choisir un modèle (0, 1 ou 2) pour la simulation
                System.out.print("Choisissez un modèle (0, 1 ou 2) \n - 0 pour le jeu de la vie de Conway \n - 1 pour le jeu de l'immigration \n - 2 pour le modèle de Schelling : ");
                modelType = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrée incorrecte. Veuillez saisir un modèle valide (0, 1 ou 2).");
                scanner.nextLine(); // Pour vider la ligne incorrecte du scanner
                modelType = -1; // Une valeur non valide pour le modèle
            }
        } while (modelType != 0 && modelType != 1 && modelType != 2);

        int nbrRows, nbrColumns, nbrStates, threshold;

        do {
            try {
                // Demande à l'utilisateur de spécifier le nombre de lignes et de colonnes pour la grille de simulation
                System.out.print("Nombre de lignes dans la grille entre 5 et 100 : ");
                nbrRows = scanner.nextInt();
                System.out.print("Nombre de colonnes dans la grille entre 5 et 150 : ");
                nbrColumns = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrée incorrecte. Veuillez saisir un nombre valide entre 5 et 100 pour les lignes et entre 5 et 150 pour les colonnes.");
                scanner.nextLine(); // Pour vider la ligne incorrecte du scanner
                nbrRows = -1; // Une valeur non valide pour le nombre de lignes
                nbrColumns = -1; // Une valeur non valide pour le nombre de colonnes
            }
        } while (nbrRows < 5 || nbrRows > 100 || nbrColumns < 5 || nbrColumns > 150);

        if (modelType >= 1) {
            do {
                try {
                    // Demande à l'utilisateur de spécifier le nombre d'états (couleurs) pour la simulation
                    System.out.print("Nombre d'états (couleurs) pas de limite mais bon un nombre strictement positif :-) : ");
                    nbrStates = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrée incorrecte. Veuillez saisir un nombre strictement positif pour le nombre d'états.");
                    scanner.nextLine(); // Pour vider la ligne incorrecte du scanner
                    nbrStates = -1; // Une valeur non valide pour le nombre d'états
                }
            } while (nbrStates <= 0);
        } else {
            nbrStates = 2; // Valeur par défaut pour les autres modèles
        }

        if (modelType == 2) {
            do {
                try {
                    // Demande à l'utilisateur de spécifier le seuil K pour le modèle de Schelling
                    System.out.print("Seuil K pour le déplacement, logiquement entre 0 et 8 : ");
                    threshold = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrée incorrecte. Veuillez saisir un nombre valide entre 0 et 8 pour le seuil K.");
                    scanner.nextLine(); // Pour vider la ligne incorrecte du scanner
                    threshold = -1; // Une valeur non valide pour le seuil K
                }
            } while (threshold < 0 || threshold > 8);
        } else {
            // Valeur par défaut pour les autres modèles
            threshold = 2;
        }

        scanner.close(); // Fermer le scanner pour éviter les fuites de ressources

        // Création de l'interface graphique avec une résolution de 1850x930 pixels et un fond noir
        GUISimulator gui = new GUISimulator(1850, 930, Color.BLACK);
        // Création de l'objet de simulation CellsSimulator avec les paramètres saisis
        CellsSimulator cellsSimulator = new CellsSimulator(gui, 1850, 930, nbrRows, nbrColumns, nbrStates, threshold, modelType);
        // Réinitialisation de la simulation
        cellsSimulator.restart();
        // Définition du simulateur pour l'interface graphique
        gui.setSimulable(cellsSimulator);
    }
}
