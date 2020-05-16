package controller.menuResponsability.popup;

import application.objects.Customers;
import controller.menuResponsability.ExpertCustomers;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupCustomers {
	Stage popup;
	ExpertCustomers customers;
	
	private static PopupCustomers instance = new PopupCustomers(new ExpertCustomers(null));
	
	public static PopupCustomers getInstance() {
		return instance;
		
	}
	
	private PopupCustomers(ExpertCustomers expert) {
		customers = expert;
		popup = new Stage();
		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une adresse");
	}
	
	
	public Customers display() {


		Button selectAdd = new Button("Selectionner !");
		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				((Stage) customers.getView().getScene().getWindow()).close();
			}
		});
		
		customers.getView().getChildren().add(selectAdd);
		

		popup.showAndWait();
		
		
		customers.getView().getChildren().remove(selectAdd);
		
		return customers.getTable().getSelectionModel().getSelectedItem();
		
	
	}
}
