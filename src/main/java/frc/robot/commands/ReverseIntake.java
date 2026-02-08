package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ReverseIntake extends Command {
    private final Intake intake;

    public ReverseIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void execute() {
        intake.reverseIntake();
    }

    public void end(boolean interrupted) {
        intake.stopIntake();
    }

    public boolean isFinished() {
        return false;
    }
}