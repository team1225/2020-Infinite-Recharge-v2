package frc.robot.subsystems;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.team1225.helpers.MockButton;
import com.team1225.helpers.MockHardwareExtension;
import com.team1225.helpers.TestWithScheduler;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.WenchDown;
// import frc.robot.subsystems.Wench;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WenchTest {

  Wench mockedWench;

  /**
  * This method is run before the tests begin. initialize all mocks you wish to
  * use in multiple functions here. Copy and paste this function in your own test
  */
  @BeforeEach
  public void before() {
    TestWithScheduler.schedulerStart();
    TestWithScheduler.schedulerClear();
    MockHardwareExtension.beforeAll();
    mockedWench = mock(Wench.class);
  }

  @Test
  @DisplayName("When command WenchDown activated lower method should be called twice")
  public void wenchDownOnWenchSubSystem() {
    // Reset the subsystem to make sure all mock values are reset
    reset(mockedWench);

    // Create command
    final WenchDown wenchDown = new WenchDown(mockedWench);

    // Create a fake button that will be "pressed"
    final MockButton fakeButton = new MockButton();

    // Tell the button to run example command when pressed
    fakeButton.whenPressed(wenchDown);

    // Push the button and run the scheduler once
    fakeButton.push();
    CommandScheduler.getInstance().run();
    fakeButton.release();

    // Verify that subsystemMethod was called twice
    verify(mockedWench, times(2)).lower();

    // Clear the scheduler
    TestWithScheduler.schedulerClear();
  }

  // This test makes sure that periodic is called properly (odd case as this
  // should already work, but you may want to test methods inside of periodic)
  @Test
  public void wenchSubSystemCallsPeriodic() {
    // Reset the subsystem to make sure all mock values are reset
    reset(mockedWench);

    // Make sure that the scheduler has the subsystem registered
    CommandScheduler.getInstance().registerSubsystem(mockedWench);

    // run the scheduler once
    CommandScheduler.getInstance().run();

    // Verify that periodic was called once
    verify(mockedWench, times(1)).periodic();

    // Clear the scheduler
    TestWithScheduler.schedulerClear();
  }
    
  // This is called after tests, and makes sure that nothing is left open and
  // everything is ready for the next test class
  @AfterEach
  public void after() {
    TestWithScheduler.schedulerDestroy();
    MockHardwareExtension.afterAll();
  }
}
