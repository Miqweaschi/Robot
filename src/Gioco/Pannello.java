package Gioco;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;


/// Questa classe si occupa solo di disegnare il labirinto
public class Pannello extends JPanel {
    private final int CASELLA = 40;  /// Dimensione di ogni singola casella in pixel

    private int[][] GrigliaLab;

    public Pannello(int[][] GrigliaLab){
        this.GrigliaLab = GrigliaLab;

        /// Impostiamo delle dimensioni preferire per far in modo che Il pannello
        /// calcoli esattamente i pixel necessari basandosi sulle caselle
        this.setPreferredSize(new Dimension(GrigliaLab[0].length * CASELLA, GrigliaLab.length * CASELLA));
    }


    /// Ridefinizione del metodo della classe java.awt.Graphics
    /// utilizzato per disegnare i componenti sul Pannello.
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); /// Pulisce lo schermo

        for(int row = 0; row < GrigliaLab.length; row++ ){
            for(int col = 0; col < GrigliaLab[0].length; col++){

            int ValoreCasella = GrigliaLab[row][col];
            int x = col * CASELLA;
            int y = row * CASELLA;

            switch(ValoreCasella){
                case 1: g.setColor(Color.BLACK); break;  /// Muro con colore Nero

                case 2: g.setColor(Color.BLUE); break;  /// Robot con colore Blu

                case 3: g.setColor(Color.GREEN); break;
                default: g.setColor(Color.WHITE);
                }

                g.fillRect(x,y,CASELLA,CASELLA); /// Serve per disegnare la casella piena

                g.setColor(Color.GRAY);
                g.drawRect(x,y,CASELLA,CASELLA);  /// Serve per disegnare il contorno della casella
            }
        }
    }

    /// Metodo per aggiornare la griglia quando il robot si muove
    public void updatePannello(int[][] NuovaGriglia){
        this.GrigliaLab = NuovaGriglia;
        repaint(); /// Questo dice a java di richiamare paintComponent
    }

}
