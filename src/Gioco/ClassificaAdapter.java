package Gioco;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * CLASSE ADAPTER (Pattern Adapter).
 * Implementa l'interfaccia Target richiesta dal Client e referenzia
 * internamente l'oggetto Adaptee per tradurre le operazioni.
 */
public class ClassificaAdapter implements ClassificaTarget {

    // Composizione: manteniamo un riferimento all'oggetto da adattare
    private final ScrittoreFile vecchioScrittore;

    public ClassificaAdapter() {
        this.vecchioScrittore = new ScrittoreFile();
    }

    @Override
    public void salvaPunteggio(String nomeRobot, int numeroPassi) {
        // Traduce la richiesta del gioco nel formato stringa accettato dall'Adaptee
        String rigaFormattata = nomeRobot + "," + numeroPassi;
        vecchioScrittore.aggiungiRigaTesto(rigaFormattata);
        System.out.println("[ADAPTER] Dati convertiti e salvati su file.");
    }

    @Override
    public void visualizzaClassifica() {
        // Recupera le righe grezze dall'Adaptee e applica la logica di ordinamento del gioco
        List<String> righeGrezze = vecchioScrittore.leggiTutteLeRighe();
        List<String[]> recordPunteggi = new ArrayList<>();

        for (String riga : righeGrezze) {
            String[] parti = riga.split(",");
            if (parti.length == 2) {
                recordPunteggi.add(parti);
            }
        }

        // Ordina in base al minor numero di passi (Classifica dei migliori)
        recordPunteggi.sort(Comparator.comparingInt(record -> Integer.parseInt(record[1]))
        );

        System.out.println("\n===== CLASSIFICA RISULTATI MIGLIORI =====");
        int pos = 1;
        for (String[] record : recordPunteggi) {
            System.out.println(pos + ". ROBOT: " + record[0] + " | Passi: " + record[1]);
            pos++;
            if (pos > 10) break; // Mostra solo i primi 10 migliori
        }
        System.out.println("=========================================\n");
    }
}