/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;
import static java.util.Map.entry;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;

import frc.robot.subsystems.Hopper;
import frc.robot.commands.HopperOut;
import frc.robot.commands.RotationControl;
import frc.robot.commands.HopperIn;
import frc.robot.subsystems.Wench;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.WenchUp;
import frc.robot.commands.WenchDown;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.ArmLoading;
import frc.robot.commands.ArmPickup;
import frc.robot.commands.ColorControl;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import java.util.List;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Wench m_wench = new Wench();
  private final ColorWheel m_colorwheel = new ColorWheel();
  private final Hopper m_hopper = new Hopper();
  private final Arm m_arm = new Arm();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  
  private final Joystick m_joystick = new Joystick(0);
  private final Joystick m_joystick2 = new Joystick(1);
  private SendableChooser<CommandSelector> sc = new SendableChooser<CommandSelector>();
  
  private enum CommandSelector {
    Simple, Advanced, AdvancedFast, Sweep
  }

  private CommandSelector select() {
    return sc.getSelected();
  }

  private final Command m_exampleSelectCommand =
      new SelectCommand(
          // Maps selector values to commands
          Map.ofEntries(
              // entry(CommandSelector.Simple, new AutoSimple(m_drivetrain, m_arm)),
              // entry(CommandSelector.Advanced, new AutoAdvanced(m_drivetrain, m_arm, m_hopper)),
              // entry(CommandSelector.AdvancedFast, new AutoAdvancedFast(m_drivetrain, m_arm, m_hopper)),
              // entry(CommandSelector.Sweep, new AutoSweep(m_drivetrain, m_arm, m_hopper))
          ),
          this::select
      );
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    // configureButtonBindings();
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(() -> m_robotDrive
            .arcadeDrive(m_joystick.getY(),
            m_joystick.getZ()), m_robotDrive));

    // m_drivetrain.setDefaultCommand(new Drive(() -> m_joystick.getY(Hand.kLeft),
    //     () -> m_joystick.getX(Hand.kRight), m_drivetrain));
    // Configure the button bindings

    sc.setDefaultOption("Advanced", CommandSelector.Advanced);
    sc.addOption("Simple", CommandSelector.Simple);
    sc.addOption("Fast Advanced", CommandSelector.AdvancedFast);
    
    SmartDashboard.putData("Which Auto?", sc);
  }

  /**
   * Use this method to define your bu
   * '
   
    new JoystickButton(m_joystick, RobotMap.LEFT_BUMPER).whenHeld(new HopperIn(m_hopper));
    new JoystickButton(m_joystick, RobotMap.RIGHT_BUMPER).whenHeld(new HopperOut(m_hopper));
    new POVButton(m_joystick, 270).whenPressed(new ColorControl(m_colorwheel));
    new POVButton(m_joystick, 90).whenPressed(new RotationControl(m_colorwheel));
    // new JoystickButton(m_joystick, RobotMap.LEFT_STICK_BUTTON)
    //     .whenPressed(() -> m_drivetrain.setMaxOutput(DriveConstants.kMaxLowSpeed))
    //     .whenReleased(() -> m_drivetrain.setMaxOutput(DriveConstants.kMaxHighSpeed));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond,
                             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_robotDrive::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                   DriveConstants.kvVoltSecondsPerMeter,
                                   DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        m_robotDrive::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_robotDrive::tankDriveVolts,
        m_robotDrive
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  }
}
