package Gioco;

import Robot.RobotState;

/**
 * Rappresenta il 'Product' astratto nel Factory Method.
 * Definisce la struttura comune a tutti gli oggetti che compaiono nel labirinto.
 */
public abstract class OggettoMappa {
    private final int codiceGriglia;
    private final String colore;

    public OggettoMappa(int codiceGriglia, String colore) {
        this.codiceGriglia = codiceGriglia;
        this.colore = colore;
    }

    public int getCodiceGriglia() { return codiceGriglia; }
    public String getColore() { return colore; }

    public abstract RobotState getStateTrigger();
}