/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hopper;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAdvancedFast extends SequentialCommandGroup {
  /**
   * Creates a new AdvAuto.
   */
  public AutoAdvancedFast(Drivetrain drive, Arm arm, Hopper hopper) {
    super(new AutoFastSimple(drive, arm), new ArmLoading(arm).withTimeout(3), new HopperIn(hopper).withTimeout(4));
    
  }
}
