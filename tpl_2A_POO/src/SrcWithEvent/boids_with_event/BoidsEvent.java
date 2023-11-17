import EventPackage.Event;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BoidsEvent extends Event{
    private Boids boids; // Référence vers l'objet Boids
    private GUISimulator gui; // Référence vers l'interface graphique
    private double screenWidth;
    private double screenHeight; 
    private int boidsGroup;
    private Color[] colorList;

    public BoidsEvent (int date , Boids boids,GUISimulator gui, double screenWidth,double screenHeight,int boidsGroup,Color[] colorList ) {
        // Constructeur de BoidsEvent qui hérite de Event 
        super( date ) ;
        this.boids=boids;
        this.gui=gui;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        this.boidsGroup = boidsGroup;
        this.colorList=colorList;
        
        }

    // Fonction d'exécution d'un événement ! 
    @Override
    public void execute() {
        // Logique pour la prochaine étape de simulation
        // boids.nextLogic(screenWidth, screenHeight,(int)this.getDate());
        ArrayList<Boid> eaten = boids.nextLogic(screenWidth, screenHeight, boidsGroup);
        // Effacer l'écran
        gui.reset();
    
        // Mettre à jour les éléments graphiques pour les boids actuels
        ArrayList<Boid> currentBoids = boids.getBoids();
        for (Boid boid : currentBoids) {
            Point2D.Double position = boid.getPosition();
            int k = boid.getMass();
            gui.addGraphicalElement(new Oval((int) position.x, (int) position.y, colorList[k-1], colorList[k-1], 10 * boid.getMass(), 10 * boid.getMass()));
        }
    
        // Ajouter des éléments graphiques pour représenter le "sang" où les boids ont été mangés
        for (Boid eatenBoid : eaten) {
            Point2D.Double eatenPosition = eatenBoid.getPosition();
            int eatenK = eatenBoid.getMass();
            // Utilisez une couleur ou une forme différente pour représenter le "sang"
            gui.addGraphicalElement(new Oval((int) eatenPosition.x, (int) eatenPosition.y, Color.RED, Color.RED, 50 * eatenBoid.getMass(), 50 * eatenBoid.getMass()));
        }
        
    }
    
}   
