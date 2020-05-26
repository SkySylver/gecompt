package controller.menuResponsability;

import application.objects.Sellers;
import application.objects.Stores;
import controller.menuResponsability.addZone.AddZoneSellers;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupStores;
import database.dao.SellersDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertSellers extends ExpertCOR{  

	private SellersDAO DAO = SellersDAO.getInstance();
	private TableView<Sellers> table = new TableView<Sellers>();
	private HBox filterZone ;


	private VBox view = new VBox();
	
	public ExpertSellers(ExpertCOR n) {
		super(n);
		value = "Vendeurs";
		initView();
		initFilterZone();
		initCss();
		
		view.getChildren().setAll(filterZone, table, new AddZoneSellers());
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
		
		
	}
	
	
	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	
	private void initFilterZone() {
		TextField filterField = new TextField();
		Button filterApply = new Button("Rechercher");
		filterZone = new HBox();
		filterZone = new HBox(filterField, filterApply);
		
		EventHandler<Event> temp = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if((event.getEventType().equals(KeyEvent.KEY_RELEASED) && ((KeyEvent)event).getCode().equals(KeyCode.ENTER)) || event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				table.setItems(FXCollections.observableArrayList(DAO.list(filterField.getText())));
			}
		};
		
		filterField.setOnKeyReleased(temp);
		filterApply.setOnMouseClicked(temp);
	}
	

	@Override
	public VBox chargerMenu() {
		ObservableList<Sellers> lis = FXCollections.observableArrayList(DAO.listAll());
		table.setItems(lis);
		return view;
	}

}
