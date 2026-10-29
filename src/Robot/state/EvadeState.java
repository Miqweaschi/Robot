package Robot.state;


import Robot.RandomStrategy;
import Robot.Robot;
import Robot.RobotState;

public class EvadeState implements RobotState {
    private final RobotState strategy = new RandomStrategy();

    @Override
    public void move(Robot robot, int[][] mazeGrid) {
        strategy.move(robot, mazeGrid);
    }
}