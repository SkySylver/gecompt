package application;

import java.io.IOException;

import com.sun.javafx.css.StyleManager;

import controller.AbstractController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class SoftwareApp {

	// Singleton
	private static SoftwareApp instance = new SoftwareApp();

	private SoftwareApp() {
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
		StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/view/style.css").toString());
	}

	public static SoftwareApp getInstance() {
		return instance;
	}

	public void update(Stage stage, String fxmlroot, AbstractController controller, boolean maximised) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlroot));
		loader.setController(controller);

		try {
			controller.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			System.out.println("Impossible de charger l'interface");
		}

		controller.setStage(stage);
		stage.setScene(controller.getScene());
		stage.setMaximized(maximised);
		
		controller.init();
	}

}
