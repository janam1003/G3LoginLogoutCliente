package View.SignUp;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import View.Generic.GenericController;

/**
 * FXML Controller class
 *
 * @author Dani
 */
public class SignUpController extends GenericController {


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
    private TextField tfAddress;
    
    @FXML
    private FontAwesomeIcon faEye1;
    
    @FXML
    private FontAwesomeIcon faEye2;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnEye1;
    
    @FXML
    private Button btnEye2;

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("View.SignUp");
        
    @FXML
    private TextField tfPasswordReveal;
    @FXML
    private TextField tfConfirmPasswordReveal;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblZip;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblConfirmPassword;
    @FXML
    private Label lblExits;
    
    private final String mailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private final String namePattern = "\\b\\w+\\b.*\\b\\w+\\b";
    
    private final String phonePattern = "^(\\+\\d{1,3})?\\s*\\d{1,4}\\s*\\d{1,4}$";
    
    private final String zipPattern = "^\\d{5}(-\\d{4})?$";
    
    private final String addressPattern = "^[A-Za-z0-9\\s,.'-]{3,}$";
    
    private final String passwordPattern = "^(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    /**
     * Initializes the controller class.
     */
	
	 public void setStage(Stage stage) {
		this.stage = stage;
	}

    public void initStage(Parent root) {
        
        tfPasswordReveal.setVisible(false);
        tfConfirmPasswordReveal.setVisible(false);
        lblName.setVisible(false);
        lblAddress.setVisible(false);
        lblZip.setVisible(false);
        lblPhone.setVisible(false);
        lblEmail.setVisible(false);
        lblPassword.setVisible(false);
        lblConfirmPassword.setVisible(false);
        lblExits.setVisible(false);
        

        this.btnCreateAccount.setDefaultButton(true);
        this.btnCancel.setCancelButton(true);
        this.btnCreateAccount.setOnAction(this::handleSignedUpButtonAction);
        this.btnCancel.setOnAction(this::handleOnActionExit);
        
        this.btnEye1.setOnAction(this::togglePasswordVisibility1);
        this.btnEye2.setOnAction(this::togglePasswordVisibility2);

        stage.setOnCloseRequest(this::handleOnActionExit);


        LOGGER.info("Initializing the SignUp window");

        Scene scene = new Scene(root);

        //Not a resizable window.
        stage.setResizable(false);

        //Modal window of LogIn.
        stage.initModality(Modality.APPLICATION_MODAL);

        //The window title will be ”SignUp”
        stage.setTitle("SignUp");

        //Add the G3 icon.
        stage.getIcons().add(new Image("Resources/logo.png"));

        //Add scene
        stage.setScene(scene);

        //Show window
        stage.show();     
    }
    public void handleOnActionExit(Event event) {

        try {   
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Are you sure you want to exit?", ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                Platform.exit();

                LOGGER.info("SignUP Window Exit");

            } else {

                event.consume();
            }

        } catch (Exception e) {

            String errorMsg = "Error exiting application:" + e.getMessage();

            this.showErrorAlert(errorMsg);

            LOGGER.log(Level.SEVERE, errorMsg);
        }
    }
    @FXML
    private void handleSignedUpButtonAction(ActionEvent event) {
        
        boolean errorExits = false;

        try {
            if(isAnyTextFieldEmpty(tfNameSurname, tfEmail, tfPhone, tfZip, tfAddress) || isAnyTextFieldEmpty(pfPassword, pfConfirmPassword)){
                showErrorAlert("Rellene todos los campos");
            }else{
                
                Pattern nameP = Pattern.compile(namePattern);
                
                if (!nameP.matcher(tfNameSurname.getText()).matches()) {
                    lblName.setVisible(true);
                    errorExits = true;
                }else{
                    lblName.setVisible(false);
                }
                
                Pattern mailP = Pattern.compile(mailPattern);
                
                if (!mailP.matcher(tfEmail.getText()).matches()) {
                    lblEmail.setVisible(true);
                    errorExits = true;
                }else{
                    lblEmail.setVisible(false);
                }
                
                Pattern phoneP = Pattern.compile(phonePattern);
                
                if (!phoneP.matcher(tfPhone.getText()).matches()) {
                    lblPhone.setVisible(true);
                    errorExits = true;
                }else{
                    lblPhone.setVisible(false);
                }
                
                Pattern zipP = Pattern.compile(zipPattern);
                
                if (!zipP.matcher(tfZip.getText()).matches()) {
                    lblZip.setVisible(true);
                    errorExits = true;
                }else{
                    lblZip.setVisible(false);
                }
                
                Pattern AddressP = Pattern.compile(addressPattern);
                
                if (!AddressP.matcher(tfAddress.getText()).matches()) {
                    lblAddress.setVisible(true);
                    errorExits = true;
                }else{
                    lblAddress.setVisible(false);
                }
                
                Pattern PasswordP = Pattern.compile(passwordPattern);
                
                if (!PasswordP.matcher(pfPassword.getText()).matches()) {
                    lblPassword.setVisible(true);
                    errorExits = true;
                }else{
                    lblPassword.setVisible(false);
                }
                
                if (!pfPassword.getText().equals(pfConfirmPassword.getText())) {
                    lblConfirmPassword.setVisible(true);
                    errorExits = true;
                }else{
                    lblConfirmPassword.setVisible(false);
                }
                
                if(errorExits==true){
                 showErrorAlert("Revise the values");
                }else {
                
                    
                }  
            }

        } catch (Exception e) {

            
            showErrorAlert("ERROR");
        }
    }
    private void togglePasswordVisibility1(ActionEvent event){
        
        showPassword(faEye1, pfPassword, tfPasswordReveal);
    
    }
    private void togglePasswordVisibility2(ActionEvent event){
        
        showPassword(faEye2, pfConfirmPassword, tfConfirmPasswordReveal);
    
    }

    private boolean isAnyTextFieldEmpty(TextField... textFields) {
        for (TextField x : textFields) {
            if (x.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
        private boolean isAnyFieldEmpty(PasswordField... passwordFields) {
        for (PasswordField passwordField : passwordFields) {
            if (passwordField.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
