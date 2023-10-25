import gui.Simulable;

import java.awt.Color;

import gui.GUISimulator;
import java.awt.Point;
import gui.Rectangle;


public class CellsSimulator implements Simulable {
    private CellsGlobal cells;  // Référence vers l'objet Cells
    private GUISimulator gui;  // Référence vers l'interface graphique
    private int screen_width;
    private int screen_height;
    private int nbr_rows;
    private int nbr_columns;
    private int modelType;

    public CellsSimulator(GUISimulator gui, int screen_width, int screen_height,int nbr_rows,int nbr_columns,int nbrStates, int threshold, int modelType) {
        this.cells = new CellsGlobal(nbr_rows, nbr_columns, screen_width, screen_height,nbrStates,threshold,modelType);
        this.gui = gui;
        this.screen_height = screen_height;
        this.screen_width = screen_width;
        this.nbr_rows = nbr_rows;  // Mettez le nombre de lignes correct (30 dans ce cas)
        this.nbr_columns = nbr_columns;  // Mettez le nombre de colonnes correct (20 dans ce cas)
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
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screen_width / nbr_columns, screen_height / nbr_rows));
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
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screen_width / nbr_columns, screen_height / nbr_rows));
            }

        }
    }
}
