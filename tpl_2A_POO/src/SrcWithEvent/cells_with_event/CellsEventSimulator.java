import EventPackage.EventManager;
import gui.Simulable;

import java.awt.Color;

import gui.GUISimulator;
import java.awt.Point;
import gui.Rectangle;

/* Cette classe modélise un simulateur de gestionnaire d'évenements
 * pour tous les types de jeux ( modeltype correspond au jeu que 
 * tu veux )
 */

public class CellsEventSimulator implements Simulable {
    private Cells cells;  // Référence vers l'objet Cells
    private GUISimulator gui;  // Référence vers l'interface graphique
    private int screenWidth;
    private int screenHeight;
    private int nbrRows;
    private int nbrColumns;
    private EventManager eventManager; 


    public CellsEventSimulator(GUISimulator gui, int screenWidth, int screenHeight,int nbrRows,int nbrColumns,int nbrStates, int threshold, int modelType) {
        if(modelType==0){
            this.cells = new CellsAuto(nbrRows, nbrColumns, screenWidth, screenHeight);
        }
        else if(modelType==1){
            this.cells = new CellsConway(nbrRows, nbrColumns, screenWidth, screenHeight,nbrStates);
        }
        else if(modelType==2){
            this.cells = new CellsImmigrate(nbrRows, nbrColumns, screenWidth, screenHeight,nbrStates,threshold);
        }
        else{
            throw new IllegalArgumentException("Veuillez choisir un modèle valable entre 0 et 2 ");
        }
        this.eventManager=new EventManager();
        this.gui = gui;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.nbrRows = nbrRows;  // Mettez le nombre de lignes correct (30 dans ce cas)
        this.nbrColumns = nbrColumns;  // Mettez le nombre de colonnes correct (20 dans ce cas)
        scheduleSimulationEvents();
    }

    private void scheduleSimulationEvents() {
        // Schedule events to advance the simulation at regular intervals
        int numSteps = 10000; // Number of simulation steps
        for (int i = 1; i <= numSteps; i++) {
            int eventDate = i; // Each step corresponds to an event at time i            
            eventManager.addEvent(new CellsEvent(eventDate,gui,cells,screenWidth,screenHeight,nbrRows,nbrColumns));
                
            }
    }
    
    @Override
    public void next(){
        eventManager.next();
    }
    @Override
    public void restart() {
        // Réinitialisation de l'état des balles
        cells.reInit();
        eventManager.restart(); // Reset the EventManager

        // Effacer l'écran
        gui.reset();
        // init
        Color[][] colors=cells.getColors();
        Point[][] cellss=cells.getCells();
        for (int i=0;i<colors.length;i++){
            for(int j=0;j<colors[0].length;j++){
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screenWidth / nbrColumns, screenHeight / nbrRows));
            }
        }
        scheduleSimulationEvents(); 

    }
}
