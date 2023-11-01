package View.LogIn;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.regex.Pattern;
import Classes.SigninSignup;
import Classes.User;
import Exceptions.IncorrectLoginException;
import Exceptions.MaxUserException;
import Exceptions.ServerErrorException;
import Exceptions.UnknownTypeException;
import Factory.ClientFactory;
import View.Generic.GenericController;
import View.Logged.LoggedController;
import View.SignUp.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Iñigo
 */
public class LoginController extends GenericController {
	/**
	 * Text field for the user's mail.
	 */
	@FXML
	private TextField tfMail;
	/**
	 * Button to log in.
	 */
	@FXML
	private Button btnLogIn;
	/**
	 * Hyperlink to sign up.
	 */
	@FXML
	private Hyperlink hlSignUp;
	/**
	 * Text field for the user's password.
	 */
	@FXML
	private PasswordField pfPassword;
	/**
	 * Label for the username.
	 */
	@FXML
	private Label lblUsername;
	/**
	 * Label for the password.
	 */
	@FXML
	private Label lblPassword;
	/**
	 * Button to show the password.
	 */
	@FXML
	private FontAwesomeIcon btEye;
	/**
	 * Text field to show the password.
	 */
	@FXML
	private TextField tfPasswordReveal;

	/**
	 * Method to initialize the stage.
	 * 
	 * @param root FXML document graph.
	 */
	public void initStage(Parent root) {
		try {
			LOGGER.info("Initializing Login stage.");
			// We create a new scene
			Scene scene = new Scene(root);
			// We set the scene not resizable
			stage.setResizable(false);
			// We set the title Login
			stage.setTitle("Login");
			// We set the window icon
			stage.getIcons().add(new Image("/Resources/logo.png"));
			// We set the scene
			stage.setScene(scene);
			// We set the default button
			btnLogIn.setDefaultButton(true);
			// We set the cancel button
			tfPasswordReveal.setVisible(false);
			// We show the stage
			stage.show();

		} catch (Exception e) {
			this.showErrorAlert(e.getMessage());
		}
	}

	/**
	 * Method to handle the LogIn button action.
	 * 
	 * @param event An action event.
	 */
	@FXML
	private void handleLogInButtonAction(ActionEvent event) {
		try {
			// If the password is revealed, we get it text to set it into password field
			if (tfPasswordReveal.isVisible())
				pfPassword.setText(tfPasswordReveal.getText());
			// If the mail or the password are empty, we throw an exception
			if (tfMail.getText().isEmpty() || pfPassword.getText().isEmpty())
				throw new Exception("Error, rellena todos los campos");
			// We check if the mail pattern is valid
			Pattern pattern = Pattern.compile(mailPattern);
			if (!pattern.matcher(tfMail.getText()).matches())
				throw new Exception("Error, el mail no es valido");
			// We create a new user with the mail and the password
			User user = new User();
			user.setMail(tfMail.getText());
			user.setPassword(pfPassword.getText());
			// We clear the text fields
			tfMail.clear();
			pfPassword.clear();
			// We get the user logged
			SigninSignup signinSignup = ClientFactory.getSigninSignup();
			User userLogged = signinSignup.SignIn(user);
			// We load the Logged view
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Logged/Logged.fxml"));
			// We get the root
			Parent root = (Parent) loader.load();
			// We get the controller
			LoggedController controller = (LoggedController) loader.getController();
			// We set the stage
			controller.setStage(stage);
			// We init the stage
			controller.initStage(root, userLogged);

		} catch (IncorrectLoginException e) {
			this.showErrorAlert(e.getMessage());
		} catch (ServerErrorException e) {
			this.showErrorAlert(e.getMessage());
		} catch (MaxUserException e) {
			this.showErrorAlert(e.getMessage());
		} catch (UnknownTypeException e) {
			this.showErrorAlert(e.getMessage());
		} catch (Exception e) {
			this.showErrorAlert(e.getMessage());
		}
	}

	/**
	 * Method to handle the SignUp hyperlink action.
	 * 
	 * @param event An action event.
	 */
	@FXML
	private void handleSignUpHyperlinkAction(ActionEvent event) {
		try {
			// We load the SignUp view
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUp/SignUp.fxml"));
			// We get the root
			Parent root = (Parent) loader.load();
			// We get the controller
			SignUpController controller = (SignUpController) loader.getController();
			// We set the stage
			controller.setStage(stage);
			// We init the stage
			controller.initStage(root);

		} catch (Exception e) {
			this.showErrorAlert(e.getMessage());
		}
	}

	/**
	 * Method to handle the Eye button action.
	 * 
	 * @param event An action event.
	 */
	@FXML
	private void handleEyeButtonAction(ActionEvent event) {
		// We call the showPassword method
		showPassword(btEye, pfPassword, tfPasswordReveal);
	}
}
