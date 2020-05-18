package controller.menuResponsability.popup;

import application.objects.Categories;
import controller.menuResponsability.ExpertCategories;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupCategories {
	Stage popup;
	ExpertCategories expert;
	
	private static PopupCategories instance = new PopupCategories(new ExpertCategories(null));
	
	public static PopupCategories getInstance() {
		return instance;
		
	}
	
	private PopupCategories(ExpertCategories expert) {
		this.expert = expert;
		popup = new Stage();
		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une catégorie");
	}
	
	
	public Categories display() {
		Button selectCat = new Button("Selectionner !");
		selectCat.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Stage) expert.getView().getScene().getWindow()).close();
			}
		});
		
		expert.getView().getChildren().add(selectCat);
		popup.showAndWait();
		expert.getView().getChildren().remove(selectCat);
		
		return expert.getTable().getSelectionModel().getSelectedItem();
	}
}
