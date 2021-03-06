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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoSimple extends ParallelCommandGroup {
  /**
   * Creates a new AutoSimple.
   */
  public AutoSimple(Drivetrain drivetrain, Arm arm) {
    // Add your commands in the super() call, e.g.
    super(new DriveForward(AutoConstants.speed, 
        drivetrain).withTimeout(4), 
        new ArmInitialize(arm)); 
  }
}
