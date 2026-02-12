package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
    

    public LimelightSubsystem() {
    }

    // All this method does is get the values from the limelight and display them on the SmartDashboard.
    // Its called in Robot.java in the robotInit() method to update the values on the SmartDashboard - essentially a placeholder until elastic is used correctly.
    public static void DisplayData() {
        // Get the values from limelight
        double x = LimelightHelpers.getTX("limelight");
        double y = LimelightHelpers.getTY("limelight");
        double area = LimelightHelpers.getTA("limelight");

        // Display the values on the SmartDashboard
        SmartDashboard.putNumber("Limelight X", x);
        SmartDashboard.putNumber("Limelight Y", y);
        SmartDashboard.putNumber("Limelight Area", area);
    }
}