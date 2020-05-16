package controller;

import application.SoftwareApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import method.LoginMethod;

public class LoginController extends AbstractController {

	@FXML
	private TextField loginField, passwordField;
	@FXML
	private Text errorText;
	@FXML
	private Button connectButton;

	@FXML
	void onEnter(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER))
			connect();
	}

	@FXML
	void connect() {
		if (LoginMethod.getInstance().login(loginField.getText(), passwordField.getText()))
			SoftwareApp.getInstance().update(stage, "AppView.fxml", AppController.getInstance(), true);
		else
			errorText.setText("Identifiant ou mot de passe invalide !");
	}
	
	public void init() {
		stage.setTitle("Digicom - Authentification");
	}

	
	

	// Singleton
	private static LoginController instance = new LoginController();

	private LoginController() {
		super();
	}

	public static LoginController getInstance() {
		return instance;
	}

}
