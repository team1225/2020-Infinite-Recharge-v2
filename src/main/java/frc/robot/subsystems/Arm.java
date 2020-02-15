/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMTalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends SubsystemBase {
  private PWMTalonSRX m_ArmMotor = new PWMTalonSRX(0);
  private DigitalInput m_Down = new DigitalInput(0);
  private DigitalInput m_Up = new DigitalInput(1);

  /**
   * Creates a new Arm.
   */
  public Arm() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void raise() {
    m_ArmMotor.set(1);
  }
  
  public void lower (){
    m_ArmMotor.set(1);
  }
  public void stop() {
    m_ArmMotor.set(0);
  }

  public boolean isDown() {
  return m_Down.get();
  }

  public boolean isUp (){
    return m_Up.get();
  }
}