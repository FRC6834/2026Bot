package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    //Motor Controllers
    private final SparkFlex shooterLead = new SparkFlex(Constants.ShooterConstants.kShooterLead, MotorType.kBrushless); //Shoot 1
    private final SparkFlex shooterFollow = new SparkFlex(Constants.ShooterConstants.kShooterFollow, MotorType.kBrushless); //Shoot 2
    private final RelativeEncoder shooterEncoder = shooterLead.getEncoder();
    
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
    public void runLongShooter() {
        shooterLead.set(Constants.ShooterConstants.kFastShooterSpeed);
    }   

    public void runShortShooter(){
        shooterLead.set(Constants.ShooterConstants.kSlowShooterSpeed);
    }

    public void runAdjustableShooter(double speed) {
        shooterLead.set(speed);
    }
         
    public void stopShooter() {
        shooterLead.set(0);
    }

    public boolean atTargetSpeed(double targetRPM) {
        double currentRPM = shooterEncoder.getVelocity();
        return Math.abs(currentRPM - targetRPM) < 100;  // tolerance
    }

    public void periodic() {
        SmartDashboard.putNumber("ShooterRPM", shooterEncoder.getVelocity());
        SmartDashboard.putBoolean("Long Shot", atTargetSpeed(3200)); //These need adjusted
        SmartDashboard.putBoolean("Short Shot", atTargetSpeed(2450));
    }
}