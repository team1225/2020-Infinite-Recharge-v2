/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch colorMatcher = new ColorMatch();

  private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private PWMTalonSRX spinner = new PWMTalonSRX(2);

  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel() {
    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget);   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Color detectedColor = colorSensor.getColor();

    String colorString;
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.color == blueTarget) {
      colorString = "Blue";
    } else if (match.color == redTarget) {
      colorString = "Red";
    } else if (match.color == greenTarget) {
      colorString = "Green";
    } else if (match.color == yellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }

  public void spin() {
    spinner.set(1.0);
  }

  public void spinSlow() {
    spinner.set(0.30);
  }

  public void stop() {
    spinner.set(0);
  }

  public void reverse() {
    spinner.set(-1.0);
  }

  /**
   * Determines the closest color to the defined colors.
   * Converts color to a String representing the name of the color 
   * @return String color
   */
  public String getColor() {
    Color detectedColor = colorSensor.getColor();

    String colorString;
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == blueTarget) {
      colorString = "Blue";
    } else if (match.color == redTarget) {
      colorString = "Red";
    } else if (match.color == greenTarget) {
      colorString = "Green";
    } else if (match.color == yellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }

  /**
   * Returns the color assigned by the field.
   * @return String representing the full name of the color.
   */
  public String getAssignedColor() {
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          return "Red";
        case 'G' :
          return "Yellow";
        case 'R' :
          return "Blue";
        case 'Y' :
          return "Green";
        default :
          return "None";
      }
    } else {
      return "None";
    }
  }
}
