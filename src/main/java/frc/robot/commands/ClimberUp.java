package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimberUp extends Command {
    private final Climber climber;

    public ClimberUp(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    public void execute() {
        climber.climberUp();
    }

    public void end(boolean interrupted) {
        climber.stopClimber();
    }

    public boolean isFinished() {
        return false;
    }
    
}
