/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class DriveConstants {
    public static final int[] kLeftMotorPorts = new int[]{7, 6};
    public static final int[] kRightMotorPorts = new int[]{10, 4};
    
    public static final double kRampRate = 1.0; // Seconds to go from 0 to full speed
    public static final double kMaxHighSpeed = 1.0;
    public static final double kMaxLowSpeed = 0.25;

    public static final int maxAmps = 50;
    public static final int maxSecAmps = 80;

    public static final double kTurnP = 0.04;
    public static final double kTurnI = 0.0;
    public static final double kTurnD = 0.0;
    public static final double kTurnToleranceDeg = 0.25;
    public static final double kTurnRateToleranceDegPerS = 1.0;

    public static final double kDistanceP = 0.35;
    public static final double kDistanceI = 0.0;
    public static final double kDistanceD = 0.0;
    public static final double kDistanceToleranceFeet = 0.25;
    public static final double kDistanceRateToleranceFeetPerS = 1.0;
  }

  public static final class WenchConstants {
    public static final int kMotorPort = 11;
    public static final double kMaxSpeedUp = 0.5;
    public static final double kMaxSpeedDown = -0.5;
    public static final int kLowerLimitSwitchPort = 3;
    public static final int kUpperLimitSwitchPort = 4;
  }

  public static final class ArmConstants {
    public static final int kMotorPort = 1;
    public static final int kLowerLimitSwitchPort = 0;
    public static final int kUpperLimitSwitchPort = 1;
    public static final int kSafetyLimitSwitchPort = 2;
    public static final double kMaxSpeedUp = 0.40;
    public static final double kMaxSpeedDown = -0.60;
  }

  public static final class HopperConstants {
    public static final int kMotorPort = 0;
    public static final double kMaxSpeed = 1;
  }

  public static final class VisionConstants {
    public static final double targetHeight = 6.9375;
    public static final double cameraHeight = 2.083;
    public static final double cameraAngle = 19.0;
    public static final double desiredDistanceToTarget = 10.0;
  }

  public static final class AutoConstants {
    public static final double speed = 0.5;
    public static final double fastSpeed = 0.75;
  }

  public static final class ShooterConstants {
    public static final int[] motorPorts = new int[]{5, 8};

    public static final double kShooterFreeRPS = 11000;
    public static final double kShooterTargetRPS = 8000;
    public static final double kShooterToleranceRPS = 50;

    public static final double kP = 1;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kSVolts = 0.05;
    public static final double kVVoltSecondsPerRotation =
        // Should have value 12V at free speed...
        12.0 / kShooterFreeRPS;
  }
}
