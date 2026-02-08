package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class StopShooter extends Command {

    private final Shooter shooter;
    
     public StopShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
     }

     // What should happen when the command begins?
     public void initialize() {
        shooter.stopShooter();
     }

     // Continues running until interrupted or ended
     public boolean isFinished() {
         return true;
     }
}