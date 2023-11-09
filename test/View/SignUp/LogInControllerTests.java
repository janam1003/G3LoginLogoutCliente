package View.SignUp;

import Application.Application;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * This is the main class for the LogInControllerServerTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInControllerTests extends ApplicationTest{
	/**
	 * This method is used to start the application to be tested.
	 */
	@Override
    public void start(Stage stage) throws Exception {
        // Launch the application
        new Application().start(stage);
    }
    
	/**
	 * This method is used to stop the application to be tested.
	 */
    @Override
    public void stop() {
        // Cleanup or perform any necessary actions when the test ends
    }

    /**
     * Test case for creating an account with a server error.
     */
    @Test
    public void Test_A_createServerErrorTest() {
		//We check that when server is down, we get an error
		clickOn("#tfEmail").write("g3@gmail");
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Error at reaching the server.", isVisible());
	}
	/**
	 * Test case for creating an account with a login error.
	 */
	@Test
	public void Test_B_createLoginErrorTest() {
		//We check when server up and mail or password are wrong, we get an error
		clickOn("#tfEmail").write("notExistingMail@gmail");
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Email or password is incorrect or user does not exist.", isVisible());
	}

	/**
	 * Test case for creating an account with errors in the process.
	 */
	@Test
	public void Test_C_generalErrorTest() {
		//We check that both are empty
		clickOn("#btnLogIn");
		verifyThat("Error, rellena todos los campos", isVisible());
		//We check that the mail format is correct
		clickOn("#tfEmail").write("g3@gmail");
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Error, el mail no es valido", isVisible());
		//We check that the max length is 256
		clickOn("#tfEmail").write("aaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfu");
		clickOn("#pfPassword").write("aaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfu");
		verifyThat("La longitud máxima del campo es de 255 caracteres", isVisible());
	}

	/**
	 * Test case for creating an account with a successful login.
	 */
	@Test
	public void Test_D_signUpHyperLinkTest() {
		//We check that the hyperlink works
		clickOn("#hlSignUp");
		verifyThat("#btnCancel", isVisible());
	}
}
