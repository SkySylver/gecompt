package controller.menuResponsability.addZone;

import application.objects.Addresses;
import application.objects.Customers;
import controller.menuResponsability.popup.PopupAddress;
import database.dao.CustomersDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddZoneCustomer extends HBox{

	private TextField fieldSurname = new TextField();
	private TextField fieldFirstName = new TextField();
	private TextField fieldPhone = new TextField();
	private TextField fieldSociety = new TextField();
	private ToggleGroup fieldGender = new ToggleGroup();

	private ChoiceBox<String> choixAddress = new ChoiceBox<String>();
	private Addresses fieldAddresses;
	private Label addressName = new Label();
	
	
	private Button addCustomer = new Button("Ajouter !");
	
	
	public AddZoneCustomer() {
		super();

		fieldFirstName.setPromptText("Prénom");
		fieldSurname.setPromptText("Nom");
		fieldPhone.setPromptText("Téléphone");
		fieldSociety.setPromptText("Société");
		
		RadioButton fieldMale = new RadioButton("Homme");
		RadioButton fieldFemale = new RadioButton("Femme");

		choixAddress.getItems().setAll("", "Choisir une adresse");
		fieldMale.setToggleGroup(fieldGender);
		fieldFemale.setToggleGroup(fieldGender);
		
		getChildren().setAll(fieldSurname, fieldFirstName, new VBox(fieldFemale, fieldMale), choixAddress, fieldSociety, fieldPhone, addCustomer);
		
		getStyleClass().add("addZone");
		choixAddress.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch (choixAddress.getItems().get((Integer) newValue)) {
				case "":
					fieldAddresses = null;
					addressName.setText("");
					break;

				case "Choisir une adresse":
					fieldAddresses = PopupAddress.getInstance().display();
					addressName.setText(fieldAddresses.toString());
					break;
				}
			}
		});
		
		
		addCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Customers temp = new Customers(fieldAddresses, fieldSurname.getText(), fieldFirstName.getText(), fieldSociety.getText(), ((RadioButton)fieldGender.getSelectedToggle()).getText().equals("Homme"), fieldPhone.getText());
				CustomersDAO.getInstance().save(temp);
				//table.getItems().add(temp);
				addressName.setText("");
			}
		});
	}
	
}
