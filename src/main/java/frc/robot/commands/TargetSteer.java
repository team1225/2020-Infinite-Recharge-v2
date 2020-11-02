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

public class TargetSteer extends CommandBase {
  private final Drivetrain drivetrain;
  private final LimeLight2 limeLight2;

  private final double steerK = 0.04;
  private final double driveK = 0.26;
  private final double desiredDistance = 10.0;
  private final double maxDrive = 0.7;

  /**
   * Creates a new Drive.
   */
  public TargetSteer(final Drivetrain drivetrain, final LimeLight2 limeLight2) {
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
    double steerCommand;
    double maxSteer = 0.4;
    if (this.limeLight2.hasTarget()) {
        steerCommand = this.limeLight2.getX() * this.steerK;
        steerCommand = steerCommand > maxSteer ? maxSteer : steerCommand;
        steerCommand = steerCommand < -maxSteer ? -maxSteer : steerCommand;
        

      drivetrain.drive(0.0, steerCommand);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    drivetrain.drive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !this.limeLight2.hasTarget();
  }
}
