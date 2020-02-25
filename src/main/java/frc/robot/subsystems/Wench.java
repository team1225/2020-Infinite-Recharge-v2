/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.WenchConstants;

public class Wench extends SubsystemBase {
  private final CANSparkMax m_motorLeader;
  private DigitalInput m_Down;
  private DigitalInput m_Up;
  /**
   * Creates a new Wench.
   */
  public Wench() {
    m_motorLeader = new CANSparkMax(WenchConstants.kMotorPort, MotorType.kBrushless);
    m_motorLeader.setIdleMode(IdleMode.kBrake);
    m_Down = new DigitalInput(WenchConstants.kLowerLimitSwitchPort);
    m_Up = new DigitalInput(WenchConstants.kUpperLimitSwitchPort);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Up - Wench", this.isUp());
    SmartDashboard.putBoolean("Down - Wench", this.isDown());
  }

  public void lower() {
    m_motorLeader.set(WenchConstants.kMaxSpeedDown);
  }

  public void raise() {
      m_motorLeader.set(WenchConstants.kMaxSpeedUp);
  }

  public void stop() {
      m_motorLeader.set(0);
  }

  public Boolean isDown() {
    return !m_Down.get();
  }

  public Boolean isUp() {
    return !m_Up.get();
  }
}
