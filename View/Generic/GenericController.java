package View.Generic;

import java.util.logging.Logger;

import Classes.SigninSignup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GenericController {
	/**
    * Logger object used to log messages for application.
    */
   	protected static final Logger LOGGER=Logger.getLogger("G3LoginLogoutCliente.View");
    /**
     * Maximum text fields length.
     */
    //protected final int MAX_LENGTH=255;

	protected String mailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    //protected SigninSignup  signinSignup ;
    //public void setUsersManager(UsersManager usersManager){
    //    this.usersManager=usersManager;
    //}

	
    /**
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the 
     * controller to the Stage object in order to make its initialization. Note 
     * that this makes Application, Controller and Stage being tightly coupled.
     */
    protected Stage stage;
    /**
     * Gets the Stage object related to this controller.
     * @return The Stage object initialized by this controller.
     */
    public Stage getStage(){
        return stage;
    }
    /**
     * Sets the Stage object related to this controller. 
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    /**
     * Shows an error message in an alert dialog.
     * @param errorMsg The error message to be shown.
     */
    protected void showErrorAlert(String errorMsg){
        //Shows error dialog.
        Alert alert=new Alert(Alert.AlertType.ERROR, errorMsg, ButtonType.OK);
        alert.getDialogPane();
        alert.showAndWait();
    }

	protected void showPassword(FontAwesomeIcon Eye, PasswordField pfPassword,  TextField tfPasswordReveal) {
		Boolean passwordVisible = pfPassword.isVisible();
		if (pfPassword.isVisible()) {
			Eye.setGlyphName("MINUS");
			tfPasswordReveal.setText(pfPassword.getText());
		} else {
			Eye.setGlyphName("EYE");
            pfPassword.setText(tfPasswordReveal.getText());
		}
		pfPassword.setVisible(!passwordVisible);
		tfPasswordReveal.setVisible(passwordVisible);
	}
}
