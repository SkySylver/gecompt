package controller.menuResponsability.addZone;

import application.objects.Sellers;
import application.objects.Stores;
import controller.menuResponsability.ExpertStores;
import controller.menuResponsability.popup.PopupStores;
import database.dao.SellersDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AddZoneSellers extends HBox{

	private SellersDAO DAO = SellersDAO.getInstance();
	
	
	private TextField fieldSurname = new TextField();
	private TextField fieldFirstName = new TextField();
	private TextField fieldPhone = new TextField();
	private Stores fieldStore;

	private ChoiceBox<String> choiceStores = new ChoiceBox<String>();

	private Button addStore = new Button("Ajouter");

	public AddZoneSellers() {
		super();

		fieldFirstName.setPromptText("Prénom");
		fieldSurname.setPromptText("Nom");
		fieldPhone.setPromptText("Téléphone");
		getChildren().setAll(new HBox(fieldSurname, fieldFirstName, fieldPhone, choiceStores, addStore));
		getStyleClass().add("addZone");		choiceStores.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch (choiceStores.getItems().get((Integer) newValue)) {
				case "":
					fieldStore = null;
//					addressName.setText("");
					break;

				case "Choisir un magasin":
					fieldStore = new PopupStores(new ExpertStores(null)).display();
//					addressName.setText(fieldStore.toString());
					break;
				}
			}
		});
		
		
			addStore.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Sellers temp = new Sellers(fieldSurname.getText(), fieldFirstName.getText(), fieldPhone.getText(), fieldStore);
				DAO.save(temp);
//				table.getItems().add(temp);
			}
		});
	}
}
