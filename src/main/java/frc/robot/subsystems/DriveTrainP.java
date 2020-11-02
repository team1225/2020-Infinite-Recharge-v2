/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.RobotMap;


public class DriveTrainPID extends PIDSubsystem {
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;
  private CANSparkMax leftFollower;
  private CANSparkMax rightFollower;
  private DifferentialDrive drive;
  private Double maxSpeed; 
  /**
   * Creates a new DriveTrainPID.
   */
  public DriveTrainPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));

    leftMotor = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
    rightMotor = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
    leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
    rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);

    //Set Following
    leftFollower.follow(leftMotor);
    rightFollower.follow(rightMotor);

    // Set ramp rate from constant (Seconds it takes to go from 0 to full speed)
    leftMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    rightMotor.setOpenLoopRampRate(DriveConstants.kRampRate);
    leftFollower.setOpenLoopRampRate(DriveConstants.kRampRate);
    rightFollower.setOpenLoopRampRate(DriveConstants.kRampRate);

    // Get encoders
    // m_leftEncoder = m_leftMotor.getEncoder();
    // m_rightEncoder = m_rightMotor.getEncoder();
    drive = new DifferentialDrive(leftMotor, rightMotor);

    leftMotor.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    leftFollower.setIdleMode(IdleMode.kBrake);
    rightFollower.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Max Speed", maxSpeed);
  }
}
