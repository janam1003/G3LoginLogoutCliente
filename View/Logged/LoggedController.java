/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Logged;

import java.net.URL;
import java.util.ResourceBundle;

import Classes.User;
import View.Generic.GenericController;
import View.LogIn.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class LoggedController extends GenericController{

	@FXML
	private Button btnLogOut1;
	@FXML
	private Label lblWelcome;

	/**
	 * Initializes the controller class.
	 */
	public void initStage(Parent root, User user) {
		try {
			LOGGER.info("Initializing Logged stage.");

			stage.setTitle("Logged");
			stage.getIcons().add(new Image("/Resources/logo.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
			lblWelcome.setText(lblWelcome.getText().toString() + user.getName());
		} catch (Exception e) {
			// TODO: handle exception
			this.showErrorAlert(e.getMessage());
		}
	}

	@FXML
	private void handleLogOutButtonAction(ActionEvent event) {
		try {
			// Mostrar panel de confirmación
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.NO,
					ButtonType.YES);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				// Abrir login
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LogIn/Login.fxml"));

				Parent root = (Parent) loader.load();

				LoginController controller = (LoginController) loader.getController();

				controller.setStage(stage);

				controller.initStage(root);
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.showErrorAlert(e.getMessage());
		}
	}

}
