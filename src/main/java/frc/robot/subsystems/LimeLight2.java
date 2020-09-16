/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.VisionConstants;

public class LimeLight2 extends SubsystemBase {
  /**
   * Creates a new LimeLight2.
   */
  private double targetX;
  private double targetY;
  private double area;
  private boolean hasTarget;
  private NetworkTable table;

  public LimeLight2() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", getX());
    SmartDashboard.putNumber("LimelightY", getY());
    SmartDashboard.putNumber("LimelightArea", getArea());
    SmartDashboard.putBoolean("LimelightHasTarget", hasTarget());
    SmartDashboard.putNumber("LimelightDistance", getDistance());
  }
  
  public double getX() {
    NetworkTableEntry tx = table.getEntry("tx");
    targetX = tx.getDouble(0.0);
    return targetX;
  }
  
  public double getY() {
    NetworkTableEntry ty = table.getEntry("ty");
    targetY = ty.getDouble(0.0);
    return targetY;
  }
  
  public double getArea() {
    NetworkTableEntry ta = table.getEntry("ta");
    area = ta.getDouble(0.0);
    return area;
  }
  
  public boolean hasTarget() {
    NetworkTableEntry tv = table.getEntry("tv");
    hasTarget = tv.getDouble(0.0) == 1.0 ? true : false;
    return hasTarget;
  }

  public double getDistance() {
    return (VisionConstants.targetHeight - VisionConstants.cameraHeight) 
        /  Math.tan(VisionConstants.cameraAngle + getY());
  }
}
