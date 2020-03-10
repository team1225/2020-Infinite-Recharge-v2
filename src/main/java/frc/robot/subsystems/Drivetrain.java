/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

public class Drivetrain extends SubsystemBase {
  // private CANEncoder m_leftEncoder;
  // private CANEncoder m_rightEncoder;
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;
  private CANSparkMax m_leftFollower;
  private CANSparkMax m_rightFollower;
  private DifferentialDrive m_drive;
  private Double maxSpeed; 

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    maxSpeed = 1.0;
    // Get motors
    m_leftMotor = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
    m_leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
    m_rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);

    //Set Following
    m_leftFollower.follow(m_leftMotor);
    m_rightFollower.follow(m_rightMotor);

    // Set ramp rate from constant (Seconds it takes to go from 0 to full speed)
    m_leftMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_rightMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_leftFollower.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_rightFollower.setOpenLoopRampRate(DriveConstants.kRampRate);

    // Get encoders
    // m_leftEncoder = m_leftMotor.getEncoder();
    // m_rightEncoder = m_rightMotor.getEncoder();
    m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

    m_leftMotor.setIdleMode(IdleMode.kBrake);
    m_rightMotor.setIdleMode(IdleMode.kBrake);
    m_leftFollower.setIdleMode(IdleMode.kBrake);
    m_rightFollower.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Max Speed", maxSpeed);
  }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
    maxSpeed = maxOutput;
  }

  public void drive(Double speed, Double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }
  
  public void drive(Joystick joystick) {
    m_drive.arcadeDrive(joystick.getY(), joystick.getRawAxis(RobotMap.X_RIGHT_STICK_X));
  }
  public void Stop() {
    m_drive.arcadeDrive(0, 0);
  }
}
