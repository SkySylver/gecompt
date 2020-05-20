package controller.menuResponsability;

import application.objects.Addresses;
import application.objects.Stores;
import controller.menuResponsability.element.AddressColumn;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupAddress;
import database.dao.StoresDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertStores extends ExpertCOR {

	private StoresDAO DAO = StoresDAO.getInstance();
	private TableView<Stores> table = new TableView<Stores>();
	private VBox vieew = new VBox();

	private Button add = new Button("Ajouter");
	private TextField nameField = new TextField();
	private Label addText = new Label("Ajouter un magasin");
	private Label addressName = new Label("");
	private Addresses addressTemp;
	private ChoiceBox<String> choixAddress = new ChoiceBox<String>();
	private HBox addZone;
	private VBox addPanel;

	public ExpertStores(ExpertCOR n) {
		super(n);
		value = "Magasins";
		initAddZone();
		initView();
		initEvents();
		initCss();
	}

	
	@SuppressWarnings({ "unchecked" })
	protected void initView() {
		StringEditableColumn<Stores> storesName = new StringEditableColumn<Stores>("Nom", "name", Stores.class);
		AddressColumn<Stores >storesAddresses = new AddressColumn<Stores>("Adresse", "addresses", Stores.class);		
				
		table.getColumns().addAll(storesName, storesAddresses);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		

		vieew.getChildren().addAll(table, addPanel);
	}


	private void initAddZone() {
		nameField.setPromptText("Nom du magasin");
		choixAddress.getItems().setAll("", "Choisir une adresse");
		addZone = new HBox(nameField, choixAddress, add);
		addPanel = new VBox(addText, addZone, addressName);
	}
	
	private void initCss() {
		table.getStyleClass().add("table");
		vieew.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addZone.getStyleClass().add("addZone");
	}
	
	protected void initEvents() {
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
				table.getItems().add(temp);
				addressName.setText("");
				addressTemp = null;
			}
		});
	}
	
	@Override
	public VBox chargerMenu() {
		ObservableList<Stores> lis = FXCollections.observableArrayList(DAO.listAll());
		table.setItems(lis);
		return vieew;
	}

	
	public VBox getView() {
		return vieew;
	}
	
	public TableView<Stores> getTable() {
		return table;
	}
}
