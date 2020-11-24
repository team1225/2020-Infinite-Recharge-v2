/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  private PWMTalonSRX armMotor;
  private DigitalInput downLimitSwitch;
  private DigitalInput upLimitSwitch;
  private DigitalInput safetySwitch;

  /**
   * Creates a new Arm.
   */
  public Arm() {
    armMotor = new PWMTalonSRX(ArmConstants.kMotorPort);
    downLimitSwitch = new DigitalInput(ArmConstants.kLowerLimitSwitchPort);
    upLimitSwitch = new DigitalInput(ArmConstants.kUpperLimitSwitchPort);
    safetySwitch = new DigitalInput(ArmConstants.kSafetyLimitSwitchPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // ShuffleboardTab tab = Shuffleboard.getTab("Data");
    // tab.add("Up - Arm", this.isUp());
    // tab.add("Down - Arm", this.isDown());
    SmartDashboard.putBoolean("Up - Arm", this.isUp());
    SmartDashboard.putBoolean("Down - Arm", this.isDown());
    SmartDashboard.putBoolean("Safety - Arm", this.safetyTriggered());
  }

  public void raise() {
    armMotor.set(ArmConstants.kMaxSpeedUp);
  }
  
  public void lower() {
    armMotor.set(ArmConstants.kMaxSpeedDown);
  }

  public void stop() {
    armMotor.set(0);
  }

  public boolean isDown() {
    return !downLimitSwitch.get();
  }

  public boolean isUp() {
    return !upLimitSwitch.get();
  }

  public boolean safetyTriggered() {
    return !safetySwitch.get();
  }
}