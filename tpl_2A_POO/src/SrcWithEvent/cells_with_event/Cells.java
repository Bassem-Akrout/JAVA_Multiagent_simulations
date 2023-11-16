import java.awt.Color;

import java.awt.Point;

public abstract class Cells {
    protected Point[][]cells;
    protected Color[][]initColors;
    protected Color[][]colors;
    public Cells(int nbrRows,int nbrColumns, int screenWidth,int screenHeight ){
        //attributes init
        cells= new Point[nbrRows][nbrColumns];
        initColors=new Color[nbrRows][nbrColumns];
        colors=new  Color[nbrRows][nbrColumns];
        int cellWidth= screenWidth/nbrColumns;
        int cellHeight= screenHeight/nbrRows;

        //Cells init
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                int centerX=column*cellWidth +cellWidth/2;
                int centerY=row*cellHeight +cellHeight/2;
                cells[row][column]=new Point(centerX, centerY);
            }
        }
    }
    public Point[][] getCells(){
        return this.cells;
    }
    public Color[][] getColors(){
        return colors;
    }
    public void reInit(){
        int nbrRows= cells.length;
        int nbrColumns= cells[0].length;
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                colors[row][column] = initColors[row][column]; 
            }
        }    
    }
    protected abstract int countNeighbors(int row,int column);
    public abstract void nextLogic();
}
