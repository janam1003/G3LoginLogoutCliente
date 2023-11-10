package Application;

import View.LogIn.LoginController;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * This class launches the application.
 *
 * @author Janam and Iñigo
 */
public class Application extends javafx.application.Application {

    /**
     * Logger object used to log messages for the application.
     */
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    /**
     * Open the login window
     *
     * @param stage Stage where the scene will be projected
     * @throws Exception all exceptions
     */
    @Override
    public void start(Stage stage) throws Exception {

        LOGGER.info("Initializing start method to open signin window.");

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
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
