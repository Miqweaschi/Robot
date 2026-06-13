package Gioco;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class MappaFeromoni {

    /// Questa matrice conterrà i valori del "profumo"
    /// Più alto è il numero, più siamo vicini all'uscita.
    private double[][] mappaOdori;

    public MappaFeromoni(int[][] levelData) {
        calcolaOdori(levelData);
    }

    private void calcolaOdori(int[][] levelData) {
        int rows = levelData.length;
        int cols = levelData[0].length;
        mappaOdori = new double[rows][cols];

        /// Coda per l'algoritmo di esplorazione (BFS)
        Queue<Point> coda = new LinkedList<>();

        ///  Troviamo l'uscita (codice 3) e facciamo partire l'odore da lì
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (levelData[y][x] == 3) {
                    mappaOdori[y][x] = 1000.0; // Valore massimo all'uscita
                    coda.add(new Point(x, y));
                } else {
                    mappaOdori[y][x] = 0.0; // Inizializziamo tutto a 0
                }
            }
        }

        /// Espandiamo il feromone nel labirinto
        int[][] direzioni = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        while (!coda.isEmpty()) {
            Point corrente = coda.poll();
            double valoreCorrente = mappaOdori[corrente.y][corrente.x];

            /// Controlliamo i vicini
            for (int[] dir : direzioni) {
                int nx = corrente.x + dir[0];
                int ny = corrente.y + dir[1];

                /// Se è dentro la mappa
                if (ny >= 0 && ny < rows && nx >= 0 && nx < cols) {
                    /// Se NON è un muro (1) e non ha ancora un odore assegnato
                    if (levelData[ny][nx] != 1 && mappaOdori[ny][nx] == 0.0) {

                        /// Il profumo diminuisce leggermente allontanandosi
                        mappaOdori[ny][nx] = valoreCorrente - 1.0;

                        /// Aggiungiamo alla coda per processare i suoi vicini dopo
                        coda.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    // Metodo per far "annusare" al robot il valore di una cella
    public double getValore(int x, int y) {
        if (y < 0 || y >= mappaOdori.length || x < 0 || x >= mappaOdori[0].length) {
            return -1.0; // Fuori mappa
        }
        return mappaOdori[y][x];
    }
}