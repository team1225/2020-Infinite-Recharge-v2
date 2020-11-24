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
  private final NetworkTable table;

  public LimeLight2() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", getX());
    SmartDashboard.putNumber("LimelightY", getY());
    SmartDashboard.putNumber("LimelightArea", getArea());
    SmartDashboard.putBoolean("LimelightHasTarget", hasTarget());
    SmartDashboard.putNumber("LimelightDistance", getDistance());
  }

  /**
   * Returns the horizontal angle the target is off from center.
   *
   * @return Double Target X axis degrees from center
   */
  public double getX() {
    final NetworkTableEntry tx = table.getEntry("tx");
    targetX = tx.getDouble(0.0);
    return targetX;
  }

  /**
   * Returns the vertical angle the target is off from center.
   *
   * @return Double Target Y axis degrees from center
   */
  public double getY() {
    final NetworkTableEntry ty = table.getEntry("ty");
    targetY = ty.getDouble(0.0);
    return targetY;
  }

  /**
   * Returns the area of the target.
   *
   * @return Double Area of target
   */
  public double getArea() {
    final NetworkTableEntry ta = table.getEntry("ta");
    area = ta.getDouble(0.0);
    return area;
  }

  /**
   * Returns if the limelight has a target.
   *
   * @return Boolean Does the limelight see a target
   */
  public boolean hasTarget() {
    final NetworkTableEntry tv = table.getEntry("tv");
    hasTarget = tv.getDouble(0.0) == 1.0 ? true : false;
    return hasTarget;
  }

  /**
   * Returns the distance from the target.
   *
   * @return Double distance in feet from the target
   */
  public double getDistance() {
    return (VisionConstants.targetHeight - VisionConstants.cameraHeight) 
        /  Math.tan((VisionConstants.cameraAngle + getY()) * 3.1415 / 180);
  }
}
