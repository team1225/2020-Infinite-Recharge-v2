/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.WenchConstants;

public class Wench extends SubsystemBase {
  private CANSparkMax m_motorLeader;
  /**
   * Creates a new Wench.
   */
  public Wench() {
    m_motorLeader = new CANSparkMax(WenchConstants.kMotorPort, MotorType.kBrushless);
    m_motorLeader.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
}
