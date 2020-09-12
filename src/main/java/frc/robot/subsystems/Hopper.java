/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class Hopper extends SubsystemBase {
  private PWMTalonSRX intakeMotor;

  /**
   * Creates a new Hooper.
   */
  public Hopper() {
    intakeMotor = new PWMTalonSRX(HopperConstants.kMotorPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void in() {
    intakeMotor.set(-HopperConstants.kMaxSpeed);
  }

  public void out() {
    intakeMotor.set(HopperConstants.kMaxSpeed);
  }
  
  public void inStop() {
    intakeMotor.set(0);
  }
}
