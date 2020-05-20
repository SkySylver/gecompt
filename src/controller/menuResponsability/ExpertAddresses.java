package controller.menuResponsability;

import application.objects.Addresses;
import controller.menuResponsability.addZone.AddZoneAddress;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.IntegerEditableColumn;
import controller.menuResponsability.element.StringEditableColumn;
import database.dao.AddressesDAO;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExpertAddresses extends ExpertCOR {

	private AddressesDAO DAO = AddressesDAO.getInstance();
	private TableView<Addresses> table = new TableView<Addresses>();
	private HBox filterZone;
	private VBox view = new VBox();

	private Button selectAdd = new Button("Selectionner !");

	public ExpertAddresses(ExpertCOR n) {
		super(n);
		value = "Adresses";
		initView();
		initFilterZone();
		initCss();

		view.getChildren().addAll(filterZone, table, new Label("Ajouter une addresse :"), new AddZoneAddress());
	}

	@SuppressWarnings("unchecked")
	protected void initView() {
		IntegerEditableColumn<Addresses> addressNumber = new IntegerEditableColumn<Addresses>("Numéro", "number",
				Addresses.class);
		StringEditableColumn<Addresses> addressStreet = new StringEditableColumn<Addresses>("Rue", "street",
				Addresses.class);
		StringEditableColumn<Addresses> addressCity = new StringEditableColumn<Addresses>("Ville", "city",
				Addresses.class);

		table.getColumns().addAll(addressNumber, addressStreet, addressCity,
				new DeleteColumn<Addresses>(Addresses.class));
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

		filterField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER))
					table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText() + "%")));
			}
		});
		
		filterApply.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText() + "%")));
			}
		});
		filterZone = new HBox(filterField, filterApply);

	}

	public TableView<Addresses> getTable() {
		return table;
	}

	@Override
	public VBox chargerMenu() {
		table.setItems(FXCollections.observableArrayList(DAO.listAll()));
		return view;
	}

	public void setModal(boolean b) {
		selectAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Stage) view.getScene().getWindow()).close();
			}
		});
		if (b)
			view.getChildren().add(selectAdd);
		else
			view.getChildren().remove(selectAdd);

	}

}
