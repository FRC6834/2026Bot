package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;

public class ReverseFeeder extends Command {
    private final Feeder feeder;

    public ReverseFeeder(Feeder feeder) {
        this.feeder = feeder;
        addRequirements(feeder);
    }

    public void execute() {
        feeder.reverseFeeder();
    }

    public void end(boolean interrupted) {
        feeder.stopFeeder();
    }

    public boolean isFinished() {
        return false;
    }
    
}
