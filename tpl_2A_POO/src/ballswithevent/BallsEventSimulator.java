import gui.GUISimulator;
import java.awt.Color;
import java.awt.Point;
import gui.Simulable;
import gui.Oval;
import gui.Rectangle;

import java.util.ArrayList;


public class BallsEventSimulator implements Simulable {
    private Balls balls;  // Référence vers l'objet Balls
    private GUISimulator gui;  // Référence vers l'interface graphique
    private EventManager eventManager;
    public BallsEventSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.gui = gui;
        this.eventManager= new EventManager();
        scheduleSimulationEvents(); 



    }
    private void scheduleSimulationEvents() {
        // Schedule events to advance the simulation at regular intervals
        int numSteps = 10000; // Number of simulation steps
        for (int i = 1; i <= numSteps; i++) {
            int eventDate = i; // Each step corresponds to an event at time i            

            eventManager.addEvent(new ballsEvent(eventDate,gui,balls));
                
            }
    }
    

    @Override
    public void next() {
        eventManager.next();
    }

    @Override
    public void restart() {
        // Réinitialisation de l'état des balles
        balls.reInit();
        eventManager.restart(); // Reset the EventManager

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
        scheduleSimulationEvents(); 

    }
}



