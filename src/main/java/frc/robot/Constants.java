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
        public static final int kLeftMotor1Port = 7;
        public static final int kLeftMotor2Port = 1;
        public static final int kRightMotor1Port = 10;
        public static final int kRightMotor2Port = 6;

        public static final double kRampRate = 1.0; // Seconds to go from 0 to full speed

        //Drive train
        public static final int maxAmps = 50;
        public static final int maxSecAmps = 80;
    };

    public static final class WenchConstants {
        public static final int kMotorPort = 11;
        public static final double kMaxSpeedUp = 0.25;
        public static final double kMaxSpeedDown = -0.25;
    }

    public static final class ArmConstants {
        public static final int kMotorPort = 1;
        public static final int kLowerLimitSwitchPort = 0;
        public static final int kUpperLimitSwitchPort = 1;
        public static final double kMaxSpeedUp = 0.5;
        public static final double kMaxSpeedDown = -0.5;
    }

    public static final class HopperConstants {
        public static final int kMotorPort = 0;
        public static final double kMaxSpeed = 1;
    }
 }
