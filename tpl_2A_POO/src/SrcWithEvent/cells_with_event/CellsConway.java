import java.awt.Color;
import java.util.Random;

/* Cette classe hérite de la classe Abstraite Cells 
 et est implémentée pour le jeu de Conway
 */

public class CellsConway extends Cells {
    private int nbrStates;  /// nbr of colors
    
    public CellsConway(int nbrRows,int nbrColumns, int screenWidth, 
    int screenHeight, int nbrStates ){
        super(nbrRows, nbrColumns, screenWidth, screenHeight);
        this.nbrStates=nbrStates;
        Color[] colorList =new Color[nbrStates];
        
        Random random= new Random();
        for(int i=0; i<nbrStates;i++){
            int j=i*(256/nbrStates);
            colorList[i]=new Color(j,j,j);
        }

        //Cells init
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                int idxColor=random.nextInt(nbrStates);
                colors[row][column]=colorList[idxColor];
                initColors[row][column]=colorList[idxColor];
            }
        }
    }

    protected  int countNeighbors(int row,int column){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        int nextStateNeighbors=0;
        int[][] relativeMoves={
            {-1, -1},{-1, 0},{-1, 1},
            { 0, -1},        { 0, 1},
            { 1, -1},{ 1, 0},{ 1, 1}
        };

        int idxColor=colors[row][column].getBlue()/(256/nbrStates); 
        int nextColorIdx=(idxColor+1)%nbrStates;     
        for (int[] move:relativeMoves){
            int newRow = (row+ move[0]+ nbrRows)%nbrRows;                   // gestion de la bordure circulaire
            int newColumn = (column+ move[1]+ nbrColumns)%nbrColumns;       // gestion de la bordure circulaire
            if((colors[newRow][newColumn].getRed())==(nextColorIdx*(256/nbrStates))){
                nextStateNeighbors++;
            }
        }
        return nextStateNeighbors;
    }
    public void nextLogic(){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        Color[][] newColors=new Color[nbrRows][nbrColumns];
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                Color actuaColor=colors[row][column];
                int neighbors=countNeighbors(row, column);
                int idxColor=actuaColor.getBlue()/(256/nbrStates); //model 2
                int nextColorIdx=(idxColor+1)%nbrStates;                    //model 2
                if (neighbors==3){
                    newColors[row][column]= new Color(nextColorIdx*(256/nbrStates),nextColorIdx*(256/nbrStates),nextColorIdx*(256/nbrStates));
                }
                else{
                    newColors[row][column]=actuaColor;
                }
            }
        }
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                colors[row][column]=newColors[row][column];
            }
        } 
    }
}
