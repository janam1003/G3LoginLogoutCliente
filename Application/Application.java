package Application;

import View.LogIn.LoginController;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class launches the application.
 * @author Janam and Iñigo
 */
public class Application extends javafx.application.Application {

    /**
     * Open the login window
     *
     * @param stage Stage where the scene will be projected
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
		// Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LogIn/Login.fxml"));
		// Get the root
        Parent root = (Parent) loader.load();
		// Get the controller
        LoginController controller = (LoginController) loader.getController();
		// Set the stage
        controller.setStage(stage);
		// Initialize the stage
        controller.initStage(root);
    }

    /**
	 * Entry point for the Java application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
