package controller.menuResponsability.popup;

import application.objects.Products;
import controller.menuResponsability.ExpertProducts;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupAddProduct {
	Stage popup = new Stage();
	ExpertProducts expert;
	Products temp;

	Button selectAdd = new Button("Selectionner !");

	public PopupAddProduct(ExpertProducts expert) {
		this.expert = expert;
		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Choisissez une adresse");
	}

	public Products display() {
		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				temp = expert.getTable().getSelectionModel().getSelectedItem();
				((Stage) expert.chargerMenu().getScene().getWindow()).close();
			}
		});

		expert.chargerMenu().getChildren().add(selectAdd);

		popup.showAndWait();

		expert.chargerMenu().getChildren().remove(selectAdd);
		return temp;
	}
}
