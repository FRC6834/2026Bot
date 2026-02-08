package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    //Motor Controllers
    private final SparkFlex shooterLead = new SparkFlex(Constants.ShooterConstants.kShooterLead, MotorType.kBrushless); //Shoot 1
    private final SparkFlex shooterFollow = new SparkFlex(Constants.ShooterConstants.kShooterFollow, MotorType.kBrushless); //Shoot 2
    
     public Shooter() {
        //Configure Motors
        //See Configs class for motor configuration details
         shooterLead.configure(
            Configs.ShooterSubsystem.flywheelConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        //This motor is set to follow the lead motor in Configs class
        //This is a really important step to make sure that the follower motor spins correctly and in sync with the lead motor
        shooterFollow.configure(
            Configs.ShooterSubsystem.flywheelFollowerConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    //Shooter Methods
    //Speeds can be adjusted in the Constants class
    public void runShooter() {
        shooterLead.set(Constants.ShooterConstants.kShooterSpeed);
    }   
         
    public void stopShooter() {
        shooterLead.set(0);
    }
}