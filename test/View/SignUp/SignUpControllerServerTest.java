/*
 * This is a test class for the SignUpControllerServer class.
 * It contains test cases for various scenarios.
 */

// Import necessary packages and classes
package View.SignUp;

import Application.Application;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * This is the main class for the SignUpControllerServerTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerServerTest extends ApplicationTest {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Launch the application and click on the SignUp button
        new Application().start(stage);
        
    }
    
    @Override
    public void stop() {
        // Cleanup or perform any necessary actions when the test ends
    }

    /**
     * Test case for creating an account with a server error.
     */
	@Ignore
    @Test
    public void Test_A_createServerErrorTest() {
        // Perform actions to simulate user input
        clickOn("#hlSignUp");
        String mail = "johndoe"+(Math.random()*100)+"@gmail.com";
        
        clickOn("#tfNameSurname").write("John Doe");
        clickOn("#tfAddress").write("123 Main St");
        clickOn("#tfZip").write("12345");
        clickOn("#tfPhone").write("+34666666666");
        clickOn("#tfEmail").write(mail);
        clickOn("#pfPasswordSU").write("Password1234*");
        clickOn("#pfConfirmPasswordSU").write("Password1234*");
        clickOn("#btnCreateAccount");
        verifyThat("Error at reaching the server.", isVisible());
    }
    
    /**
     * Test case for handling an unknown error type.
     */
	@Ignore
    @Test
    public void Test_B_createButtonUnknownErrorTest() {
        // Verify that the error message for an unknown error type is visible
        verifyThat("Unknown type of message.", isVisible());
    }
}
