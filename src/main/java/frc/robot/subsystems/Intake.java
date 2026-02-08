package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    //Motor Controller
    SparkMax intake = new SparkMax(Constants.IntakeConstants.kIntake, MotorType.kBrushless);

    public Intake(){
        intake.configure(
            Configs.IntakeSubsystem.intakeConfig, 
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    //Methods
    public void runIntake() {
        intake.set(Constants.IntakeConstants.kIntakeSpeed);
    }

    public void reverseIntake() {
        intake.set(Constants.IntakeConstants.kReverseIntakeSpeed);
    }

    public void stopIntake() {
        intake.set(0);
    }
}