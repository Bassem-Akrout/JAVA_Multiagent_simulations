import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BoidsSimulator implements Simulable {
    private Boids boids; // Référence vers l'objet Boids
    private GUISimulator gui; // Référence vers l'interface graphique
    private double screenWidth;
    private double screenHeight;

    public BoidsSimulator(GUISimulator gui, int numBoids, double screenWidth, double screenHeight) {
        this.boids = new Boids(numBoids, screenWidth, screenHeight);
        this.gui = gui;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        initializeGraphics();
    }

    private void initializeGraphics() {
        // Ajouter les éléments graphiques initiaux, par exemple les boids
        ArrayList<Boid> currentBoids = boids.getBoids();
        for (Boid boid : currentBoids) {
            Point2D.Double position = boid.getPosition();
            gui.addGraphicalElement(new Rectangle((int) position.x, (int) position.y, Color.BLUE,Color.BLACK, 10, 10));

        }
        // Ajoutez d'autres éléments graphiques si nécessaire
    }

    @Override
    public void next() {
        // Logique pour la prochaine étape de simulation
        boids.nextLogic(screenWidth, screenHeight);

        // Effacer l'écran
        gui.reset();

        // Mettre à jour les éléments graphiques
        ArrayList<Boid> currentBoids = boids.getBoids();
        for (Boid boid : currentBoids) {
            Point2D.Double position = boid.getPosition();
            gui.addGraphicalElement(new Rectangle((int) position.x, (int) position.y, Color.BLUE,Color.BLACK, 10, 10));
        }
        // Ajoutez d'autres éléments graphiques si nécessaire
    }

    @Override
    public void restart() {
        // Réinitialisation de la simulation
        boids.reInit();

        // Effacer l'écran
        gui.reset();

        // Réinitialiser les éléments graphiques
        initializeGraphics();
    }
}
