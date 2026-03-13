package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    
    //Motor Controller
    private final SparkMax climber = new SparkMax(Constants.ClimberConstants.kClimber, MotorType.kBrushless);
    private final RelativeEncoder climberEncoder = climber.getEncoder();

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

    public void periodic() {
        SmartDashboard.putNumber("ClimberPosition", climberEncoder.getPosition());
    }
}