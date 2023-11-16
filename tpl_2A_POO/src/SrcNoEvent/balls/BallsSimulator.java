import gui.GUISimulator;
import java.awt.Color;
import java.awt.Point;
import gui.Simulable;
import gui.Oval;
import gui.Rectangle;

import java.util.ArrayList;


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
        gui.addGraphicalElement(new Rectangle( (1850/2), (930/2), Color.RED, Color.BLACK, 1848,928));

        // Dessiner les balles sous forme de cercles
        ArrayList<Point> ballsList= this.balls.getBalls();
        ArrayList<int[]> ballsColors = this.balls.getColors();
        // On parcours toutes les balles , on les colore et on dessine ! 
        for (int i=0 ; i< ballsList.size() ; i++){
            Point ball = ballsList.get(i);
            int centerX = ball.x;
            int centerY = ball.y;
            int radius = 5; // Rayon du cercle
            Color ballColor = new Color(ballsColors.get(i)[0],ballsColors.get(i)[1],ballsColors.get(i)[2]);
            gui.addGraphicalElement(new Oval(centerX, centerY, ballColor,ballColor, 2*radius, 2*radius));
        }
    }

    @Override
    public void restart() {
        // Réinitialisation de l'état des balles
        balls.reInit();

        // Effacer l'écran
        gui.reset();
        gui.addGraphicalElement(new Rectangle( (1850/2), (930/2), Color.RED, Color.BLACK, 1848,928));

// Dessiner les balles sous forme de cercles
        ArrayList<Point> ballsList = this.balls.getBalls();
        ArrayList<int[]> ballsColors = this.balls.getColors();

        for (int i=0 ; i< ballsList.size() ; i++){
            Point ball = ballsList.get(i);
            int centerX = ball.x;
            int centerY = ball.y;
            int radius = 5; // Rayon du cercle
            Color ballColor = new Color(ballsColors.get(i)[0],ballsColors.get(i)[1],ballsColors.get(i)[2]);
            gui.addGraphicalElement(new Oval(centerX, centerY, ballColor,ballColor, 2*radius, 2*radius));
            
        }
    }
}



