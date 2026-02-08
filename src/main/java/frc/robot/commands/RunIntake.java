package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class RunIntake extends Command {
    private final Intake intake;

    public RunIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void execute() {
        intake.runIntake();
    }

    public void end(boolean interrupted) {
        intake.stopIntake();
    }

    public boolean isFinished() {
        return false;
    }
}