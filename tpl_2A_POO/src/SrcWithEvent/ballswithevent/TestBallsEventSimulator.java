import gui . GUISimulator ;
import java . awt . Color ;
import gui.Rectangle;

public class TestBallsEventSimulator {
public static void main ( String [] args ) {
    //Fonction de test de BallsEventSimulator
    GUISimulator gui = new GUISimulator (1850 , 930 , Color . BLACK );
    BallsEventSimulator ballsSimulator = new BallsEventSimulator (gui) ;
    ballsSimulator.restart();
    gui.setSimulable (ballsSimulator) ;
}
}