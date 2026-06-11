package Robot;


import java.awt.*;

public class Robot {
    private String nome;
    private Point posizione;


    public Robot(String nome,int StartX, int StartY){
        this.nome = nome;
        this.posizione = new Point(StartX,StartY);
    }
}
