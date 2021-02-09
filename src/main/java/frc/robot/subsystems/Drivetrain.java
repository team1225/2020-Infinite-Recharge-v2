/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  // private CANEncoder m_leftEncoder;
  // private CANEncoder m_rightEncoder;
  private final CANSparkMax leftMotor;
  private final CANSparkMax rightMotor;
  private final CANSparkMax leftFollower;
  private final CANSparkMax rightFollower;
  private final DifferentialDrive drive;
  private Double maxSpeed; 

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    maxSpeed = 1.0;
    // Get motors
    leftMotor = new CANSparkMax(DriveConstants.kLeftMotorPorts[0], MotorType.kBrushless);
    rightMotor = new CANSparkMax(DriveConstants.kRightMotorPorts[0], MotorType.kBrushless);
    leftFollower = new CANSparkMax(DriveConstants.kLeftMotorPorts[1], MotorType.kBrushless);
    rightFollower = new CANSparkMax(DriveConstants.kRightMotorPorts[1], MotorType.kBrushless);

    //Set Following
    leftFollower.follow(leftMotor);
    rightFollower.follow(rightMotor);

    // Set ramp rate from constant (Seconds it takes to go from 0 to full speed)
    // leftMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    // rightMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    // leftFollower.setOpenLoopRampRate(DriveConstants.kRampRate);
    // rightFollower.setOpenLoopRampRate(DriveConstants.kRampRate);

    // Get encoders
    // m_leftEncoder = m_leftMotor.getEncoder();
    // m_rightEncoder = m_rightMotor.getEncoder();
    
    leftMotor.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    leftFollower.setIdleMode(IdleMode.kBrake);
    rightFollower.setIdleMode(IdleMode.kBrake);
    drive = new DifferentialDrive(leftMotor, rightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Max Speed", maxSpeed);
  }

  public void setMaxOutput(final double maxOutput) {
    drive.setMaxOutput(maxOutput);
    maxSpeed = maxOutput;
  }

  public void drive(final Double speed, final Double rotation) {
    drive.arcadeDrive(speed, rotation);
  }
  
  public void drive(final Joystick joystick) {
    drive.arcadeDrive(joystick.getY(), joystick.getRawAxis(RobotMap.X_RIGHT_STICK_X));
  }

  public void stop() {
    drive.arcadeDrive(0, 0);
  }
}
