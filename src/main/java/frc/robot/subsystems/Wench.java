/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WenchConstants;

public class Wench extends SubsystemBase {
  private final CANSparkMax motorLeader;
  private DigitalInput limitSwitchDown;
  private DigitalInput limitSwitchUp;
  
  /**
   * Creates a new Wench.
   */
  public Wench() {
    motorLeader = new CANSparkMax(WenchConstants.kMotorPort, MotorType.kBrushless);
    motorLeader.setIdleMode(IdleMode.kBrake);
    limitSwitchDown = new DigitalInput(WenchConstants.kLowerLimitSwitchPort);
    limitSwitchUp = new DigitalInput(WenchConstants.kUpperLimitSwitchPort);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Up - Wench", this.isUp());
    SmartDashboard.putBoolean("Down - Wench", this.isDown());
  }

  public void lower() {
    motorLeader.set(WenchConstants.kMaxSpeedDown);
  }

  public void raise() {
    motorLeader.set(WenchConstants.kMaxSpeedUp);
  }

  public void stop() {
    motorLeader.set(0);
  }

  public Boolean isDown() {
    return !limitSwitchDown.get();
  }

  public Boolean isUp() {
    return !limitSwitchUp.get();
  }
}
