package controller.menuResponsability.popup;

import application.objects.Addresses;
import controller.menuResponsability.ExpertAddresses;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupAddress extends Stage{
	private Stage popup;
	private ExpertAddresses addresses;
	private static PopupAddress instance = new PopupAddress(new ExpertAddresses(null));
	
	
	public static PopupAddress getInstance() {
		return instance;
	}
	
	private PopupAddress(ExpertAddresses expert) {
		addresses = expert;
		popup = new Stage();
		popup.setScene(new Scene(addresses.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une adresse");
	}
	
	
	public Addresses display() {
		addresses.setModal(true);

		popup.showAndWait();
		
		addresses.setModal(false);

		return addresses.getTable().getSelectionModel().getSelectedItem();
	}
}
