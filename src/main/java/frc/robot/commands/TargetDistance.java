/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight2;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetDistance extends CommandBase {
  private final Drivetrain drivetrain;
  private final LimeLight2 limeLight2;

  private final double steerK = 0.03;
  private double driveK = 0.35;
  private final double desiredDistance = 12.0;
  private final double maxDrive = 0.7;

  /**
   * Creates a new Drive.
   */
  public TargetDistance(Drivetrain drivetrain, LimeLight2 limeLight2) {
    this.drivetrain = drivetrain;
    this.limeLight2 = limeLight2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drivetrain);
    addRequirements(this.limeLight2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Distance K", this.driveK);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double driveCommand;
    this.driveK = SmartDashboard.getNumber("Distance K", 1);
    if (this.limeLight2.hasTarget()) {
      driveCommand = ((this.desiredDistance - this.limeLight2.getDistance()) * this.driveK) * 1.0;
      SmartDashboard.putNumber("DRIVE COMMAND", driveCommand);
      driveCommand = driveCommand > maxDrive ? maxDrive : driveCommand;
      driveCommand = driveCommand < -maxDrive ? -maxDrive : driveCommand;
      drivetrain.drive(driveCommand, 0.0);
    } else {
      drivetrain.drive(0.0,0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !this.limeLight2.hasTarget();
  }
}
