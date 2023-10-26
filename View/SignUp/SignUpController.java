/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.SignUp;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class SignUpController{

    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmPassword;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private TextField tfZip;
    @FXML
    private TextField tfNameSurname;
    @FXML
    private Label lblNameSurname;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblState;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblStreet;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblZip;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblConfirmPassword;
    @FXML
    private FontAwesomeIcon faEye1;
    @FXML
    private FontAwesomeIcon faEye2;
    @FXML
    private Button btnCancel;

	private Stage stage;
    /**
     * Initializes the controller class.
     */
	
	 public void setStage(Stage stage) {
		this.stage = stage;
	}

    public void initStage(Parent root) {
        // TODO
		    Scene scene = new Scene(root);

			stage.setResizable(false);

        	stage.setTitle("Login");

       		stage.getIcons().add(new Image("/Resources/logo.png"));
			
    		stage.setScene(scene);

        	stage.show();
    }    

    @FXML
    private void handleSignedUpButtonAction(ActionEvent event) {
    }
    
}
