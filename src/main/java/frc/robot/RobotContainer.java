/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Hopper;
import frc.robot.commands.HopperOut;
import frc.robot.commands.HopperIn;
import frc.robot.subsystems.Wench;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.WenchUp;
import frc.robot.commands.WenchDown;
import frc.robot.commands.ArmLoading;
import frc.robot.commands.ArmPickup;
import frc.robot.commands.ColorControl;
// import frc.robot.Constants.UpDown;
// import frc.robot.commands.ChangeMaxSpeed;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Wench m_wench = new Wench();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final ColorWheel m_colorwheel = new ColorWheel();
  private final Hopper m_hopper = new Hopper();
  private final Arm m_arm = new Arm();
  
  private final Joystick m_joystick = new Joystick(0);
  
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(new Drive(() -> -m_joystick.getY(Hand.kLeft),
        () -> m_joystick.getX(Hand.kRight), m_drivetrain));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_joystick, RobotMap.BUTTON_A).whileHeld(new WenchUp(m_wench));
    new JoystickButton(m_joystick, RobotMap.BUTTON_B).whileHeld(new WenchDown(m_wench));
    new JoystickButton(m_joystick, RobotMap.BUTTON_X).whenPressed(new ArmLoading(m_arm));
    new JoystickButton(m_joystick, RobotMap.BUTTON_Y).whenPressed(new ArmPickup(m_arm));
    new JoystickButton(m_joystick, RobotMap.LEFT_BUMPER).whenHeld(new HopperIn(m_hopper));
    new JoystickButton(m_joystick, RobotMap.RIGHT_BUMPER).whenHeld(new HopperOut(m_hopper));
    new POVButton(m_joystick, 270).whenPressed(new ColorControl(m_colorwheel));
    new JoystickButton(m_joystick, RobotMap.LEFT_STICK_BUTTON)
        .whenPressed(() -> m_drivetrain.setMaxOutput(1.0))
        .whenReleased(() -> m_drivetrain.setMaxOutput(0.5));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
