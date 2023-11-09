package View.Logged;

import Classes.User;
import View.Generic.GenericController;
import View.LogIn.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Iñigo
 */
public class LoggedController extends GenericController {

    /**
     * Button to log out.
     */
    @FXML
    private Button btnLogOut;

    /**
     * Label to welcome the user.
     */
    @FXML
    private Label lblWelcome;

    /**
     * Initializes the controller class.
     *
     * @param root Parent object.
     * @param user User object with the user's data.
     */
    public void initStage(Parent root, User user) {

        try {

            // Logger
            LOGGER.info("Initializing Logged stage.");

            // Set a title to the window
            stage.setTitle("Logged");

            // Set the window icon
            stage.getIcons().add(new Image("/Resources/logo.png"));

            // Create and set the scene
            stage.setScene(new Scene(root));

            // Set window non-resizable
            stage.setResizable(false);

            //Define the action when the x for exit on the window is clicked
            stage.setOnCloseRequest(this::handleOnActionExit);

            // Show the window
            stage.show();

            // Set the user's name in the label
            lblWelcome.setText(lblWelcome.getText().toString() + user.getName());

            // Event Handler
            this.btnLogOut.setOnAction(this::handleLogOutButtonAction);

        } catch (Exception e) {

            // severe Logger
            LOGGER.severe("Error while initializing logged window. " + e.getMessage());

            this.showErrorAlert(e.getMessage());

        }
    }

    /**
     * Method to handle the log out button action.
     *
     * @param event An action event.
     */
    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {

        try {

            // logger
            LOGGER.info("Initializing Logout Button.");

            // We show an alert to confirm the log out
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.NO, ButtonType.YES);

            alert.showAndWait();

            // If the user clicks yes, we open the login window
            if (alert.getResult() == ButtonType.YES) {

                // Load the FXML document
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LogIn/Login.fxml"));

                // Load the parent
                Parent root = (Parent) loader.load();

                // Get the controller
                LoginController controller = (LoginController) loader.getController();

                // Set the stage
                controller.setStage(stage);

                // Initialize the stage
                controller.initStage(root);

            }

        } catch (Exception e) {

            // Logger
            LOGGER.severe("Error while clicking in LogOut Button. " + e.getMessage());

            this.showErrorAlert(e.getMessage());

        }
    }
}
