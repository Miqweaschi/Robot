package Robot;

import java.util.Random;

///  Questo qui è lo stato principale in cui il robot si trova di default, i suoi movimenti sono casuali.
public class RandomStrategy implements RobotState {
    private Random random = new Random();

    @Override
    public void move(Robot robot, int[][] griglia) {
        int xCorrente = robot.getPosizione().x;
        int yCorrente = robot.getPosizione().y;

        int[][] direzioni = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, {1, 0},
                {-1, 1}, {0, 1}, {1, 1}
        };

        boolean mosso = false;
        int tentativi = 0;

        while (!mosso && tentativi < 10) {

            int[] dir = direzioni[random.nextInt(direzioni.length)];

            int nextX = xCorrente + dir[0];
            int nextY = yCorrente + dir[1];

            /// Controlliamo se la mossa è valida
            if (controlloMovimento(nextX, nextY, griglia)) {
                /// Aggiorniamo la posizione del robot
                robot.setPosizione(nextX, nextY);
                mosso = true;

            }
            tentativi++;
        }

    }

    private boolean controlloMovimento(int x, int y, int[][] griglia) {
        int rows = griglia.length;
        int cols = griglia[0].length;

        /// Controllo bordi
        if (x < 0 || x >= cols || y < 0 || y >= rows) return false;

        /// Controllo muri
        if (griglia[y][x] == 1) return false;

        return true;
    }

}