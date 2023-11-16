import gui.Simulable;

import java.awt.Color;

import gui.GUISimulator;
import java.awt.Point;
import gui.Rectangle;


public class CellsSimulator implements Simulable {
    private CellsGlobal cells;  // Référence vers l'objet Cells
    private GUISimulator gui;  // Référence vers l'interface graphique
    private int screenWidth;
    private int screenHeight;
    private int nbrRows;
    private int nbrColumns;
    private int modelType;

    public CellsSimulator(GUISimulator gui, int screenWidth, int screenHeight,int nbrRows,int nbrColumns,int nbrStates, int threshold, int modelType) {
        this.cells = new CellsGlobal(nbrRows, nbrColumns, screenWidth, screenHeight,nbrStates,threshold,modelType);
        this.gui = gui;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.nbrRows = nbrRows;  // Mettez le nombre de lignes correct (30 dans ce cas)
        this.nbrColumns = nbrColumns;  // Mettez le nombre de colonnes correct (20 dans ce cas)
        this.modelType=modelType;
    }
    
    @Override
    public void next(){
        //System.out.println("just clicked next");
        //Color[][] unchanged_colors=cells.get_colors(); on peut ici ne addGraphElement que ceux qui changent
        cells.nextLogic(modelType);
        gui.reset();//à ne pas enlever: plus il y a des GraphicalElement plus ça lag
        Color[][] colors=cells.getColors();
        Point[][] cellss=cells.getCells();
        //add grid
        for (int i=0;i<colors.length;i++){
            for(int j=0;j<colors[0].length;j++){
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screenWidth / nbrColumns, screenHeight / nbrRows));
            }
        }
        //System.out.println("just finished next");
    }
    @Override
    public void restart() {
        // Réinitialisation de l'état des balles
        cells.reInit();

        // Effacer l'écran
        gui.reset();
        //add GRID !!!!!!!!!
        Color[][] colors=cells.getColors();
        Point[][] cellss=cells.getCells();
        for (int i=0;i<colors.length;i++){
            for(int j=0;j<colors[0].length;j++){
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screenWidth / nbrColumns, screenHeight / nbrRows));
            }

        }
    }
}
