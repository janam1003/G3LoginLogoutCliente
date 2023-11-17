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
import View.Generic.MailLengthException;
import View.Generic.PassLengthException;
import View.Logged.LoggedController;
import View.SignUp.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
	 * Button to Eye.
	 */
	@FXML
	private Button btnEye;

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
	 * Icon for the button to show the password.
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

			// Logger
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

			// Define the action when the x for exit on the window is clicked
			stage.setOnCloseRequest(this::handleOnActionExit);

			// Event Handlers
			this.hlSignUp.setOnAction(this::handleSignUpHyperlinkAction);

			this.btnLogIn.setOnAction(this::handleLogInButtonAction);

			this.btnEye.setOnAction(this::handleEyeButtonAction);

			// We show the stage
			stage.show();

		} catch (Exception e) {

			// Logger
			LOGGER.severe("Unable to Initialize Login window: " + e.getMessage());

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

			// Logger
			LOGGER.info("Initializing login button action.");

			// If the password is revealed, we get it text to set it into password field
			if (tfPasswordReveal.isVisible()) {
				pfPassword.setText(tfPasswordReveal.getText());
			}

			// If the mail or the password are empty, we throw an exception
			if (tfMail.getText().trim().isEmpty() || pfPassword.getText().isEmpty()) {
				throw new Exception("Error, rellena todos los campos");
			}

			// Crompobamos la longitud del Mail, si es correcto se cambia a negro por si se
			// habia cambiado a rojo
			if (tfMail.getText().length() > this.MAX_LENGTH)
				throw new MailLengthException("La longitud máxima del campo Mail es de 255 caracteres");
			else
				tfMail.setStyle("-fx-text-fill: black");
			// Crompobamos la longitud del Passeord, si es correcto se cambia a negro por si
			// se habia cambiado a rojo
			if (pfPassword.getText().length() > this.MAX_LENGTH)
				throw new PassLengthException("La longitud máxima del campo Pass es de 255 caracteres");
			else
				pfPassword.setStyle("-fx-text-fill: black");

			// We check if the mail pattern is valid
			Pattern pattern = Pattern.compile(mailPattern);
			if (!pattern.matcher(tfMail.getText()).matches()) {
				throw new Exception("Error, el mail no es valido");
			}

			// We create a new user with the mail and the password
			User user = new User();

			user.setMail(tfMail.getText());

			user.setPassword(pfPassword.getText());

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
		} catch (MailLengthException e) {
			// Logger
			LOGGER.severe("Mail Length exception. " + e.getMessage());
			// MOstramos la alerta al usario
			this.showErrorAlert(e.getMessage());
			// Cambiamos el texto de color a rojo
			tfMail.setStyle("-fx-text-fill: red");
			// Le damos el foco
			tfMail.setFocusTraversable(true);
		} catch (PassLengthException e) {
			// Logger
			LOGGER.severe("Pass Length exception. " + e.getMessage());
			// Mostramos el error
			this.showErrorAlert(e.getMessage());
			// Cambiamos a rojo la password
			pfPassword.setStyle("-fx-text-fill: red");
			// Le damos el foco
			pfPassword.setFocusTraversable(true);
		} catch (IncorrectLoginException e) {

			// Logger
			LOGGER.severe("Incorrect Login Exception. " + e.getMessage());

			this.showErrorAlert(e.getMessage());

		} catch (ServerErrorException e) {

			// Logger
			LOGGER.severe("Server Error Exception. " + e.getMessage());
			this.showErrorAlert(e.getMessage());

		} catch (MaxUserException e) {

			// Logger
			LOGGER.severe("Max User Exception. " + e.getMessage());
			this.showErrorAlert(e.getMessage());

		} catch (UnknownTypeException e) {

			// Logger
			LOGGER.severe("UnknownType Exception. " + e.getMessage());
			this.showErrorAlert(e.getMessage());

		} catch (Exception e) {

			// Logger
			LOGGER.severe("Exception. " + e.getMessage());
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

			// Logger
			LOGGER.info("Initializing SignUp Hypwrlink Action.");

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

			// Logger
			LOGGER.severe("Exception: " + e.getMessage());
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

		// Logger
		LOGGER.info("Initializing handle on eye button action.");

		// We call the showPassword method
		showPassword(btEye, pfPassword, tfPasswordReveal);
	}
}
