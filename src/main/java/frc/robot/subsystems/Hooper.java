/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Hooper extends SubsystemBase {
  private PWMTalonSRX m_motor = new PWMTalonSRX(0);
  /**
   * Creates a new Hooper.
   */
  public Hooper() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void raise() {
    m_motor.set(0.1);
  }

  public void lower() {
    m_motor.set(-0.1);
  }

  public void stop() {
    m_motor.set(0);
  }
}
