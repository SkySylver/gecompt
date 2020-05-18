package controller.menuResponsability;

import application.objects.Addresses;
import application.objects.Customers;
import controller.menuResponsability.element.AddressColumn;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupAddress;
import database.dao.CustomersDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertCustomers extends ExpertCOR {

	private VBox view = new VBox();
	
	private CustomersDAO DAO = CustomersDAO.getInstance();
	private TableView<Customers> table = new TableView<Customers>();

	private TextField fieldSurname = new TextField();
	private TextField fieldFirstName = new TextField();
	private TextField fieldPhone = new TextField();
	private TextField fieldSociety = new TextField();
	private ToggleGroup fieldGender = new ToggleGroup();

	private ChoiceBox<String> choixAddress = new ChoiceBox<String>();
	private Addresses fieldAddresses;
	private Label addressName = new Label();
	
	
	private Button addCustomer = new Button("Ajouter !");
	
	public ExpertCustomers(ExpertCOR n) {
		super(n);
		value = "Clients";

 		initView();
		initEvents();
		initAddZone();
		initCss();
		table.setItems(FXCollections.observableArrayList(DAO.listAll()));
	}



	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Customers> customersSurname = new StringEditableColumn<Customers>("Nom", "surname", Customers.class);
		StringEditableColumn<Customers> customersFirstName = new StringEditableColumn<Customers>("Prénom", "firstName", Customers.class);
		StringEditableColumn<Customers> customersPhone= new StringEditableColumn<Customers>("Téléphone", "phone", Customers.class);
		StringEditableColumn<Customers> customersSociety= new StringEditableColumn<Customers>("Societé", "societyName", Customers.class);
		AddressColumn<Customers> customersAddress = new AddressColumn<Customers>("Addresse", "addresses", Customers.class);
		TableColumn<Customers, Boolean> customersGender = new TableColumn<Customers, Boolean>("Genre");
		

		customersGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		customersGender.setCellFactory(tc -> new TableCell<Customers, Boolean>(){
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : item.booleanValue() ? "Monsieur" : "Madame");
			}
		});
		
		
		table.getColumns().addAll(customersGender, customersSurname, customersFirstName, customersPhone, customersSociety, customersAddress, new DeleteColumn<Customers>(Customers.class));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		
		
		//View
		view.getChildren().setAll(table);
		
	}
	
	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void initAddZone() {

		fieldFirstName.setPromptText("Prénom");
		fieldSurname.setPromptText("Nom");
		fieldPhone.setPromptText("Téléphone");
		fieldSociety.setPromptText("Société");
		
		RadioButton fieldMale = new RadioButton("Homme");
		RadioButton fieldFemale = new RadioButton("Femme");

		choixAddress.getItems().setAll("", "Choisir une adresse");
		fieldMale.setToggleGroup(fieldGender);
		fieldFemale.setToggleGroup(fieldGender);
		
		HBox addZone = new HBox(fieldSurname, fieldFirstName, new VBox(fieldFemale, fieldMale), choixAddress, fieldSociety, fieldPhone, addCustomer);
		addZone.getStyleClass().add("addZone");
		
		view.getChildren().add(addZone);
	}
	
	
	
	protected void initEvents() {
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
				DAO.save(temp);
				table.getItems().add(temp);
				addressName.setText("");
			}
		});
		
	}
	
	
	
	@Override
	public VBox chargerMenu() {
		return view;
	}


	public VBox getView() {
		return view;
	}


	public TableView<Customers> getTable() {
		return table;
	}
}
