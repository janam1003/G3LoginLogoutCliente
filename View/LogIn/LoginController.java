/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.LogIn;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Classes.User;
import Exceptions.IncorrectLoginException;
import View.Generic.GenericController;
import View.Logged.LoggedController;
import View.SignUp.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author 
 */
public class LoginController extends GenericController {

    @FXML
    private TextField tfMail;
    @FXML
    private Button btnLogIn;
    @FXML
    private Hyperlink hlSignUp;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private FontAwesomeIcon btEye;
	@FXML
	private TextField tfPasswordReveal;

    @FXML
    private void handleLogInButtonAction(ActionEvent event) {
		try {
				if (tfMail.getText().isEmpty() || pfPassword.getText().isEmpty()) 
					throw new Exception("Error, rellena todos los campos");
				Pattern pattern = Pattern.compile(mailPattern);
				if (!pattern.matcher(tfMail.getText()).matches())
					throw new Exception("Error, el mail no es valido");
				User user = new User();
				user.setMail(tfMail.getText());
				user.setPassword(pfPassword.getText());
				tfMail.clear();
				pfPassword.clear();
				User userLogged = checkUser(user);
				if (userLogged == null)
					throw new IncorrectLoginException("Error, el usuario no existe(esto deberia estar en la librearia)");

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Logged/Logged.fxml"));

				Parent root = (Parent) loader.load();
			
				LoggedController controller = (LoggedController) loader.getController();
			
				controller.setStage(stage);
			
				controller.initStage(root, userLogged);
				
		} catch (Exception e) {
			// TODO: handle exception
			this.showErrorAlert(e.getMessage());
		}
    }

    private User checkUser(User user) {
		return user;
	}

	@FXML
    private void handleSignUpHyperlinkAction(ActionEvent event) {
		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUp/SignUp.fxml"));

        		Parent root = (Parent) loader.load();

				Stage modalStage = new Stage();

				modalStage.initOwner(stage);
				modalStage.initModality(Modality.WINDOW_MODAL);

       			SignUpController controller = (SignUpController) loader.getController();

        		Scene modalScene = new Scene(root);

       			modalStage.setScene(modalScene);
        		modalStage.setTitle("Registro"); // Puedes personalizar el título
        		modalStage.showAndWait(); // Mostrar la ventana modal y esperar hasta que se cierre

		} catch (Exception e) {
			// TODO: handle exception
			this.showErrorAlert(e.getMessage());
		}
    }


	@FXML
	private void handleEyeButtonAction(ActionEvent event) {
		showPassword(btEye, pfPassword, tfPasswordReveal);
	}

	public void initStage(Parent root) {
		try {
			LOGGER.info("Initializing Login stage.");

        	Scene scene = new Scene(root);

       		stage.setResizable(false);

        	stage.setTitle("Login");

       		stage.getIcons().add(new Image("/Resources/logo.png"));
			
    		stage.setScene(scene);
			//Seteamos el boton LogIn como Default
			btnLogIn.setDefaultButton(true);
			//Quitamos para que tfMail no tenga el foco
			//tfMail.setFocusTraversable(false); MEJOR O PEOR? PREGUNTAR
			tfPasswordReveal.setVisible(false);

        	stage.show();

		} catch (Exception e) {
			// TODO: handle exception
			this.showErrorAlert(e.getMessage());
		}
	}
    
}
