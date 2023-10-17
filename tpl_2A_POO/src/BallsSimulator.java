import gui.GUISimulator;
import java.awt.Color;
import java.awt.Point;
import gui.Rectangle;
import java.util.ArrayList;
import gui.Simulable;


public class BallsSimulator implements Simulable {
    private Balls balls;  // Référence vers l'objet Balls
    private GUISimulator gui;  // Référence vers l'interface graphique
    
    public BallsSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.gui = gui;

    }
    
    

    @Override
    public void next() {
        // Mise à jour de l'état des balles (par exemple, translation)
        balls.translate(1, 1);

        // Effacer l'écran
        gui.reset();

// Dessiner les balles sous forme de cercles
for (Point ball : balls.getBalls()) {
    int centerX = ball.x;
    int centerY = ball.y;
    int radius = 20; // Rayon du cercle

    int numRectangles = 36; // Nombre de rectangles pour former le cercle
    for (int i = 0; i < numRectangles; i++) {
        double angle = 2 * Math.PI * i / numRectangles;
        int rectX = (int) (centerX + radius * Math.cos(angle));
        int rectY = (int) (centerY + radius * Math.sin(angle));
        gui.addGraphicalElement(new Rectangle(rectX, rectY, Color.RED, Color.RED, 5));
    }
    gui.addGraphicalElement(new Rectangle(centerX, centerY, Color.RED, Color.RED, 30));
}
    }

    @Override
    public void restart() {
        // Réinitialisation de l'état des balles
        balls.reInit();

        // Effacer l'écran
        gui.reset();

// Dessiner les balles sous forme de cercles
for (Point ball : balls.getBalls()) {
    int centerX = ball.x;
    int centerY = ball.y;
    int radius = 20; // Rayon du cercle

    int numRectangles = 36; // Nombre de rectangles pour former le cercle
    for (int i = 0; i < numRectangles; i++) {
        double angle = 2 * Math.PI * i / numRectangles;
        int rectX = (int) (centerX + radius * Math.cos(angle));
        int rectY = (int) (centerY + radius * Math.sin(angle));
        gui.addGraphicalElement(new Rectangle(rectX, rectY, Color.RED, Color.RED, 5));
    }
    gui.addGraphicalElement(new Rectangle(centerX, centerY, Color.RED, Color.RED, 30));

}
    }
}



