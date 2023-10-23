package Application;

import View.LogIn.LoginController;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Janam
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LogIn/Login.fxml"));

        Parent root = (Parent) loader.load();

        LoginController controller = (LoginController) loader.getController();

        controller.setStage(stage);

        controller.initialize(root);

        Scene scene = new Scene(root);

        stage.setResizable(false);

        stage.setTitle("Login");

        stage.getIcons().add(new Image("/Resources/logo.png"));

        stage.setScene(scene);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
