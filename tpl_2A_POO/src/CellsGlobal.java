import java.awt.Point;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;


public class CellsGlobal {
    private Point[][]cells;
    private Color[][]initColors;
    private Color[][]colors;
    private ArrayList<int[]> unoccupiedCells;
    private ArrayList<int[]> initialUnoccupiedCells;
    private int nbrStates;  /// nbr of colors
    private int threshold; /// k NBR DE VOISINS POUR SEGREG

    public CellsGlobal(int nbrRows,int nbrColumns, int screenWidth, 
    int screenHeight , int nbrStates,int threshold,int modelType){
        //attributes init
        cells= new Point[nbrRows][nbrColumns];
        initColors=new Color[nbrRows][nbrColumns];
        colors=new  Color[nbrRows][nbrColumns];
        unoccupiedCells=new ArrayList<>();
        initialUnoccupiedCells=new ArrayList<>();
        this.nbrStates=nbrStates;
        this.threshold=threshold;

        Random random= new Random();
        int cellWidth= screenWidth/nbrColumns;
        int cellHeight= screenHeight/nbrRows;

        Color[] colorList;
        if(modelType==0){
            nbrStates=2; //useful for line 64
            colorList =new Color[]{Color.BLACK,Color.WHITE};
        }
        else if(modelType==1){
            colorList =new Color[nbrStates];
            for(int i=0; i<nbrStates;i++){
                int j=i*(256/nbrStates);
                colorList[i]=new Color(j,j,j);
            }
            
        }
        else if (modelType==2){
            colorList =new Color[nbrStates+1];
            colorList[0]= Color.BLACK;
            for (int i=1; i<nbrStates+1;i++){
                colorList[i]=new Color(random.nextInt(10,256),random.nextInt(10,256),random.nextInt(10,256));        
            }
            nbrStates+=1; //useful for line 64
        }
        else{
            throw new IllegalArgumentException("Veuillez choisir un modèle valable entre 0 et 2 ");/////////// mettre dans testSimuCell
        }

        //Cells init
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                int centerX=column*cellWidth +cellWidth/2;
                int centerY=row*cellHeight +cellHeight/2;

                cells[row][column]=new Point(centerX, centerY);
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

    public Point[][] getCells(){
        return this.cells;
    }
    public Color[][] getColors(){
        return this.colors;
    }
    public void reInit(){
        int nbrRows= cells.length;
        int nbrColumns= cells[0].length;
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                colors[row][column] = initColors[row][column]; 
            }
        }
        unoccupiedCells.clear();
        for (int[] elt:initialUnoccupiedCells){
            unoccupiedCells.add(elt);
        }
    }

    private int countNeighbors(int row,int column,int modelType){
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        int sameNeighbors=0;
        int aliveNeighbors=0;
        int nextStateNeighbors=0;
        int[][] relativeMoves={
            {-1, -1},{-1, 0},{-1, 1},
            { 0, -1},        { 0, 1},
            { 1, -1},{ 1, 0},{ 1, 1}
        };
        int actualColor=colors[row][column].getRGB();

        int idxColor=colors[row][column].getBlue()/(256/nbrStates); //model 2
        int nextColorIdx=(idxColor+1)%nbrStates;                    //model 2

        for (int[] move:relativeMoves){
            int newRow = (row+ move[0]+ nbrRows)%nbrRows;                   // gestion de la bordure circulaire
            int newColumn = (column+ move[1]+ nbrColumns)%nbrColumns;       // gestion de la bordure circulaire
        
            if(colors[newRow][newColumn].getRGB() == actualColor){
                sameNeighbors++;
            }
            if(colors[newRow][newColumn] == Color.WHITE){
                aliveNeighbors++;
            }
            if((colors[newRow][newColumn].getRed())==(nextColorIdx*(256/nbrStates))){
                nextStateNeighbors++;
            };           
        }
        if (modelType==0){
            return aliveNeighbors;
        }
        else if (modelType==1){
            return nextStateNeighbors;
        }
        return sameNeighbors;
    }

    public void nextLogic(int modelType){
        //System.out.println("just started next logic");
        int nbrRows = cells.length;
        int nbrColumns= cells[0].length;
        Color[][] newColors=new Color[nbrRows][nbrColumns];
        if (modelType==0){
            for(int row=0; row<nbrRows;row++){
                for (int column=0; column<nbrColumns;column++){
                    Color actuaColor=colors[row][column];
                    int neighbors=countNeighbors(row, column, modelType);

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
        }
        else if (modelType==1){
            for(int row=0; row<nbrRows;row++){
                for (int column=0; column<nbrColumns;column++){
                    Color actuaColor=colors[row][column];
                    int neighbors=countNeighbors(row, column, modelType);
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

        }   
        else if (modelType==2){
            Random random= new Random();
            for(int row=0; row<nbrRows;row++){
                for (int column=0; column<nbrColumns;column++){
                    Color actualColor=colors[row][column];
                    if (actualColor.getRed()==0){
                        continue;
                    }
                    int neighbors=countNeighbors(row, column, modelType);
                    if (neighbors<=threshold){
                        newColors[row][column]=Color.BLACK;

                        int idxRandomUnoccupiedCell=random.nextInt(unoccupiedCells.size());
                        int[] randomUnoccupiedCell=unoccupiedCells.get(idxRandomUnoccupiedCell);

                        unoccupiedCells.set(idxRandomUnoccupiedCell, new int[] {row,column});
                        newColors[randomUnoccupiedCell[0]][randomUnoccupiedCell[1]]=actualColor;
                    }
                    else{
                        newColors[row][column]=actualColor;
                    }
                }
            }

            for (int[] element:unoccupiedCells){
                newColors[element[0]][element[1]]=Color.BLACK;
            }
        }      
        for(int row=0; row<nbrRows;row++){
            for (int column=0; column<nbrColumns;column++){
                colors[row][column]=newColors[row][column];
            }
        }        
        //System.out.println("just ended next logic");

    }   
}
