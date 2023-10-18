import gui . GUISimulator ;
import java . awt . Color ;
import gui.Rectangle;

public class TestBallsSimulator {
public static void main ( String [] args ) {
    GUISimulator gui = new GUISimulator (1850 , 930 , Color . BLACK );
    BallsSimulator ballsSimulator = new BallsSimulator (gui) ;
    ballsSimulator.restart();
    gui.setSimulable (ballsSimulator) ;
}
}