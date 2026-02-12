package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
    // Values for limelight distance estimation - these are just placeholders and will need to be tuned for the actual robot and limelight setup.
    private static final double LIMELIGHT_LENS_HEIGHT_INCHES = 20.0; // Height of the Limelight lens from the ground
    private static final double TARGET_HEIGHT_INCHES = 60.0; // Height of the center of the AprilTag from the ground
    private static final double LIMELIGHT_MOUNT_ANGLE_DEGREES = 25.0; // Angle the Limelight is mounted at (from horizontal)

    public LimelightSubsystem() {
    }

    // This method calculates the distance from the robot to the AprilTag using a tangent equation.
     public static double getDistance() {
        double targetOffsetAngleVertical = NetworkTableInstance.getDefault().getTable("limelight4").getEntry("ty").getDouble(0.0);

        // Calculate the total angle to the goal in radians
        double angleToGoalDegrees = LIMELIGHT_MOUNT_ANGLE_DEGREES + targetOffsetAngleVertical;
        double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);

        // Calculate distance using tangent
        double distanceFromApriltag = (TARGET_HEIGHT_INCHES - LIMELIGHT_LENS_HEIGHT_INCHES) / Math.tan(angleToGoalRadians);

        return distanceFromApriltag;
    }

    // All this method does is get the values from the limelight and display them on the SmartDashboard.
    // Its called in Robot.java in the robotInit() method to update the values on the SmartDashboard - essentially a placeholder until elastic is used correctly.
    public static void DisplayData() {
        // Get the values from limelight
        double x = LimelightHelpers.getTX("limelight4");
        double y = LimelightHelpers.getTY("limelight4");
        double area = LimelightHelpers.getTA("limelight4");

        // Display the values on the SmartDashboard
        SmartDashboard.putNumber("Limelight X", x);
        SmartDashboard.putNumber("Limelight Y", y);
        SmartDashboard.putNumber("Limelight Area", area);
        SmartDashboard.putNumber("Limelight Distance" , getDistance());
                                                            //Finish later^
    }
}