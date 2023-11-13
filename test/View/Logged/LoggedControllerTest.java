package View.Logged;

import Application.Application;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
	 * Random mail so it doesnt repeat when testing
	 */
	private static final String mail = "johndoe" + (Math.random() * 100) + "@gmail.com";
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
	@Ignore
    @Test
    public void Test_A_createLogOutTest() {
		clickOn("#hlSignUp");
		clickOn("#tfNameSurname").write("John Doe");
        clickOn("#tfAddress").write("123 Main St");
        clickOn("#tfZip").write("12345");
        clickOn("#tfPhone").write("+34666666666");
		clickOn("#tfEmail").write(mail);
        clickOn("#pfPasswordSU").write("Password1234*");
        clickOn("#pfConfirmPasswordSU").write("Password1234*");
        clickOn("#btnCreateAccount");
        clickOn("Aceptar");
		clickOn("#tfMail").write(mail);
		clickOn("#pfPassword").write("Password1234*");
		clickOn("#btnLogIn");
        //We check that when we click on log out, we confirm we want to leave and we go to the log in screen
        clickOn("#btnLogOut");
        clickOn("Sí");
        verifyThat("#btnLogIn", isVisible());
    }

    /*
	 * This method tests that logOut and  no confirmation button works
     */
	@Ignore
    @Test
    public void Test_B_createLogOutTest() {
		clickOn("#hlSignUp");
		clickOn("#tfNameSurname").write("John Doe");
        clickOn("#tfAddress").write("123 Main St");
        clickOn("#tfZip").write("12345");
        clickOn("#tfPhone").write("+34666666666");
		clickOn("#tfEmail").write(mail);
        clickOn("#pfPasswordSU").write("Password1234*");
        clickOn("#pfConfirmPasswordSU").write("Password1234*");
        clickOn("#btnCreateAccount");
        clickOn("Aceptar");
		clickOn("#tfMail").write(mail);
		clickOn("#pfPassword").write("Password1234*");
		clickOn("#btnLogIn");
        //We check that when we click on log out, we DONT confirm we want to leave and we stay in the logged screen
        clickOn("#btnLogOut");
        clickOn("No");
        verifyThat("#btnLogOut", isVisible());
    }
}
