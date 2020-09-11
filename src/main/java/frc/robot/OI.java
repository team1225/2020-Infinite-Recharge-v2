/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.utils.DPadButton;

public class OI {
  public Joystick driverJoystick = new Joystick(0);
  public Joystick operatorJoystick = new Joystick(1);

  Button operatorButton1 = new JoystickButton(operatorJoystick, RobotMap.LEFT_BUMPER);
  Button operatorButton2 = new JoystickButton(operatorJoystick, RobotMap.RIGHT_BUMPER);
  Button operatorButton3 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_X);
  Button operatorButton4 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_Y);
  Button operatorButton5 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_B);
  Button operatorButton6 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_A);
  Button operatorButton7 = new JoystickButton(operatorJoystick, RobotMap.RIGHT_STICK_BUTTON);
  Button operatorButton8 = new DPadButton(operatorJoystick, DPadButton.Direction.LEFT);
  Button operatorButton9 = new DPadButton(operatorJoystick, DPadButton.Direction.UP);
  Button operatorButton10 = new DPadButton(operatorJoystick, DPadButton.Direction.RIGHT);
  Button operatorButton11 = new DPadButton(operatorJoystick, DPadButton.Direction.DOWN);
  Button driverButton1 = new JoystickButton(driverJoystick, RobotMap.LEFT_BUMPER);
  Button driverButton2 = new JoystickButton(driverJoystick, RobotMap.RIGHT_BUMPER);
  Button driverButton3 = new JoystickButton(driverJoystick, RobotMap.BUTTON_X);
  Button driverButton4 = new JoystickButton(driverJoystick, RobotMap.BUTTON_Y);
  Button driverButton5 = new DPadButton(driverJoystick, DPadButton.Direction.LEFT);
  Button driverButton6 = new DPadButton(driverJoystick, DPadButton.Direction.UP);
  Button driverButton7 = new DPadButton(driverJoystick, DPadButton.Direction.RIGHT);
  Button driverButton8 = new DPadButton(driverJoystick, DPadButton.Direction.DOWN);

  public OI() {
  }

  public Joystick getdriverJoystick() {
    return driverJoystick;    
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}
