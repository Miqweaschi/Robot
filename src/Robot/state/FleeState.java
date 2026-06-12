package Robot.state;

import Robot.AntColonyStrategy;
import Robot.Robot;
import Robot.RobotState;

public class FleeState implements RobotState {
    private final RobotState strategy = new AntColonyStrategy(2); // Passo doppio

    @Override
    public void move(Robot robot, int[][] mazeGrid) {
        strategy.move(robot, mazeGrid);
    }
}