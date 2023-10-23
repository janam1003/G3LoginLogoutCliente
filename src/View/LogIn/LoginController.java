/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.LogIn;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class LoginController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogInButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleSignUpHyperlinkAction(ActionEvent event) {
    }

    public void setStage(Stage stage) {
    }

    public void initialize(Parent root) {
    }

}
