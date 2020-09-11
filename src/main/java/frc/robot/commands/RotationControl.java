/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class RotationControl extends CommandBase {
  private ColorWheel colorWheel;
  private String startingColor;
  private String currentColor;
  private int counter;
  
  /**
   * Creates a new RotationControl.
   */
  public RotationControl(ColorWheel colorWheel) {
    this.colorWheel = colorWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingColor = colorWheel.getColor();
    currentColor = startingColor;
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    colorWheel.spin();
    String sensorColor = colorWheel.getColor();
    if (currentColor != sensorColor) {
      currentColor = sensorColor;
      if (currentColor == startingColor) {
        counter++;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    colorWheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter == 7;
  }
}
