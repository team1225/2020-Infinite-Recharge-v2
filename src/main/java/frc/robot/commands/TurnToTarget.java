/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight2;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToTarget extends PIDCommand {
  private final LimeLight2 limeLight;

  /**
   * Creates a new TurnToTarget.
   */
  public TurnToTarget(final Drivetrain driveTrain, final LimeLight2 limeLight) {
    super(
        // The controller that the command will use
        new PIDController(
            DriveConstants.kTurnP, 
            DriveConstants.kTurnI, 
            DriveConstants.kTurnD),
        // This should return the measurement
        limeLight::getX,
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          driveTrain.drive(0.0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain, limeLight);
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController()
      .setTolerance(DriveConstants.kTurnToleranceDeg, DriveConstants.kTurnRateToleranceDegPerS);
    this.limeLight = limeLight;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // The command ends when it reaches the setpoint or loses the target
    return getController().atSetpoint() || !this.limeLight.hasTarget();
  }
}
