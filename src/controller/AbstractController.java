package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {

	protected Scene scene;
	protected Stage stage;
	

	public abstract void init();
	
	
	// Getters - Setters
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setStage(Stage s) {
		stage = s;
	}

	public Stage getStage(Stage s) {
		return stage;
	}
}
