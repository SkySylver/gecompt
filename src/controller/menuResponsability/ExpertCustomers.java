package controller.menuResponsability;

import application.objects.Customers;
import controller.menuResponsability.addZone.AddZoneCustomer;
import controller.menuResponsability.element.AddressColumn;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.GenderColumn;
import controller.menuResponsability.element.StringEditableColumn;
import database.dao.CustomersDAO;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertCustomers extends ExpertCOR {

	private VBox view = new VBox();
	
	private CustomersDAO DAO = CustomersDAO.getInstance();
	private TableView<Customers> table = new TableView<Customers>();

	private HBox filterZone;
	
	
	public ExpertCustomers(ExpertCOR n) {
		super(n);
		value = "Clients";

 		initView();
		initCss();
		initFilterZone();
		table.setItems(FXCollections.observableArrayList(DAO.listAll()));
		view.getChildren().setAll(filterZone, table, new AddZoneCustomer());
	}



	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Customers> customersSurname = new StringEditableColumn<Customers>("Nom", "surname", Customers.class);
		StringEditableColumn<Customers> customersFirstName = new StringEditableColumn<Customers>("Prénom", "firstName", Customers.class);
		StringEditableColumn<Customers> customersPhone= new StringEditableColumn<Customers>("Téléphone", "phone", Customers.class);
		StringEditableColumn<Customers> customersSociety= new StringEditableColumn<Customers>("Societé", "societyName", Customers.class);
		AddressColumn<Customers> customersAddress = new AddressColumn<Customers>("Addresse", "addresses", Customers.class);
		GenderColumn<Customers> customersGender = new GenderColumn<Customers>("Genre", "gender", Customers.class);
		
		
		table.getColumns().setAll(customersGender, customersSurname, customersFirstName, customersPhone, customersSociety, customersAddress, new DeleteColumn<Customers>(Customers.class));
		table.setEditable(true);		
	}
	
	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	

	private void initFilterZone() {
		TextField filterField = new TextField();
		Button filterApply = new Button("Chercher");

		EventHandler<Event> temp = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if((event.getEventType().equals(KeyEvent.KEY_RELEASED) && ((KeyEvent)event).getCode().equals(KeyCode.ENTER)) || event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText())));
			}
		};
		
		filterField.setOnKeyReleased(temp);
		filterApply.setOnMouseClicked(temp);
		filterZone = new HBox(filterField, filterApply);
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
