import gui.GUISimulator;
import java.awt.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Ce test vous permet de choisir par vous même les paramètres 
d'un jeu de votre choix ! Exécutez et découvrez la magie 
par vous même !  */

public class TestBoidsEventSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nbrTypesBoids;
        int nbrBoids;

        // Demande à l'utilisateur de saisir le nombre de types de boids (supérieur à 1)
        do {
            try {
                System.out.print("Veuillez saisir le nombre de types de boids (supérieur à 1) : ");
                nbrTypesBoids = scanner.nextInt();

                if (nbrTypesBoids <= 1) {
                    System.out.println("Veuillez entrer un nombre supérieur à 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un entier valide.");
                scanner.next(); // Pour consommer la saisie incorrecte et éviter une boucle infinie
                nbrTypesBoids = 0; // Réinitialiser la variable pour forcer la répétition de la boucle
            }
        } while (nbrTypesBoids <= 1);

        // Demande à l'utilisateur de saisir le nombre de boids (entre 250 et 1000)
        do {
            try {
                System.out.print("Veuillez saisir le nombre de boids (entre 250 et 1000) : ");
                nbrBoids = scanner.nextInt();

                if (nbrBoids < 250 || nbrBoids > 1000) {
                    System.out.println("Veuillez entrer un nombre entre 250 et 1000.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un entier valide.");
                scanner.next(); // Pour consommer la saisie incorrecte et éviter une boucle infinie
                nbrBoids = 0; // Réinitialiser la variable pour forcer la répétition de la boucle
            }
        } while (nbrBoids < 250 || nbrBoids > 1000);

        scanner.close();

        GUISimulator gui = new GUISimulator(1850, 930, Color.BLACK);

        BoidsEventsSimulator boidsSimulator = new BoidsEventsSimulator(gui, nbrBoids, 1850, 930, nbrTypesBoids);

        gui.setSimulable(boidsSimulator);
    }
}
