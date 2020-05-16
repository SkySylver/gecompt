package controller.menuResponsability.popup;

import application.objects.Stores;
import controller.menuResponsability.ExpertStores;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupStores {
	Stage popup;
	ExpertStores stores;
	
	public PopupStores(ExpertStores expert) {
		stores = expert;
		popup = new Stage();
		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une adresse");
	}
	
	
	public Stores display() {	


		Button selectAdd = new Button("Selectionner !");
		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				((Stage) stores.getView().getScene().getWindow()).close();
				
			}
		});
		
		stores.getView().getChildren().add(selectAdd);
		

		popup.showAndWait();
		
		
		stores.getView().getChildren().remove(selectAdd);
		
		return stores.getTable().getSelectionModel().getSelectedItem();
		
	
	}
}
