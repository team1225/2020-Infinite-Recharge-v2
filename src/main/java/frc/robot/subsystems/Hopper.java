/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Hopper extends SubsystemBase {
  private PWMTalonSRX m_LiftMotor = new PWMTalonSRX(0);
  private PWMTalonSRX m_IntakeMotor = new PWMTalonSRX(1);
  /**
   * Creates a new Hooper.
   */
  public Hopper() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void raise() {
    m_LiftMotor.set(0.5);
  }

  public void lower() {
    m_LiftMotor.set(-0.5);
  }

  public void stopLift() {
    m_LiftMotor.set(0);
  }

  public void in() {
    m_IntakeMotor.set(-1);
  }
  public void out() {
    m_IntakeMotor.set(1);
  }
  public void inStop() {
    m_IntakeMotor.set(0);
  }
}
