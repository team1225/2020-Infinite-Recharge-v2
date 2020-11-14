/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hopper;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoSweep extends ParallelCommandGroup {
  /**
   * Creates a new AutoSweep.
   */
  public AutoSweep(Drivetrain drivetrain, Arm arm, Hopper hopper) {
    // Add your commands in the super() call, e.g.
    super(new DriveForward(AutoConstants.speed, drivetrain).withTimeout(3), new ArmPickup(arm),
        new AutoHopperIn(hopper)); 
  }
}
