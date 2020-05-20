package controller.menuResponsability.addZone;

import application.objects.Addresses;
import database.dao.AddressesDAO;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AddZoneAddress extends HBox{
	
	private AddressesDAO DAO = AddressesDAO.getInstance();
	private Text responseText = new Text();

	private TextField number = new TextField();
	private TextField street = new TextField();
	private TextField city = new TextField();
	private Button addButton = new Button("Ajouter");
	
	public AddZoneAddress() {
		number.setPromptText("Numéro");
		street.setPromptText("Rue");
		city.setPromptText("Ville");
		this.getChildren().addAll(number, street, city, addButton);

		getStyleClass().add("addZone");
		
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {			
				try {
					Addresses add = new Addresses(Integer.parseInt(number.getText()), street.getText(), city.getText());
					DAO.save(add);
//					table.getItems().add(add);
					responseText.setText("Adresse ajoutée !");
				}
				catch(Exception e) {
					responseText.setText("Saisie invalide !");
				}
			}});
	}

}
