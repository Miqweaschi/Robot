package Gioco;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE ADAPTEE (Pattern Adapter).
 * Rappresenta la classe con l'interfaccia incompatibile che dobbiamo
 * riutilizzare senza modificarne il codice sorgente.
 */
public class ScrittoreFile {
    private final String pathFile = "classifica_robot.txt";

    public void aggiungiRigaTesto(String testo) {
        try (FileWriter fw = new FileWriter(pathFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(testo);
        } catch (IOException e) {
            System.err.println("Errore di I/O nel vecchio scrittore: " + e.getMessage());
        }
    }

    public List<String> leggiTutteLeRighe() {
        List<String> righe = new ArrayList<>();
        File file = new File(pathFile);
        if (!file.exists()) return righe;

        try (FileReader fr = new FileReader(pathFile);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    righe.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore di lettura nel vecchio scrittore: " + e.getMessage());
        }
        return righe;
    }
}