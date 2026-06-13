package Gioco;

import javax.swing.JFrame;
import javax.swing.Timer;
import Robot.Robot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    /**
     * 1 = Muro
     * 2 = Robot
     * 3 = Uscita
     * 0 = Vuoto
     */
    
    public static MappaFeromoni mappaFeromoni;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Robot");  /// Creazione finestra JFrame con titolo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mappaFeromoni = new MappaFeromoni(Mappa);

        Robot Pino = new Robot("Pino", 1, 8); /// Creazione del robot con nome e posizione iniziale

        Mappa[8][1] = 2;  ///  Posizionamento sulla mappa della casella relativa al robot

        Pannello Pannello = new Pannello(Mappa);
        frame.add(Pannello);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer gameTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int oldX = Pino.getPosizione().x;
                int oldY = Pino.getPosizione().y;
                Mappa[oldY][oldX] = 0;

                Pino.muoviti(Mappa);

                int newX = Pino.getPosizione().x;
                int newY = Pino.getPosizione().y;


                if (Math.random() <= 0.20) { // 20% di probabilità
                    int randRow = (int) (Math.random() * Mappa.length);
                    int randCol = (int) (Math.random() * Mappa[0].length);

                    if (Mappa[randRow][randCol] == 0) {
                        // La Factory decide quale oggetto concreto istanziare
                        OggettoMappa nuovoOggetto = OggettoFactory.creaOggettoCasuale();
                        Mappa[randRow][randCol] = nuovoOggetto.getCodiceGriglia();
                        System.out.println("Oggetto " + nuovoOggetto.getColore() + " apparso in: " + randRow + "," + randCol);
                    }
                }
                Mappa[newY][newX] = 2;

                Pannello.updatePannello(Mappa);
            }
        });

        gameTimer.start();
    }
}
