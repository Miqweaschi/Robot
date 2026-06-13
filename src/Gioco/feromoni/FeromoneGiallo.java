package Gioco.feromoni;
import Gioco.OggettoMappa;
import Robot.RobotState;
import Robot.state.PursuitState;

public class FeromoneGiallo extends OggettoMappa {
    public FeromoneGiallo() { super(5, "GIALLO"); }
    @Override
    public RobotState getStateTrigger() { return new PursuitState(); }
}