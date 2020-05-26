package controller.menuResponsability;

import application.objects.Stores;
import controller.menuResponsability.addZone.AddZoneStore;
import controller.menuResponsability.element.AddressColumn;
import controller.menuResponsability.element.StringEditableColumn;
import database.dao.StoresDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertStores extends ExpertCOR {

	private StoresDAO DAO = StoresDAO.getInstance();
	private TableView<Stores> table = new TableView<Stores>();
	private VBox view = new VBox();
	private HBox filterZone;

	public ExpertStores(ExpertCOR n) {
		super(n);
		value = "Magasins";
		initView();
		initCss();
		initFilterZone();
		
		view.getChildren().addAll(filterZone, table, new AddZoneStore());
	}

	
	@SuppressWarnings({ "unchecked" })
	protected void initView() {
		StringEditableColumn<Stores> storesName = new StringEditableColumn<Stores>("Nom", "name", Stores.class);
		AddressColumn<Stores> storesAddresses = new AddressColumn<Stores>("Adresse", "addresses", Stores.class);		

		table.getColumns().addAll(storesName, storesAddresses);
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
					table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText())));
			}
		});
		
		filterApply.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText())));
			}
		});
		filterZone = new HBox(filterField, filterApply);

	}
	
	
	
	
	

	@Override
	public VBox chargerMenu() {
		table.setItems(FXCollections.observableArrayList(DAO.listAll()));
		return view;
	}

	public VBox getView() {
		return view;
	}
	
	public TableView<Stores> getTable() {
		return table;
	}
}
