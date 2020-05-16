package controller.menuResponsability;

import application.objects.Addresses;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.IntegerEditableColumn;
import controller.menuResponsability.element.StringEditableColumn;
import database.dao.AddressesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


public class ExpertAddresses extends ExpertCOR{
	
	private AddressesDAO DAO = AddressesDAO.getInstance();
	private TableView<Addresses> table = new TableView<Addresses>();

	private Text responseText = new Text();
	private TextField number = new TextField();
	private TextField street = new TextField();
	private TextField city = new TextField();
	private Button addButton = new Button("Ajouter");
	private VBox vieew = new VBox();

	private Button selectAdd = new Button("Selectionner !");
	
	
	public ExpertAddresses(ExpertCOR n) {
		super(n);
		value = "Adresses";
		initView();
		initEvents();
		initAddZone();
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected void initView() {
		IntegerEditableColumn<Addresses> addressNumber = new IntegerEditableColumn<Addresses>("Numéro", "number", Addresses.class);
		StringEditableColumn<Addresses> addressStreet = new StringEditableColumn<Addresses>("Rue", "street", Addresses.class);
		StringEditableColumn<Addresses> addressCity = new StringEditableColumn<Addresses>("Ville", "city", Addresses.class);
		
		table.getColumns().addAll(addressNumber, addressStreet, addressCity, new DeleteColumn<Addresses>(Addresses.class));
		table.setEditable(true);

		vieew.getChildren().addAll(table, new Label("Ajouter une addresse :"));
	}
	
	private void initAddZone() {

		
		number.setPromptText("Numéro");
		street.setPromptText("Rue");
		city.setPromptText("Ville");
		HBox addZone = new HBox(number, street, city, addButton);

		addZone.getStyleClass().add("addZone");
		vieew.getChildren().addAll(addZone, responseText);
		
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {			
				try {
					Addresses add = new Addresses(Integer.parseInt(number.getText()), street.getText(), city.getText());
					DAO.save(add);
					table.getItems().add(add);
					responseText.setText("Adresse ajoutée !");
				}
				catch(Exception e) {
					responseText.setText("Saisie invalide !");
				}
			}});
	}
	

	protected void initEvents() {

		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Stage) vieew.getScene().getWindow()).close();
			}
		});
	}
	
	
	

	public TableView<Addresses> getTable(){
		return table;
	}
	
	@Override
	public VBox chargerMenu() {
		table.setItems(FXCollections.observableArrayList(DAO.listAll()));
		return vieew;
	}
	
	public void setModal(boolean b) {
		if(b) vieew.getChildren().add(selectAdd);
		else vieew.getChildren().remove(selectAdd);
		
	}
	
}
