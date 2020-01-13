

package frc.robot;

public final class RobotMap {
/**************************************************
 *                   CAN Ids
 **************************************************/
// Drive train motors
    public static final int rightMotorCANId = 1;
    public static final int leftMotorCANId = 2;  
    public static final int leftFrontMotorCANId = 3;
    public static final int IntakeCANId = 11;
    public static final int rightMidMotorCANId = 5;
    public static final int leftRearMotorCANId = 6;
    public static final int WenchMotorCANId = 11;
    public static final int ArmFollowerCANId = 4;
    public static final int rightFrontMotorCANId = 8;
    public static final int PCMCANId1 = 9;
    public static final int PCMCANId2 = 10;
    
/**************************************************
 *                  Solenoids
 *************************************************/
    // PCM 1
    // public static final int upleftFrontPistonId = 4; // black 
    public static final int upleftBigFootPistonId = 2; //green
    public static final int uprightFrontPistonId = 0; //purple
    // public static final int uprightBigFootPistonId = 6; // blue
    // public static final int downleftFrontPistonId = 5; //brown
    public static final int downleftBigFootPistonId = 3; // yellow
    public static final int downrightFrontPistonId = 1; //orange
    // public static final int downrightBigFootPistonId = 7; // red
    // public static final int RaiseWristChannel = 0;
    // public static final int LowerWristChannel = 1;
    public static final int EjectChannel = 4;
    public static final int RetractChannel = 5;
    
 /************************************************
  *                    Settings
  ***********************************************/   
    
    // Drive train
    public static final double rampRate = 0.5;

    // Arm
    public static final double armMaxOutput = 0.0;
    public static final double armMinOutput = 0.0;
    public static final double armP = 0.250;
    public static final double armI = 0.000001;  //1e-4;
    public static final double armD = 1.0;
    public static final double armF = 0.0;
    public static final double armTolerance = 1; // equal to about one degree or one inch at 4 feet
    public static final double armCargoLength = 53.0;
    public static final double armHatchLength = 49.0;
    public static final double armCargoCenter = 7.5;
    public static final double armHatchCenter = 9.0;
    public static final double clicksPerRotation = 140;
    public static final double heightOfAxle = 48;

    //Drive train
    public static final int maxAmps = 50;
    public static final int maxSecAmps = 80;

    //  heights
    public static final double HatchPanel1 = 1.5; // 19.0;
    public static final double HatchPanel2 = 5.0; //47.0;
    public static final double HatchPanel3 = 8.0; // 75.0;
    public static final double Cargo1 = 2.5; //27.5;
    public static final double Cargo2 = 6.0; //55.5;
    public static final double Cargo3 = 10.0; //83.5;

    // Joystick buttons
    public static final int DRIVER_CONTROLLER = 0;
	public static final int OPERATOR_CONTROLLER = 1;

	public static final int BUTTON_X = 1;
	public static final int BUTTON_A = 2;
	public static final int BUTTON_B = 3;
	public static final int BUTTON_Y = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int LEFT_TRIGGER = 7;
	public static final int RIGHT_TRIGGER = 8;
	public static final int BACK_BUTTON = 9;
	public static final int START_BUTTON = 10;
	public static final int LEFT_STICK_BUTTON = 11;
	public static final int RIGHT_STICK_BUTTON = 12;
	
	public static final int D_LEFT_STICK_X = 0;
    public static final int D_LEFT_STICK_Y = 1;
	public static final int D_RIGHT_STICK_X = 4;
    public static final int D_RIGHT_STICK_Y = 5;

    public static final int X_LEFT_STICK_X = 0;
    public static final int X_LEFT_STICK_Y = 1;
	public static final int X_RIGHT_STICK_X = 4;
	public static final int X_RIGHT_STICK_Y = 5;
    public static final int X_LEFT_TRIGGER_STICK = 2;
    public static final int X_RIGHT_TRIGGER_STICK = 3;
}