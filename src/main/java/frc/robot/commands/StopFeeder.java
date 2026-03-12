package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;

public class StopFeeder extends Command {
    private final Feeder feeder;

    public StopFeeder(Feeder feeder) {
        this.feeder = feeder;
        addRequirements(feeder);
    }

    public void initialize() {
        feeder.stopFeeder();
    }

    public void end(boolean interrupted) {
        feeder.stopFeeder();
    }

    public boolean isFinished() {
        return false;
    }
    
}
