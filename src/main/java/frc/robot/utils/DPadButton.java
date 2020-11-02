
package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DPadButton extends Button {

  Joystick joystick;
  Direction direction;

  public DPadButton(final Joystick joystick, final Direction direction) {
    this.joystick = joystick;
    this.direction = direction;
  }

  public static enum Direction {
    UP(0), RIGHT(90), DOWN(180), LEFT(270);

    int direction;

    private Direction(final int direction) {
      this.direction = direction;
    }
  }

  /**
   * Returns the direction of the DPad.
   */
  public boolean get() {
    final int dpadValue = joystick.getPOV();
    return (dpadValue == direction.direction) || (dpadValue == (direction.direction + 45) % 360)
              || (dpadValue == (direction.direction + 315) % 360);
  }
}
