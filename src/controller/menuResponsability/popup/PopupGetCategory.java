package controller.menuResponsability.popup;

import application.objects.Categories;
import controller.menuResponsability.ExpertCategories;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupGetCategory {

	private static PopupGetCategory instance;
	private static ExpertCategories expert ;
	private Stage popup;

	
	public static PopupGetCategory getInstance(ExpertCategories exp) {
		if(expert == null) expert = exp;
		if(instance == null) instance = new PopupGetCategory();
		return instance;
	}
	
	private PopupGetCategory() {

		popup = new Stage();
		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une adresse");
	}
	
	public Categories display() {
		Button selectAdd = new Button("Selectionner !");
		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				((Stage) expert.getView().getScene().getWindow()).close();
				
			}
		});
		
		expert.getView().getChildren().add(selectAdd);
		

		popup.showAndWait();
		
		
		expert.getView().getChildren().remove(selectAdd);
		
		return expert.getTable().getSelectionModel().getSelectedItem();
		
	}
	
}
