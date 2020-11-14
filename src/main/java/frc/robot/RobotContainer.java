/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static java.util.Map.entry;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.commands.ArmLoading;
import frc.robot.commands.ArmPickup;
import frc.robot.commands.AutoAdvanced;
import frc.robot.commands.AutoAdvancedFast;
import frc.robot.commands.AutoSimple;
import frc.robot.commands.AutoSweep;
import frc.robot.commands.ColorControl;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveToTarget;
import frc.robot.commands.HopperIn;
import frc.robot.commands.HopperOut;
import frc.robot.commands.Shoot;
import frc.robot.commands.RotationControl;
import frc.robot.commands.TurnToTarget;
import frc.robot.commands.WenchDown;
import frc.robot.commands.WenchUp;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.LimeLight2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Wench;

import java.util.Map;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Wench wench = new Wench();
  private final Drivetrain drivetrain = new Drivetrain();
  private final ColorWheel colorwheel = new ColorWheel();
  private final Hopper hopper = new Hopper();
  private final Arm arm = new Arm();
  
  private final Joystick joystick = new Joystick(0);
  private final Joystick joystick2 = new Joystick(1);
  private final SendableChooser<CommandSelector> sc = new SendableChooser<CommandSelector>();
  
  private enum CommandSelector {
    Simple, Advanced, AdvancedFast, Sweep
  }

  private CommandSelector select() {
    return sc.getSelected();
  }

  private final Command exampleSelectCommand =
      new SelectCommand(
          // Maps selector values to commands
          Map.ofEntries(
              entry(CommandSelector.Simple, new AutoSimple(drivetrain, arm)),
              entry(CommandSelector.Advanced, new AutoAdvanced(drivetrain, arm, hopper)),
              entry(CommandSelector.AdvancedFast, new AutoAdvancedFast(drivetrain, arm, hopper)),
              entry(CommandSelector.Sweep, new AutoSweep(drivetrain, arm, hopper))
          ),
          this::select
      );
      
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(() -> -joystick.getY(),
        () -> joystick.getZ(), drivetrain));
    // Configure the button bindings
    configureButtonBindings();

    sc.setDefaultOption("Advanced", CommandSelector.Advanced);
    sc.addOption("Simple", CommandSelector.Simple);
    sc.addOption("Fast Advanced", CommandSelector.AdvancedFast);
    
    SmartDashboard.putData("Which Auto?", sc);

    SmartDashboard.putData("Turn to target", new TurnToTarget(new Drivetrain(), new LimeLight2()));
    SmartDashboard.putData("Drive to target", 
        new DriveToTarget(VisionConstants.desiredDistanceToTarget, new Drivetrain(), new LimeLight2()));
    SmartDashboard.putData("Turn and drive to target", new SequentialCommandGroup(
      new TurnToTarget(new Drivetrain(), new LimeLight2()), 
      new DriveToTarget(VisionConstants.desiredDistanceToTarget, 
          new Drivetrain(), new LimeLight2())));
    SmartDashboard.putData("Color control", new ColorControl(colorwheel));
    SmartDashboard.putData("Rotation control", new RotationControl(colorwheel));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(joystick2, RobotMap.BUTTON_A).whileHeld(new WenchDown(wench)); //B
    new JoystickButton(joystick2, RobotMap.BUTTON_B).whileHeld(new WenchUp(wench)); //Y
    new JoystickButton(joystick2, RobotMap.BUTTON_X).whileHeld(new ArmLoading(arm)); //X
    new JoystickButton(joystick2, RobotMap.BUTTON_Y).whenPressed(new ArmPickup(arm)); // A
    new JoystickButton(joystick, RobotMap.LEFT_BUMPER).whenHeld(new HopperIn(hopper));
    new JoystickButton(joystick, RobotMap.RIGHT_BUMPER).whenHeld(new HopperOut(hopper));
    // new POVButton(joystick, 270).whenPressed(new ColorControl(colorwheel));
    // new POVButton(joystick, 90).whenPressed(new RotationControl(colorwheel));
    new POVButton(joystick, 0).whileHeld(new Shoot(0.9, new Shooter()));
    new POVButton(joystick, 90).whileHeld(new DriveToTarget(VisionConstants.desiredDistanceToTarget,
        new Drivetrain(), new LimeLight2()));
    new POVButton(joystick, 180).whileHeld(new SequentialCommandGroup(
        new TurnToTarget(new Drivetrain(), new LimeLight2()), 
        new DriveToTarget(VisionConstants.desiredDistanceToTarget, 
            new Drivetrain(), new LimeLight2()))); 
    new POVButton(joystick, 270).whileHeld(new TurnToTarget(new Drivetrain(), new LimeLight2()));
    new JoystickButton(joystick, RobotMap.LEFT_STICK_BUTTON)
        .whenPressed(() -> drivetrain.setMaxOutput(DriveConstants.kMaxLowSpeed))
        .whenReleased(() -> drivetrain.setMaxOutput(DriveConstants.kMaxHighSpeed));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return exampleSelectCommand;
  }
}
