import EventPackage.Event;

import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Point;
import java.awt.Color;

public class CellsEvent extends Event {
    private Cells cells;
    private GUISimulator gui;
    private int screenWidth;
    private int screenHeight; 
    private int nbrRows;
    private int nbrColumns;

    public CellsEvent (int date,GUISimulator gui ,Cells cells,int screenWidth,int screenHeight,int nbrRows,int nbrColumns) {
        super(date);
        this.cells=cells;
        this.gui=gui;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        this.nbrColumns=nbrColumns;
        this.nbrRows=nbrRows;
    }

    @Override
    public void execute(){
        cells.nextLogic();
        gui.reset();//à ne pas enlever: plus il y a des GraphicalElement plus ça lag
        Color[][] colors=cells.getColors();
        Point[][] cellss=cells.getCells();
        //add grid
        for (int i=0;i<colors.length;i++){
            for(int j=0;j<colors[0].length;j++){
                gui.addGraphicalElement(new Rectangle(cellss[i][j].x, cellss[i][j].y, Color.GREEN, colors[i][j], screenWidth / nbrColumns, screenHeight / nbrRows));
            }
        }
    }
}
