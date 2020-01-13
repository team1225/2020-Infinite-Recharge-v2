/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Wench extends SubsystemBase {
  private CANSparkMax m_motorLeader;
  private CANEncoder m_encoder;
  /**
   * Creates a new Wench.
   */
  public Wench() {
    m_motorLeader = new CANSparkMax(RobotMap.WenchMotorCANId, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void lower() {
    m_motorLeader.set(-1);
  }

public void raise() {
    m_motorLeader.set(1);
}

public void stop() {
    m_motorLeader.set(0);
}
}
