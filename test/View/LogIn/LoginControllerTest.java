package View.LogIn;

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
 * This is the main class for the LogInControllerServerTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest extends ApplicationTest {
	/**
	 * Random mail so it doesnt repeat when testing
	 */
	private static final String mail = "johndoe" + (Math.random() * 100) + "@gmail.com";
	private static final String mailJavi = "testJavi12@gmail.com";
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
	@Ignore
	@Test
	public void Test_A_createServerErrorTest() {
		// We check that when server is down, we get an error
		clickOn("#tfMail").write("g3@gmail.com");
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Error at reaching the server.", isVisible());
	}

	/**
	 * Test case for creating an account with a login error, then login correct
	 */
	//@Ignore
	@Test
	public void Test_B_createLoginError() {
		clickOn("#tfMail").write(mailJavi);
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Email or password is incorrect or user does not exist.", isVisible());
		clickOn("Aceptar");
	}
	//@Ignore
	@Test
	public void Test_B_createLoginWorkingTest() { 
		// We check mail correct and password are wrong, we get an error
		clickOn("#tfMail").write(mailJavi);
		clickOn("#pfPassword").eraseText(0);
		clickOn("#pfPassword").write("Password1234*");
		clickOn("#btnLogIn");
		verifyThat("#btnLogOut", isVisible());
	}

	/**
	 * Test case for creating an account with errors in the process.
	 */
	@Ignore
	@Test
	public void Test_C_generalErrorTest() {
		// We check that both are empty
		clickOn("#btnLogIn");
		verifyThat("Error, rellena todos los campos", isVisible());
		clickOn("Aceptar");
		// We check that the mail format is correct
		clickOn("#tfMail").write("g3@gmail");
		clickOn("#pfPassword").write("1234");
		clickOn("#btnLogIn");
		verifyThat("Error, el mail no es valido", isVisible());
		clickOn("Aceptar");
		// We check that the max length is 256
		clickOn("#tfMail").write(
				"aaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfu");
		clickOn("#pfPassword").write(
				"aaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfuaaaaaaaaaaajuewiuhgoiuwehuigohiuohuihuihuhuhuhuhuhufehhhfhfehfjhjfheljfkjlehfkljshflkhalfkuhlusefhulhfulhfu");
		clickOn("#btnLogIn");
		verifyThat("La longitud máxima del campo es de 255 caracteres", isVisible());
		clickOn("Aceptar");
		verifyThat("Error, el mail no es valido", isVisible());
		clickOn("Aceptar");

	}

	/**
	 * Test case for creating an account with a successful login.
	 */
	@Ignore
	@Test
	public void Test_D_signUpHyperLinkTest() {
		// We check that the hyperlink works
		clickOn("#hlSignUp");
		verifyThat("#btnCancel", isVisible());
	}

	/**
	 * Test case for testing the password reveal method
	 */
	@Ignore
	@Test
	public void Test_E_PasswordRevealTest() {
		clickOn("#btnEye");
		verifyThat("#tfPasswordReveal", isVisible());
		clickOn("#btnEye");
		verifyThat("#pfPassword", isVisible());
	}
}
