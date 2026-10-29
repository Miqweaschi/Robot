package Robot.state;

import Robot.AntColonyStrategy;
import Robot.Robot;
import Robot.RobotState;

public class SeekState implements RobotState {
    private final RobotState strategy = new AntColonyStrategy(1);

    @Override
    public void move(Robot robot, int[][] mazeGrid) {
        strategy.move(robot, mazeGrid);
    }
}