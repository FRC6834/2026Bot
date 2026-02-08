package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class RunShooter extends Command {

    private final Shooter shooter;
    
     public RunShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
     }

     // What should happen when the command begins?
     public void initialize() {
        shooter.runShooter();
     }

     // Continues running until interrupted or ended
     public boolean isFinished() {
         return true;
     }
}