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
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax leadMotor = 
      new CANSparkMax(ShooterConstants.motorPorts[0], MotorType.kBrushless);
  private final CANSparkMax followerMotor = 
      new CANSparkMax(ShooterConstants.motorPorts[1], MotorType.kBrushless);
  private final CANEncoder leadEncoder = leadMotor.getEncoder();
  private final CANEncoder followerEncoder = followerMotor.getEncoder();

  private final SimpleMotorFeedforward shooterFeedforward =
      new SimpleMotorFeedforward(ShooterConstants.kSVolts,
                                 ShooterConstants.kVVoltSecondsPerRotation);
                                 
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    followerMotor.follow(leadMotor);

    leadMotor.setIdleMode(IdleMode.kCoast);
    followerMotor.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    super.periodic();
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Lead Encoder - Velocity", leadEncoder.getVelocity());
    SmartDashboard.putNumber("Follower Encoder - Velocity", followerEncoder.getVelocity());
  }

  public void run(Double speed) {
    leadMotor.set(speed);
  }

  public void stop() {
    leadMotor.set(0);
  }
}
