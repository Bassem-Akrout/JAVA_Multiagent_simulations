import gui.GUISimulator;
import java.awt.Color;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1850, 930, Color.BLACK);

        BoidsEventsSimulator boidsSimulator = new BoidsEventsSimulator(gui, 250, 1850, 930,3);

        //boidsSimulator.restart();
        gui.setSimulable(boidsSimulator);
    }
}
