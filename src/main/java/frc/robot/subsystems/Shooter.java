/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax leadMotor = 
      new CANSparkMax(ShooterConstants.leadMotorId, MotorType.kBrushless);
  private final CANSparkMax followerMotor = 
      new CANSparkMax(ShooterConstants.follerMotorId, MotorType.kBrushless);
  private final CANEncoder leadEncoder;
  private final CANEncoder followerEncoder;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    followerMotor.follow(leadMotor);

    leadMotor.setIdleMode(IdleMode.kCoast);
    followerMotor.setIdleMode(IdleMode.kCoast);

    leadEncoder = leadMotor.getEncoder();
    followerEncoder = followerMotor.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Lead Encoder - Velocity", leadEncoder.getVelocity());
    SmartDashboard.putNumber("Follower Encoder - Velocity", followerEncoder.getVelocity());
  }

  public void spin(final double speed) {
    leadMotor.set(speed);
  }

  public void stop() {
    leadMotor.set(0.0);
  }
}
