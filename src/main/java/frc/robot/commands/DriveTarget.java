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

public class DriveTarget extends CommandBase {
  private final Drivetrain drivetrain;
  private final LimeLight2 limeLight2;

  private final double steerK = 0.03;
  private final double driveK = 0.26;
  private final double desiredDistance = 4.0;
  private final double maxDrive = 0.7;

  /**
   * Creates a new Drive.
   */
  public DriveTarget(Drivetrain drivetrain, LimeLight2 limeLight2) {
    this.drivetrain = drivetrain;
    this.limeLight2 = limeLight2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drivetrain);
    addRequirements(this.limeLight2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double driveCommand;
    if (this.limeLight2.hasTarget()) {
      driveCommand = this.desiredDistance - this.limeLight2.getDistance() * this.driveK;
      driveCommand = driveCommand > maxDrive ? maxDrive : driveCommand;
      driveCommand = driveCommand < -maxDrive ? -maxDrive : driveCommand;
      drivetrain.drive(driveCommand, this.limeLight2.getX() * this.steerK);
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
