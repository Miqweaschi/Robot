package Gioco;
import Gioco.OggettoMappa;
import Gioco.feromoni.*;

/**
 * Il 'Creator' che incapsula la logica di istanziazione degli oggetti a run-time.
 */
public class OggettoFactory {

    /**
     * Factory Method che restituisce l'oggetto corretto in base al codice.
     */
    public static OggettoMappa getOggettoByCodice(int codice) {
        switch (codice) {
            case 4: return new FeromoneRosso();
            case 5: return new FeromoneGiallo();
            case 6: return new FeromoneCiano();
            default: return null;
        }
    }

    /**
     * Metodo helper per generare un oggetto casuale da inserire nella griglia.
     */
    public static OggettoMappa creaOggettoCasuale() {
        int codiceRandom = 4 + (int)(Math.random() * 3);
        return getOggettoByCodice(codiceRandom);
    }
}