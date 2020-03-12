/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.HopperConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Hopper extends SubsystemBase {
  private PWMTalonSRX m_IntakeMotor;
  /**
   * Creates a new Hooper.
   */
  public Hopper() {
    m_IntakeMotor = new PWMTalonSRX(HopperConstants.kMotorPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void in() {
    m_IntakeMotor.set(-HopperConstants.kMaxSpeed);
  }
  public void out() {
    m_IntakeMotor.set(HopperConstants.kMaxSpeed);
  }
  public void inStop() {
    m_IntakeMotor.set(0);
  }
}
