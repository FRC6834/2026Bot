package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
    // This method sets the priority of apriltag sensing for the Limelight based on the alliance color. This method is needed since the apriltags change on the opposite side of the field, so we want it to be dynamic.
    // Called in robotPeriodic() in Robot.java to keep the alliance and priority updated at all times.
    public static void HubPriority() {
        Optional<Alliance> team = DriverStation.getAlliance();
        int selectedApriltag = 0;
        if (team.isPresent()) {
            if (team.get() == Alliance.Red) {
                selectedApriltag = 10;
                LimelightHelpers.setPriorityTagID("limelight", selectedApriltag);
            } else if (team.get() == Alliance.Blue) {
                selectedApriltag = 26;
                LimelightHelpers.setPriorityTagID("limelight", selectedApriltag);
            }
            SmartDashboard.putString("Limelight Apriltag Prioritizor", "Team " + team.get() + " detected! The limelight will prioritize apriltag #" + selectedApriltag);
        } else {
            // Handle the case where the alliance information is not available
            SmartDashboard.putString("Limelight Apriltag Prioritizor", "No team found! The limelight won't know which apriltag to prioritize.");
        }
        
    }
    // This method calculates the distance from the robot to the AprilTag using a tangent equation - From Limelight Documentation https://docs.limelightvision.io/docs/docs-limelight/apis/limelight-lib#8-getting-detailed-results-from-networktables-rawtargets
    public static double getDistance() {
        // Set pipeline index for limelight
        LimelightHelpers.setPipelineIndex("limelight", 1);

        // Get the correct table for the limelight - This is where the apriltag dimensions that we need are stored
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

        // Get the Y value (vertical axis) from the limelight
        NetworkTableEntry ty = table.getEntry("ty");

        // Get the vertical offset angle from the limelight
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);

         // how many degrees back is your limelight rotated from perfectly vertical?
        double limelightMountAngleDegrees = 0.0; 

        // distance from the center of the Limelight lens to the floor
        double limelightLensHeightInches = 1.347; 

        // distance from the target to the floor
        double goalHeightInches = 0.0; 

        // Calculate the total angle to the goal in radians
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);

        // Calculate distance using tangent
        double distanceFromApriltag = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

        return distanceFromApriltag;
    }
    // This method calculates the rotation in radians needed to center the limelight to the X of the apriltag.
    public static double getRotation() {
        double cameraLensHorizontalOffset = LimelightHelpers.getTX("limelight") / getDistance();
        double realHorizontalOffset = Math.atan(cameraLensHorizontalOffset / getDistance());
        double rotationError = Math.atan(realHorizontalOffset / getDistance());
        return rotationError;
        }
    
        // All this method does is get the values from the limelight and publish them to the SmartDashboard.
        // Its called in Robot.java in the robotPeriodic() method to publish the values on the SmartDashboard and later be placed on elastic.
    public static void DisplayData() {
        // Get the apriltag-specific values from limelight
        double X = LimelightHelpers.getTX("limelight");
        double Y = LimelightHelpers.getTY("limelight");
        boolean TV = LimelightHelpers.getTV("limelight");
        double area = LimelightHelpers.getTA("limelight");
    
        // Get the array of values for stats from the limelight via networktables 
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double[] llstats = table.getEntry("hw").getDoubleArray(new double[6]);
        
        // initialize variables for each number in the array 
        double cpu_temp_celsius = llstats[0];
        double cpu_usage = llstats[1];
        double ram_usage = llstats[2];
        double fps = llstats[3];
    
        // Display the values on the SmartDashboard
        SmartDashboard.putNumber("Limelight X", X);
        SmartDashboard.putNumber("Limelight Y", Y);
        SmartDashboard.putNumber("Limelight Area", area);
        SmartDashboard.putNumber("Limelight Distance (IN)", getDistance());
        SmartDashboard.putNumber("Limelight Rotation (RAD)", getRotation());
        SmartDashboard.putBoolean("Apriltag Detected?", TV);
        

        // Display general status values
        SmartDashboard.putNumber("Limelight CPU Temp (C)", cpu_temp_celsius);
        SmartDashboard.putNumber("Limelight CPU Usage (%)", cpu_usage);
        SmartDashboard.putNumber("Limelight RAM Usage (%)", ram_usage);
        SmartDashboard.putNumber("Limelight FPS", fps);
        
                                                         
    }
}