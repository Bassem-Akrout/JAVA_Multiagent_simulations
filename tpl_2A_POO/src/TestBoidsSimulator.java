import gui.GUISimulator;
import java.awt.Color;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1850, 930, Color.BLACK);
        BoidsSimulator boidsSimulator = new BoidsSimulator(gui, 500, 1850, 930);
        boidsSimulator.restart();
        gui.setSimulable(boidsSimulator);
    }
}
