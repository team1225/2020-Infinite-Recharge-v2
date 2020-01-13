/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;

public class Drivetrain extends SubsystemBase {
  private CANEncoder m_leftEncoder;
  private CANEncoder m_rightEncoder;
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;
  private DifferentialDrive m_drive;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    m_leftMotor = new CANSparkMax(RobotMap.leftMotorCANId, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(RobotMap.rightMotorCANId, MotorType.kBrushless);
    m_leftMotor.setOpenLoopRampRate(RobotMap.rampRate);
    m_rightMotor.setOpenLoopRampRate(RobotMap.rampRate);
    m_leftEncoder = m_leftMotor.getEncoder();
    m_rightEncoder = m_rightMotor.getEncoder();
    m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
