/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.utils.*;

public class OI {
    public Joystick driverJoystick = new Joystick(0);
    public Joystick operatorJoystick = new Joystick(1);

    Button operatorButton1 = new JoystickButton(operatorJoystick, RobotMap.LEFT_BUMPER),
    operatorButton2 = new JoystickButton(operatorJoystick, RobotMap.RIGHT_BUMPER),
    operatorButton3 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_X),
    operatorButton4 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_Y),
    operatorButton5 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_B),
    operatorButton6 = new JoystickButton(operatorJoystick, RobotMap.BUTTON_A),
    operatorButton7 = new JoystickButton(operatorJoystick, RobotMap.RIGHT_STICK_BUTTON),
    operatorButton8 = new DPadButton(operatorJoystick, DPadButton.Direction.LEFT),
    operatorButton9 = new DPadButton(operatorJoystick, DPadButton.Direction.UP),
    operatorButton10 = new DPadButton(operatorJoystick, DPadButton.Direction.RIGHT),
    operatorButton11 = new DPadButton(operatorJoystick, DPadButton.Direction.DOWN),
    driverButton1 = new JoystickButton(driverJoystick, RobotMap.LEFT_BUMPER),
    driverButton2 = new JoystickButton(driverJoystick, RobotMap.RIGHT_BUMPER),
    driverButton3 = new JoystickButton(driverJoystick, RobotMap.BUTTON_X),
    driverButton4 = new JoystickButton(driverJoystick, RobotMap.BUTTON_Y),
    driverButton5 = new DPadButton(driverJoystick, DPadButton.Direction.LEFT),
    driverButton6 = new DPadButton(driverJoystick, DPadButton.Direction.UP),
    driverButton7 = new DPadButton(driverJoystick, DPadButton.Direction.RIGHT),
    driverButton8 = new DPadButton(driverJoystick, DPadButton.Direction.DOWN);

    public OI() {
    }

    public Joystick getdriverJoystick() {
        return driverJoystick;    
    }

    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }
}
