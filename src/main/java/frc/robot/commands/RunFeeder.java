package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;

public class RunFeeder extends Command {
    private final Feeder feeder;

    public RunFeeder(Feeder feeder) {
        this.feeder = feeder;
        addRequirements(feeder);
    }

    public void execute() {
        feeder.runFeeder();
    }

    public void end(boolean interrupted) {
        feeder.stopFeeder();
    }

    public boolean isFinished() {
        return false;
    }
    
}
