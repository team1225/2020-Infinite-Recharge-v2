/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wench;

public class WenchUp extends CommandBase {
  private final Wench m_wench;
  /**
   * Creates a new WenchUp.
   */
  public WenchUp(Wench wench) {
    super();
    // Use addRequirements() here to declare subsystem dependencies.
    m_wench = wench;
    addRequirements(m_wench);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_wench.raise();
    super.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_wench.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
