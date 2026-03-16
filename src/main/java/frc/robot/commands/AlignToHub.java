// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.LimelightHelpers;

// This command centers the limelight lens to the center of an apriltag in relation to the robot. It does not move the bot into position, only rotates it.
public class AlignToHub extends Command {
  private final DriveSubsystem m_drive;
 

  // Proportional gain 
  private static final double kP = 1.5;
  // Stop when within ~1 degree
  private static final double kToleranceRad = Math.toRadians(1.0);
  // Limit the commanded rotation fraction to avoid spinning too fast 
  private static final double kMaxRotFraction = 0.6;

  private boolean m_atTarget = false;

  public AlignToHub(DriveSubsystem drive) {
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_atTarget = false;
  }

  @Override
  public void execute() {
    // getRotation returns the rotation error (radians) needed to center the limelight
    double rotationError = LimelightSubsystem.getRotation();

    // If we're within tolerance, stop and finish (Tolerence is within 1 degree of the apriltag's center)
    if (Math.abs(rotationError) <= kToleranceRad) {
      m_atTarget = true;
      m_drive.drive(0.0, 0.0, 0.0, false);
      return;
    }

    // convert rotationError (radians) into a normal rotation command
    double rotCommand = (kP * rotationError) / DriveConstants.kMaxAngularSpeed;
    rotCommand = MathUtil.clamp(rotCommand, -kMaxRotFraction, kMaxRotFraction);

    // Drive with zero translation and the rotation command, this should only rotate the robots directon, not move it.
    m_drive.drive(0.0, 0.0, rotCommand, false);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0.0, 0.0, 0.0, false);
  }

  @Override
  public boolean isFinished() {
    return m_atTarget;
  }
}
