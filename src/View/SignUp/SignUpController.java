package View.SignUp;

import Classes.SigninSignup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.regex.Pattern;
import javax.security.auth.login.CredentialException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import View.Generic.GenericController;
import Classes.User;
import Exceptions.EmailAlreadyExistException;
import Exceptions.ServerErrorException;
import Exceptions.UnknownTypeException;
import Factory.ClientFactory;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Dani
 */
public class SignUpController extends GenericController {

    @FXML
    private TextField tfNameSurname;
    /**
     * Text field for the user's name and surname.
     */
    @FXML
    private TextField tfEmail;
    /**
     * Text field for the user's telephone number.
     */
    @FXML
    private TextField tfPhone;
    /**
     * Text field for the user's zip.
     */
    @FXML
    private TextField tfZip;
    /**
     * Text field for the user's address.
     */
    @FXML
    private TextField tfAddress;
    /**
     * Text field for the user's password.
     */
    @FXML
    private PasswordField pfPasswordSU;
    /**
     * Text field for the user's password confrimation.
     */
    @FXML
    private PasswordField pfConfirmPasswordSU;
    /**
     * Button to create and account.
     */
    @FXML
    private Button btnCreateAccount;
    /**
     * Button to cancel.
     */
    @FXML
    private Button btnCancel;
    /**
     * Button to show the password of the pfPassword.
     */
    @FXML
    private Button btnEye1;
    /**
     * Button to show the password of the pfConfirmPassword.
     */
    @FXML
    private Button btnEye2;
    /**
     * Icon located on the btnEye1.
     */
    @FXML
    private FontAwesomeIcon faEye1;
    /**
     * Icon located on the btnEye2.
     */
    @FXML
    private FontAwesomeIcon faEye2;
    /**
     * TextField that show's the revealed pfPassword.
     */
    @FXML
    private TextField tfPasswordRevealSU;
    /**
     * TextField that show's the revealed pfConfirmPassword.
     */
    @FXML
    private TextField tfConfirmPasswordRevealSU;
    /**
     * Label that show's Error on the tfNameSurname.
     */
    @FXML
    private Label lblName;
    /**
     * Label that show's Error on the tfZip.
     */
    @FXML
    private Label lblZip;
    /**
     * Label that show's Error on the tfPhone.
     */
    @FXML
    private Label lblPhone;
    /**
     * Label that show's Error on the tfEmail.
     */
    @FXML
    private Label lblEmail;
    /**
     * Label that show's Error on the pfPassword.
     */
    @FXML
    private Label lblPassword;
    /**
     * Label that show's Error on the pfConfirmPassword.
     */
    @FXML
    private Label lblConfirmPassword;
    /**
     * Label that show's Error if the email written on the tfEmail is alraedy in
     * use.
     */
    @FXML
    private Label lblExist;

    /**
     *
     * @param root signup init stage
     */
    public void initStage(Parent root) {

        //Setting every field in charge of showing error not visible
        tfPasswordRevealSU.setVisible(false);
        tfConfirmPasswordRevealSU.setVisible(false);
        lblName.setVisible(false);
        lblZip.setVisible(false);
        lblPhone.setVisible(false);
        lblEmail.setVisible(false);
        lblPassword.setVisible(false);
        lblConfirmPassword.setVisible(false);
        lblExist.setVisible(false);

        this.btnCreateAccount.setDefaultButton(true);
        this.btnCancel.setCancelButton(true);
        this.btnCreateAccount.setOnAction(this::handleSignedUpButtonAction);
        this.btnCancel.setOnAction(this::handleOnActionExit);
        this.btnEye1.setOnAction(this::togglePasswordVisibility1);
        this.btnEye2.setOnAction(this::togglePasswordVisibility2);

        LOGGER.info("Initializing the SignUp window");

        Scene scene = new Scene(root);

        //Creation of new stage to change its properties
        this.stage = new Stage();

        //Define the action when the window is closed
        stage.setOnCloseRequest(this::handleOnActionExit);
        //Define that the views size can't be cahnged
        stage.setResizable(false);
        //Define that the view is modal
        stage.initModality(Modality.APPLICATION_MODAL);
        //Defines the title to SignUp
        stage.setTitle("SignUp");
        //Adds the picture to the view
        stage.getIcons().add(new Image("Resources/logo.png"));
        //Sets the new scene
        stage.setScene(scene);

        // RequestFocus on NameSurname Textfield
        tfNameSurname.requestFocus();
        //The stage is shown
        stage.show();
    }

    /**
     * Method to handle the btnCreateAccount button action.
     *
     * @param event An action event.
     */
    @FXML
    private void handleSignedUpButtonAction(ActionEvent event) {

        boolean errorExists = false;

        try {

            LOGGER.info("Initializing SignUp Button");

            // We pass the value from the show password to the hidden one.
            if (pfPasswordSU.isVisible()) {
                tfPasswordRevealSU.setText(pfPasswordSU.getText());
            } else if (tfPasswordRevealSU.isVisible()) {
                pfPasswordSU.setText(tfPasswordRevealSU.getText());
            }

            if (pfConfirmPasswordSU.isVisible()) {
                tfConfirmPasswordRevealSU.setText(pfConfirmPasswordSU.getText());
            } else if (tfConfirmPasswordRevealSU.isVisible()) {
                pfConfirmPasswordSU.setText(tfConfirmPasswordRevealSU.getText());
            }

            //We check if there is an empty field 
            if (isAnyTextFieldEmpty(tfNameSurname, tfEmail, tfPhone, tfZip, tfAddress, tfPasswordRevealSU, tfConfirmPasswordRevealSU)) {
                //Throws an exception if something is empty
                throw new CredentialException("Complete every field");
            } else if (isAnyTextFieldTooLong(tfNameSurname, tfEmail, tfPhone, tfZip, tfAddress, tfPasswordRevealSU, tfConfirmPasswordRevealSU)) {
                throw new CredentialException("One field or more are too long");
            } else {
                //We check if there is any error on the text fields
                errorExists = validateField(tfNameSurname, namePattern, lblName)
                        | validateField(tfEmail, mailPattern, lblEmail)
                        | validateField(tfPhone, phonePattern, lblPhone)
                        | validateField(tfZip, zipPattern, lblZip)
                        | validateField(pfPasswordSU, passwordPattern, lblPassword);
                //We check if the content of the password and the confirm are the same
                if (!tfPasswordRevealSU.getText().equals(tfConfirmPasswordRevealSU.getText())) {
                    lblConfirmPassword.setVisible(true);
                    errorExists = true;
                } else {
                    lblConfirmPassword.setVisible(false);
                }

                //In case that there is an error we throw the CredentialException
                if (errorExists) {
                    throw new CredentialException("Revise the values");
                    //If there is no error it will start creating a user  
                } else {

                    User user = new User();
                    user.setName(tfNameSurname.getText());
                    user.setMail(tfEmail.getText());
                    user.setPhone(tfPhone.getText());
                    user.setAddress(tfAddress.getText());
                    user.setZip(Integer.parseInt(tfZip.getText()));
                    user.setPassword(pfPasswordSU.getText());
                    SigninSignup signinSignup = ClientFactory.getSigninSignup();
                    signinSignup.signUp(user);
                    //Show a confirmation message showing that the user hs been properli created
                    showUserCreatedAlert();
                    // We close the stage
                    stage.close();
                }
            }
        } catch (CredentialException e) {

            LOGGER.severe("Credential Exception: " + e.getMessage());
            this.showErrorAlert(e.getMessage());

        } catch (EmailAlreadyExistException e) {

            LOGGER.severe("Email already Exist Exception: " + e.getMessage());
            lblExist.setVisible(true);

        } catch (ServerErrorException e) {

            LOGGER.severe("Server Exception: " + e.getMessage());
            this.showErrorAlert(e.getMessage());

        } catch (UnknownTypeException e) {

            LOGGER.severe("UnknownType Exception: " + e.getMessage());
            this.showErrorAlert(e.getMessage());

        } catch (Exception e) {

            LOGGER.severe("Exception: " + e.getMessage());
            this.showErrorAlert(e.getMessage());
        }
    }

    /**
     * Method to validate every field.
     *
     * @param field The TextField that is beeing revised.
     * @param pattern Pattern for the field
     * @param label Label containing the error
     *
     * @return boolean Boolean defining if the field was properly written or not
     */
    private boolean validateField(TextField field, String pattern, Label label) {

        LOGGER.info("Validating Fields.");
        Pattern p = Pattern.compile(pattern);
        boolean valid = p.matcher(field.getText()).matches();
        label.setVisible(!valid);
        return !valid;
    }

    /**
     * Method show the pfPassword content.
     *
     * @param event An action event.
     */
    private void togglePasswordVisibility1(ActionEvent event) {

        LOGGER.info("Toggle Password Visibility1.");

        showPassword(faEye1, pfPasswordSU, tfPasswordRevealSU);

    }

    /**
     * Method show the pfPasswordConfirm content.
     *
     * @param event An action event.
     */
    private void togglePasswordVisibility2(ActionEvent event) {

        LOGGER.info("Toggle Password Visibility2.");

        showPassword(faEye2, pfConfirmPasswordSU, tfConfirmPasswordRevealSU);

    }

    /**
     * Checks if any of the provided TextFields are empty.
     *
     * @param textFields The TextFields to check for emptiness.
     * @return True if any of the TextFields is empty, false otherwise.
     */
    private boolean isAnyTextFieldEmpty(TextField... textFields) {

        LOGGER.info("Is Any TextFields are Empty.");
        for (TextField x : textFields) {
            if (x.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any of the provided TextFields is too long.
     *
     * @param textFields The TextFields to check for length.
     * @return True if any of the TextFields is too long, false otherwise.
     */
    private boolean isAnyTextFieldTooLong(TextField... textFields) {
        LOGGER.info("Is Any TextFields are Too Long.");
        for (TextField x : textFields) {
            if (x.getText().length() > MAX_LENGTH) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays an information alert to inform the user that their account has
     * been successfully created.
     */
    public void showUserCreatedAlert() {
        LOGGER.info("Shows users are created alerts.");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User Created");
        alert.setHeaderText("User successfully created.");
        alert.setContentText("You can now log in with your new account.");
        alert.showAndWait();
    }
}
