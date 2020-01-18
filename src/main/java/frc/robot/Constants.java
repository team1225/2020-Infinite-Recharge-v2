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
    public static enum UpDown {Up, Down};
    public static enum Speeds {low (0), low_mid (1), mid (2), high_mid (3), high (4);
        private final int gear;
        public static final double maxSpeeds[] = {0.20, 0.35, 0.50, 0.65, 0.80};
        Speeds(final int gear) {
            this.gear = gear;
        }
        public int gear() { return this.gear; };
        public double speed() { return maxSpeeds[this.gear]; }
        public static double speed(final int gear) {
            return maxSpeeds[gear];
        }
    };

 }
