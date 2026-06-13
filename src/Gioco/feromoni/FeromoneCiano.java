package Gioco.feromoni;
import Gioco.OggettoMappa;
import Robot.RobotState;
import Robot.state.PursuitState;

public class FeromoneCiano extends OggettoMappa {
    public FeromoneCiano() { super(6, "CIANO"); }
    @Override
    public RobotState getStateTrigger() { return new PursuitState(); }
}