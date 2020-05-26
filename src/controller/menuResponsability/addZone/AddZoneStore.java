package controller.menuResponsability.addZone;

import application.objects.Addresses;
import application.objects.Stores;
import controller.menuResponsability.popup.PopupAddress;
import database.dao.StoresDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddZoneStore extends VBox {

	private StoresDAO DAO = StoresDAO.getInstance();

	private Button add = new Button("Ajouter");
	private TextField nameField = new TextField();
	private Label addText = new Label("Ajouter un magasin");
	private Label addressName = new Label("");

	private Addresses addressTemp;
	private ChoiceBox<String> choixAddress = new ChoiceBox<String>();

	public AddZoneStore() {
		super();

		nameField.setPromptText("Nom du magasin");
		choixAddress.getItems().setAll("", "Choisir une adresse");

		HBox temp = new HBox(nameField, choixAddress, add);
		temp.getStyleClass().add("addZone");
		
		getChildren().setAll(addText, temp, addressName);
		
		choixAddress.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch (choixAddress.getItems().get((Integer) newValue)) {
				case "":
					addressTemp = null;
					addressName.setText("");
					break;

				case "Choisir une adresse":
					addressTemp = PopupAddress.getInstance().display();
					addressName.setText(addressTemp.toString());
					break;
				}
			}
		});

		add.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Stores temp = new Stores(nameField.getText(), addressTemp);
				DAO.save(temp);
				// table.getItems().add(temp);
				addressName.setText("");
				addressTemp = null;
			}
		});

	}
}
