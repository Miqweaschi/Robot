package Gioco.feromoni;
import Gioco.OggettoMappa;
import Robot.RobotState;
import Robot.state.PursuitState;

public class FeromoneRosso extends OggettoMappa {
    public FeromoneRosso() { super(4, "ROSSO"); }
    @Override
    public RobotState getStateTrigger() { return new PursuitState(); }
}