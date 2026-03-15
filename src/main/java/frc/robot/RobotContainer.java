// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ReverseFeeder;
import frc.robot.commands.ReverseIntake;
import frc.robot.commands.RunFeeder;
import frc.robot.commands.StopFeeder;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunAdjustableShooter;
import frc.robot.commands.StopShooter;
import frc.robot.commands.AlignToHub;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.auto.AutoBuilder;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */


public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Shooter m_shooter = new Shooter();
  private final Feeder m_feeder = new Feeder();
  private final Intake m_intake = new Intake();
  private final Climber m_climber = new Climber();

  //Driver controller
  private final CommandXboxController controller = new CommandXboxController(OIConstants.kDriverControllerPort);

  private final SendableChooser<Command> autoChooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Register commands by name for Path Planner
    NamedCommands.registerCommand(
        "StartShooter",
        new InstantCommand(() -> m_shooter.runAdjustableShooter(0.5), m_shooter)
    );

    NamedCommands.registerCommand(
        "StopShooter",
        new InstantCommand(() -> m_shooter.stopShooter(), m_shooter)
    );

        NamedCommands.registerCommand(
        "StartFeeder",
        new InstantCommand(() -> m_feeder.runFeeder(), m_feeder)
    );

    NamedCommands.registerCommand(
        "StopFeeder",
        new InstantCommand(() -> m_feeder.stopFeeder(), m_feeder)
    );

    //Build Auto Chooser
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
  
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(controller.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(controller.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(controller.getRightX(), OIConstants.kDriveDeadband),
                true),
            m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {

    //X Formation - set wheels in x formation to resist being pushed while left stick is pressed in
    controller.leftStick().whileTrue(m_robotDrive.setXCommand());

    //Zero Heading - reset the robot's heading to zero when the start button is pressed in
    controller.start().onTrue(m_robotDrive.zeroHeadingCommand());

    
    // Shooter - flywhel runs when the Y button is pressed, and stops when pressed again
    controller.y() //12 ft shot
        .toggleOnTrue(new RunAdjustableShooter(m_shooter, 0.50));

    controller.x() //8 ft shot
        .toggleOnTrue(new RunAdjustableShooter(m_shooter, 0.38));

    //Climber Up 
    controller.a().whileTrue(new ClimberUp(m_climber));

    //Climber Down
    controller.b().whileTrue(new ClimberDown(m_climber));
    

    //Feeder + Intake - runs when the RB button is held, and stops when released
    //Running the intake with the feeder will hopefully help prevent jamming
    controller.rightBumper().whileTrue(new RunFeeder(m_feeder).alongWith(new RunIntake(m_intake)));

    //Reverese Feeder - runs when the LB button is held, and stops when released
    controller.leftBumper().whileTrue(new ReverseFeeder(m_feeder));

    //Intake - runs when the RT is held, and stops when released
    controller.rightTrigger(OIConstants.kTriggerButtonThreshold).whileTrue(new RunIntake(m_intake));  

    //Reverse Intake - runs when the LT is held, and stops when released
    controller.leftTrigger(OIConstants.kTriggerButtonThreshold).whileTrue(new ReverseIntake(m_intake));

    // Align to hub - press right-stick button to run alignment (rotation-only)
    controller.rightStick().whileTrue(new AlignToHub(m_robotDrive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}