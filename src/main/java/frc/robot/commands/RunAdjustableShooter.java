package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class RunAdjustableShooter extends Command {

    private final Shooter shooter;
    private final double speed;
    
     public RunAdjustableShooter(Shooter shooter, double speed) {
        this.shooter = shooter;
        addRequirements(shooter);
        this.speed = speed;
     }

     // What should happen when the command begins?
     public void execute() {
        shooter.runAdjustableShooter(speed);
     }

       // What should happen when the command ends?
      public void end(boolean interrupted) {
        shooter.stopShooter();
    }

     // Continues running until interrupted or ended
     public boolean isFinished() {
         return false;
     }
}