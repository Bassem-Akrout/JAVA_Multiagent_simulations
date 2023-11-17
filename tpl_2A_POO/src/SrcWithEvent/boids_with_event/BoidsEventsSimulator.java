import EventPackage.EventManager;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BoidsEventsSimulator  implements Simulable {
    private Boids boids; // Référence vers l'objet Boids
    private GUISimulator gui; // Référence vers l'interface graphique
    private double screenWidth;
    private double screenHeight;
    private EventManager eventManager; 
    private int nbrTypes; // Nombre de groupement de boids 
    private Color[] colorList; // Couleurs des groupements !

    public BoidsEventsSimulator(GUISimulator gui, int numBoids, double screenWidth, double screenHeight,int nbrTypes) {
        // Constructeur pour un simulateur de Boids avec un gestionnaire d'événements
        
        this.boids = new Boids(numBoids, screenWidth, screenHeight,nbrTypes);
        this.gui = gui;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.eventManager= new EventManager();
        this.nbrTypes=nbrTypes;
        colorList =new Color[nbrTypes];
        Random random= new Random();

        for (int i=0; i<nbrTypes;i++){
            colorList[i]=new Color(random.nextInt(10,256),random.nextInt(10,256),random.nextInt(10,256));        
        }
        initializeGraphics();
        scheduleSimulationEvents(); 



    }

    //Initialisation de l'interface graphique 
    private void initializeGraphics() {
        // Ajouter les éléments graphiques initiaux, par exemple les boids
        ArrayList<Boid> currentBoids = boids.getBoids();
        for (Boid boid : currentBoids) {
            Point2D.Double position = boid.getPosition();
            int k =boid.getMass();
            gui.addGraphicalElement(new Oval((int) position.x, (int) position.y,colorList[k-1], colorList[k-1], 10 * boid.getMass(), 10 * boid.getMass()));

        }
        // Ajoutez d'autres éléments graphiques si nécessaire
    }
    // Schedule simulation events (e.g., advancing the simulation by one step)
    private void scheduleSimulationEvents() {
        // Schedule events to advance the simulation at regular intervals
        int numSteps = 10000; // Number of simulation steps
        for (int i = 1; i <= numSteps; i++) {
            int eventDate = i; // Each step corresponds to an event at time i
            
            //eventManager.addEvent(new BoidsEvent(eventDate,boids,gui,screenWidth,screenHeight));
            
            //add only the events relating to the correct boidGroups
            for (int boidGroup = 1; boidGroup <= nbrTypes; boidGroup++) {
                if (eventDate%boidGroup==0){
                    eventManager.addEvent(new BoidsEvent(eventDate,boids,gui,screenWidth,screenHeight,boidGroup,colorList));
                }
            }
        }
        
    }

    // Passage au prochain événement 
    @Override
    public void next() {

        eventManager.next();

    }

    // Réinitialise le gestionnaire
    @Override
    public void restart() {
        // Réinitialisation de la simulation
        boids.reInit();
        eventManager.restart(); // Reset the EventManager

        // Effacer l'écran
        gui.reset();

        // Réinitialiser les éléments graphiques
        initializeGraphics();
        scheduleSimulationEvents(); 

    }
}
