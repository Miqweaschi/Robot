package Gioco;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.Timer;
import Robot.Robot;

import java.awt.GridLayout;
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

    private static ClassificaTarget gestioneClassifica;

    public static void main(String[] args) {
        /// Istanziamo l'Adapter per gestire la persistenza dei punteggi
        gestioneClassifica = new ClassificaAdapter();

        /// Mostra la classifica all'inizio di ogni partita
        gestioneClassifica.visualizzaClassifica();

        JTextField nomeField = new JTextField("Pino"); /// Valore di default
        String[] difficoltaOpzioni = {"Difficile (10% Spawn)", "Media (25% Spawn)", "Facile (50% Spawn)"};
        JComboBox<String> difficoltaBox = new JComboBox<>(difficoltaOpzioni);
        difficoltaBox.setSelectedIndex(1); /// Difficoltà di Default su "Media"


        ///Pannello dove possiamo scegliere la difficoltà è il nome del robot
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Nome del Robot:"));
        panel.add(nomeField);
        panel.add(new JLabel("Difficoltà Gioco:"));
        panel.add(difficoltaBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Configurazione Iniziale",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        /// Se l'utente chiude la finestra o preme "Cancel", interrompiamo il gioco
        if (result != JOptionPane.OK_OPTION) {
            System.out.println("Gioco annullato dall'utente.");
            System.exit(0);
        }

        /// Recuperiamo i dati inseriti dall'utente
        String nomeScelto = nomeField.getText().trim().isEmpty() ? "RobotAnonimo" : nomeField.getText().trim();
        int selezioneDifficolta = difficoltaBox.getSelectedIndex();

        /// Definiamo la probabilità di spawn in base alla difficoltà scelta
        final double probabilitaSpawn;
        if (selezioneDifficolta == 0) {
            probabilitaSpawn = 0.10;
        } else if (selezioneDifficolta == 1) {
            probabilitaSpawn = 0.25;
        } else {
            probabilitaSpawn = 0.50;
        }

        /// Inizializzazione della finestra grafica principale
        JFrame frame = new JFrame("Simulazione Robot: " + nomeScelto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mappaFeromoni = new MappaFeromoni(Mappa);

        /// Creazione del robot con il nome inserito dall'utente
        Robot Pino = new Robot(nomeScelto, 1, 8);

        Mappa[8][1] = 2; /// Posizionamento sulla mappa

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

                if (Mappa[newY][newX] == 3) {
                    System.out.println("VITTORIA! Uscita raggiunta.");
                    ((Timer)e.getSource()).stop(); /// Ferma il timer di gioco

                    /// Salviamo il record corrente tramite l'interfaccia Target dell'Adapter
                    gestioneClassifica.salvaPunteggio(Pino.getNome(), Pino.getPassiTotali());

                    /// Mostra la classifica aggiornata alla fine della partita
                    gestioneClassifica.visualizzaClassifica();
                    return;
                }


                if (Math.random() <= probabilitaSpawn) { /// 20% di probabilità
                    int randRow = (int) (Math.random() * Mappa.length);
                    int randCol = (int) (Math.random() * Mappa[0].length);

                    if (Mappa[randRow][randCol] == 0) {
                        /// La Factory decide quale oggetto concreto istanziare
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
