package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    private final SparkFlex feeder = new SparkFlex(Constants.FeederConstants.kFeeder, MotorType.kBrushless);

    public Feeder() {
        feeder.configure(
            Configs.FeederSubsystem.feederConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    //Feeder Methods
    public void runFeeder() {
        feeder.set(Constants.FeederConstants.kFeederSpeed);
    }

    public void reverseFeeder() {
        feeder.set(Constants.FeederConstants.kReverseFeederSpeed);
    }

    public void stopFeeder() {
        feeder.set(0);
    }
}