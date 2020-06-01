package application;
	
import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		SoftwareApp.getInstance().update(primaryStage, "loginView.fxml", LoginController.getInstance(), false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
