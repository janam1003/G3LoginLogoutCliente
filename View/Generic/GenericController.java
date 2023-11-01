package View.Generic;

import java.util.logging.Logger;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * This is the base class for all the controllers in the application.
 * @author Iñigo
 */
public class GenericController {
	/**
    * Logger object used to log messages for application.
    */
   	protected static final Logger LOGGER=Logger.getLogger("G3LoginLogoutCliente.View");
	
    /**
     * Maximum text fields length.
     */
    //protected final int MAX_LENGTH=255;

	/**
	 * Patterns for text fields validation.
	 */
	protected String mailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    protected String namePattern = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$";
    protected String phonePattern = "^(\\+\\d{1,3})?\\s*\\d{1,4}\\s*\\d{1,4}$";
    protected String zipPattern = "^\\d{5}(-\\d{4})?$";
    protected String addressPattern = "^[A-Za-z0-9\\s,.'-]{3,}$";
    protected String passwordPattern = "^(?=.*[A-Z])(?=.*[\\W_]).{8,}$";
	
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

	/**
	 * Shows the content of a password field replacing it with a text field.
	 * @param Eye The FontAwesomeIcon that will be changed.
	 * @param pfPassword The password field that will be hidden or shown.
	 * @param tfPasswordReveal 	The text field that will be hidden or shown.
	 */
	protected void showPassword(FontAwesomeIcon Eye, PasswordField pfPassword,  TextField tfPasswordReveal) {
		// We check if the password field is visible or not.
		Boolean passwordVisible = pfPassword.isVisible();
		// We change the icon depending on the visibility of the password field.
		if (pfPassword.isVisible()) {
			Eye.setGlyphName("MINUS");
			tfPasswordReveal.setText(pfPassword.getText());
		} else {
			Eye.setGlyphName("EYE");
            pfPassword.setText(tfPasswordReveal.getText());
		}
		// We change the visibility of the password field and the text field.
		pfPassword.setVisible(!passwordVisible);
		tfPasswordReveal.setVisible(passwordVisible);
	}
}