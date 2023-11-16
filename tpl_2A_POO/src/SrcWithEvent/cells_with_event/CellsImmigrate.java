import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Point;


public class CellsImmigrate extends Cells{

    private ArrayList<int[]> unoccupiedCells;
    private ArrayList<int[]> initialUnoccupiedCells;
    private int threshold; /// k NBR DE VOISINS POUR SEGREG

    public CellsImmigrate(int nbrRows,int nbrColumns, int screenWidth, 
    int screenHeight , int nbrStates,int threshold){
        super(nbrRows, nbrColumns, screenWidth, screenHeight);
        Color[] colorList ;
        Random random= new Random();

        this.threshold=threshold;
        unoccupiedCells=new ArrayList<>();
        initialUnoccupiedCells=new ArrayList<>();

        colorList =new Color[nbrStates+1];
        colorList[0]= Color.BLACK;
        for (int i=1; i<nbrStates+1;i++){
            colorList[i]=new Color(random.nextInt(10,256),random.nextInt(10,256),random.nextInt(10,256));        
        }
        nbrStates+=1;
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                int idxColor=random.nextInt(nbrStates);
                if(idxColor==0){
                    unoccupiedCells.add(new int[]{row,column});
                    initialUnoccupiedCells.add(new int[]{row,column});
                }
                colors[row][column]=colorList[idxColor];
                initColors[row][column]=colorList[idxColor];
            }
        }
    }


    @Override
    public void reInit(){
        super.reInit();
        unoccupiedCells.clear();
        for (int[] elt:initialUnoccupiedCells){
            unoccupiedCells.add(elt);
        }
    }

    protected int countNeighbors(int row,int column){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        int sameNeighbors=0;

        int[][] relativeMoves={
            {-1, -1},{-1, 0},{-1, 1},
            { 0, -1},        { 0, 1},
            { 1, -1},{ 1, 0},{ 1, 1}
        };
        int actualColor=colors[row][column].getRGB();

        for (int[] move:relativeMoves){
            int newRow = (row+ move[0]+ nbrRows)%nbrRows;                   // gestion de la bordure circulaire
            int newColumn = (column+ move[1]+ nbrColumns)%nbrColumns;       // gestion de la bordure circulaire
            if(colors[newRow][newColumn].getRGB() == actualColor){
                sameNeighbors++;
            }
        }
        return sameNeighbors;
    }

    public void nextLogic(){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        Color[][] newColors=new Color[nbrRows][nbrColumns];

        Random random = new Random();
        List<Point> shuffledCells = new ArrayList<>();

        // Ajoutez toutes les cellules à la liste des cellules mélangées
        for (int row = 0; row < nbrRows; row++) {
            for (int column = 0; column < nbrColumns; column++) {
                Color actualColor = colors[row][column];
                if (actualColor.getRed() != 0) {
                    shuffledCells.add(new Point(row, column));
                }
            }
        }

        Collections.shuffle(shuffledCells, random); // Mélangez la liste
        // Parcourez les cellules mélangées
        for (Point cell : shuffledCells) {
            int row = cell.x;
            int column = cell.y;

            Color actualColor = colors[row][column];
            int neighbors = countNeighbors(row, column);

            if (neighbors < threshold) {
                newColors[row][column] = Color.BLACK;

                int idxRandomUnoccupiedCell = random.nextInt(unoccupiedCells.size());
                int[] randomUnoccupiedCell = unoccupiedCells.get(idxRandomUnoccupiedCell);

                unoccupiedCells.set(idxRandomUnoccupiedCell, new int[]{row, column});
                newColors[randomUnoccupiedCell[0]][randomUnoccupiedCell[1]] = actualColor;
            } else {
                newColors[row][column] = actualColor;
            }
        }
        for (int[] element:unoccupiedCells){
            newColors[element[0]][element[1]]=Color.BLACK;
        }
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                colors[row][column]=newColors[row][column];
            }
        }       
        
    }
}
