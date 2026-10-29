package Robot;

import Robot.state.EvadeState;
import Robot.state.FleeState;
import Robot.state.PursuitState;
import Robot.state.SeekState;
import java.awt.Point;


/// Attributi (Lo stato)
public class Robot {
    private String nome;
    private Point posizione;
    private RobotState statoCorrente;
    int passi;
    int passiTotali;


    /// Costruttore (l'atto di nascita)
    public Robot(String nome,int StartX, int StartY){
        this.nome = nome;
        this.posizione = new Point(StartX,StartY);
        this.statoCorrente = new EvadeState();
        this.passiTotali = 0;

    }


    /// Metodi (Il comportamento)
    public void muoviti(int[][] mazeGrid) {
        /// Prima di muoversi, controlla se c'è qualcosa vicino che fa cambiare stato
        controllaFeromoni(mazeGrid);
        this.passiTotali = passiTotali +1;
        this.passi = this.passi + 1;
        /// Poi esegue la mossa dello stato attuale
        statoCorrente.move(this, mazeGrid);
    }

    /// Lo scopo di questa Funzione è analizzare le 8 caselle adiacenti alla
    /// posizione attuale del robot per rilevare la presenza di oggetti colorati e,
    /// in base al colore trovato, aggiornare lo stato comportamentale del robot
    private void controllaFeromoni(int[][] griglia) {
        int x = posizione.x;
        int y = posizione.y;

        /// Guardiamo nelle 8 celle adiacenti
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                /// Saltiamo la cella centrale (dove si trova il robot stesso)
                if (i == 0 && j == 0) continue;

                int checkX = x + j;
                int checkY = y + i;

                /// Controllo dei confini per evitare IndexOutOfBoundsException
                if (checkY >= 0 && checkY < griglia.length && checkX >= 0 && checkX < griglia[0].length) {

                    int oggettoTrovato = griglia[checkY][checkX];

                    if (oggettoTrovato == 4) { // ROSSO
                        if (!(statoCorrente instanceof PursuitState)) {
                            this.passi = 0;
                            this.setState(new PursuitState());
                            System.out.println("Feromone ROSSO, Vado in PURSUIT");
                            return;
                        }
                    } else if (oggettoTrovato == 5) { // GIALLO
                        if (!(statoCorrente instanceof FleeState)) {
                            this.passi = 0;
                            this.setState(new FleeState());
                            System.out.println("Feromone GIALLO, Vado in FLEE");
                            return;
                        }
                    } else if (oggettoTrovato == 6) { // CIANO
                        if (!(statoCorrente instanceof SeekState)) {
                            this.passi = 0;
                            this.setState(new SeekState());
                            System.out.println("Feromone CIANO, Vado in SEEK");
                            return;
                        }
                    }
                }
            }
        }
        /// Se non siamo già in EvadeState, resettiamo il robot allo stato di default
        if (!(statoCorrente instanceof EvadeState) && this.passi >= 4) {
            this.passi = 0;
            this.setState(new EvadeState());
            System.out.println("Nessun oggetto vicino. Il robot torna in EVADE (esplorazione casuale).");
        }
    }

    private void setState(RobotState nuovoStato) {
        this.statoCorrente = nuovoStato;
    }

    public Point getPosizione() {
        return posizione;
    }

    public void setPosizione(int x, int y) {
        this.posizione.x = x;
        this.posizione.y = y;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPassiTotali() {
        return  this.passiTotali;
    }
}
