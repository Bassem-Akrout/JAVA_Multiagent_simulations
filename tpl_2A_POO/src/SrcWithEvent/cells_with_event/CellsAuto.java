import java.util.Random;
import java.awt.Color;

/* Cette classe hérite de la classe Abstraite Cells 
 et est implémentée pour le jeu d'automates Cellulaires
 */

public class CellsAuto extends Cells{    

    public CellsAuto(int nbrRows,int nbrColumns, int screenWidth, 
    int screenHeight ){
        
        // Constructeur pour la classe CellsAuto
        super(nbrRows, nbrColumns, screenWidth, screenHeight);
        Color[] colorList=new Color[]{Color.BLACK,Color.WHITE};
        Random random= new Random();
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                int idxColor=random.nextInt(2);
                colors[row][column]=colorList[idxColor];
                initColors[row][column]=colorList[idxColor];
            }
        }  
    }

    
    protected  int countNeighbors(int row,int column){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        int aliveNeighbors=0;
        int[][] relativeMoves={
            {-1, -1},{-1, 0},{-1, 1},
            { 0, -1},        { 0, 1},
            { 1, -1},{ 1, 0},{ 1, 1}
        };
        for (int[] move:relativeMoves){
            int newRow = (row+ move[0]+ nbrRows)%nbrRows;                   // gestion de la bordure circulaire
            int newColumn = (column+ move[1]+ nbrColumns)%nbrColumns;       // gestion de la bordure circulaire
            if(colors[newRow][newColumn] == Color.WHITE){
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }
    public void nextLogic(){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        Color[][] newColors=new Color[nbrRows][nbrColumns];
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                Color actuaColor=colors[row][column];
                int neighbors=countNeighbors(row, column);

                if (actuaColor == Color.BLACK){
                    if (neighbors ==3 ){
                        newColors[row][column]= Color.WHITE;
                    }
                    else{
                        newColors[row][column]= Color.BLACK;
                    }
                }
                else if (actuaColor == Color.WHITE){
                    if (neighbors ==3||neighbors ==2 ){
                        newColors[row][column]= Color.WHITE;
                    }
                    else{
                        newColors[row][column]= Color.BLACK;
                    }
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
