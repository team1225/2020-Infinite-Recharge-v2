/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends SubsystemBase {
  private PWMTalonSRX m_ArmMotor;
  private DigitalInput m_Down;
  private DigitalInput m_Up;
  private DigitalInput m_Safety;

  /**
   * Creates a new Arm.
   */
  public Arm() {
    m_ArmMotor = new PWMTalonSRX(ArmConstants.kMotorPort);
    m_Down = new DigitalInput(ArmConstants.kLowerLimitSwitchPort);
    m_Up = new DigitalInput(ArmConstants.kUpperLimitSwitchPort);
    m_Safety = new DigitalInput(ArmConstants.kSafetyLimitSwitchPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Up - Arm", this.isUp());
    SmartDashboard.putBoolean("Down - Arm", this.isDown());
    SmartDashboard.putBoolean("Safety - Arm", this.safetyTriggered());
  }
  public void raise() {
    m_ArmMotor.set(ArmConstants.kMaxSpeedUp);
  }
  
  public void lower (){
    m_ArmMotor.set(ArmConstants.kMaxSpeedDown);
  }
  public void stop() {
    m_ArmMotor.set(0);
  }

  public boolean isDown() {
    return !m_Down.get();
  }

  public boolean isUp (){
    return !m_Up.get();
  }

  public boolean safetyTriggered() {
    return !m_Safety.get();
  }
}