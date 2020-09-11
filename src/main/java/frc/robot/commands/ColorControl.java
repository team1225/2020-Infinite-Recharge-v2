/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class ColorControl extends CommandBase {
  private ColorWheel colorWheel;
  private String assignedColor;

  /**
   * Creates a new ColorControl.
   */
  public ColorControl(ColorWheel colorWheel) {
    this.colorWheel = colorWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    assignedColor = colorWheel.getAssignedColor();
    colorWheel.spinSlow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    colorWheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return colorWheel.getColor() == assignedColor;
  }
}
