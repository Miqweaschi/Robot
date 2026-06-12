package Robot;
import Gioco.MainGame;



public class AntColonyStrategy implements RobotState {
    private final int passo; // 1 per Pursuit/Seek, 2 per Flee

    public AntColonyStrategy(int passo) {
        this.passo = passo;
    }

    @Override
    public void move(Robot robot, int[][] mazeGrid) {
        int currentX = robot.getPosizione().x;
        int currentY = robot.getPosizione().y;

        double maxOdore = -1.0;
        int bestX = currentX;
        int bestY = currentY;

        int[][] directions = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0},          {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        for (int[] dir : directions) {
            int checkX = currentX + (dir[0] * passo);
            int checkY = currentY + (dir[1] * passo);

            // Chiediamo alla mappa dei feromoni
            double odore = MainGame.mappaFeromoni.getValore(checkX, checkY);

            // Controllo ostacoli e confini
            boolean mossaValida = true;
            if (odore == -1.0) {
                mossaValida = false; // Fuori mappa
            } else if (mazeGrid[checkY][checkX] == 1) {
                mossaValida = false; // Muro
            }

            if (mossaValida && odore > maxOdore) {
                maxOdore = odore;
                bestX = checkX;
                bestY = checkY;
            }
        }

        if (maxOdore >= 0 && (bestX != currentX || bestY != currentY)) {
            robot.setPosizione(bestX, bestY);
        } else if (passo == 2) {
            // Se FLEE a passo 2 fallisce, prova a fare un passo singolo di ripiego
            new AntColonyStrategy(1).move(robot, mazeGrid);
        }
    }
}