import gui . GUISimulator ;
import java . awt . Color ;

public class TestCellsSimulator {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (1850 , 930 , Color . BLACK );
        CellsSimulator cellsSimulator = new CellsSimulator(gui, 1850, 930,50,80,10,2,2) ;
        cellsSimulator.restart();
        gui.setSimulable (cellsSimulator) ;
    }
}