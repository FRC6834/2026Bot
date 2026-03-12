package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    
    //Motor Controller
    SparkMax climber = new SparkMax(Constants.ClimberConstants.kClimber, MotorType.kBrushless);

    public Climber(){
        climber.configure(
            Configs.ClimberSubsystem.climberConfig, 
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    //Methods
    public void climberUp() {
        climber.set(Constants.ClimberConstants.kClimberUpSpeed);
    }

    public void climberDown() {
        climber.set(Constants.ClimberConstants.kClimberDownSpeed);
    }

    public void stopClimber() {
        climber.set(0);
    }
}