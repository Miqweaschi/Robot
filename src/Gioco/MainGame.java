package Gioco;

import javax.swing.*;
import Robot.Robot;

public class MainGame {

    static int[][] Mappa = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 3, 1},
            {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    /*
     * 1 = Muro
     * 2 = Robot
     * 3 = Uscita
     * 0 = Vuoto
     */


    public static void main(String[] args) {

        JFrame frame = new JFrame("ROBOT.Robot 3");  /// Creazione finestra jFrame con titolo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Robot Pino = new Robot("Pino", 1, 8); /// Creazione del robot con nome e posizione iniziale

        Mappa[8][1] = 2;  ///  Posizionamento sulla mappa della casella relativa al robot */

        Pannello Pannello = new Pannello(Mappa);
        frame.add(Pannello);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}