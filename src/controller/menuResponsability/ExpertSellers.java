package controller.menuResponsability;

import application.objects.Sellers;
import application.objects.Stores;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupStores;
import database.dao.SellersDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertSellers extends ExpertCOR{  

	private TextField fieldSurname = new TextField();
	private TextField fieldFirstName = new TextField();
	private TextField fieldPhone = new TextField();
	private Stores fieldStore;

	private ChoiceBox<String> choiceStores = new ChoiceBox<String>();

	private Button addStore = new Button("Ajouter");
	private HBox addZone = new HBox();
	
	
	private SellersDAO DAO = SellersDAO.getInstance();
	private TableView<Sellers> table = new TableView<Sellers>();

	private VBox view = new VBox();
	
	public ExpertSellers(ExpertCOR n) {
		super(n);
		value = "Vendeurs";
		initView();
		initEvents();
		initCss();
		initAddZone();
	}


	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Sellers> sellersSurname = new StringEditableColumn<Sellers>("Nom", "surname", Sellers.class);
		StringEditableColumn<Sellers> sellersFirstName = new StringEditableColumn<Sellers>("Prénom", "firstName", Sellers.class);
		StringEditableColumn<Sellers> sellersPhone = new StringEditableColumn<Sellers>("Téléphone", "phone", Sellers.class);
		
		TableColumn<Sellers, Stores> sellersStores = new TableColumn<Sellers, Stores>("Magasin");
		sellersStores.setCellValueFactory(new PropertyValueFactory<>("stores"));
		
		
		table.getColumns().addAll(sellersSurname, sellersFirstName, sellersPhone, sellersStores);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		
		fieldFirstName.setPromptText("Prénom");
		fieldSurname.setPromptText("Nom");
		fieldPhone.setPromptText("Téléphone");

		//choiceStores.getItems().setAll("", "Choisir un magasin");
		
		view.getChildren().setAll(table, addZone);
	}
	
	private void initAddZone() {
		addZone.getChildren().setAll(new HBox(fieldSurname, fieldFirstName, fieldPhone, choiceStores, addStore));
	}
	
	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addZone.getStyleClass().add("addZone");
	}

	protected void initEvents() {
		choiceStores.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
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
				table.getItems().add(temp);
			}
		});
	
	}
	

	@Override
	public VBox chargerMenu() {
		ObservableList<Sellers> lis = FXCollections.observableArrayList(DAO.listAll());
		table.setItems(lis);
		return view;
	}

}
