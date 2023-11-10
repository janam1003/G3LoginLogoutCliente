package View.Logged;

import Application.Application;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/*
 * This class tests logged controller
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggedControllerTest extends ApplicationTest {

    /**
     * This method is used to start the application to be tested.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Launch the application and click fill mail and password and click on log in
        new Application().start(stage);

    }

    @Override
    public void stop() {
        // Cleanup or perform any necessary actions when the test ends
    }

    /*
	 * This method tests that logOut button works
     */
    @Test
    public void Test_A_createLogOutTest() {
        clickOn("#tfMail").write("danidiaz@gmail.com");
        clickOn("#pfPassword").write("Abcd*1234");
        clickOn("#btnLogIn");
        //We check that when we click on log out, we confirm we want to leave and we go to the log in screen
        clickOn("#btnLogOut");
        clickOn("Sí");
        verifyThat("#btnLogIn", isVisible());
    }

    /*
	 * This method tests that logOut and  no confirmation button works
     */
    @Test
    public void Test_B_createLogOutTest() {
        
        clickOn("#tfMail").write("danidiaz@gmail.com");
        clickOn("#pfPassword").write("Abcd*1234");
        clickOn("#btnLogIn");
        //We check that when we click on log out, we DONT confirm we want to leave and we stay in the logged screen
        clickOn("#btnLogOut");
        clickOn("No");
        verifyThat("#btnLogOut", isVisible());
    }
}
