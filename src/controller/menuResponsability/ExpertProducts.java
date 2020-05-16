package controller.menuResponsability;

import java.math.BigDecimal;
import java.util.List;

import application.objects.Categories;
import application.objects.Products;
import application.objects.Vat;
import controller.menuResponsability.element.BooleanColumn;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupStock;
import database.dao.ProductsDAO;
import database.dao.VatDAO;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.BigDecimalStringConverter;

public class ExpertProducts extends ExpertCOR {

	private ProductsDAO dao = ProductsDAO.getInstance();
	private TableView<Products> table = new TableView<Products>();
	private VBox view = new VBox();
	private List<Products> listProducts;
	private Categories currentCategory = null;

	private TextField fieldName = new TextField();
	private TextField fieldDesc = new TextField();
	private TextField fieldPrice = new TextField();
	private ChoiceBox<Vat> choixVAT = new ChoiceBox<Vat>();
	private Button addButton = new Button("Ajouter");
	
	private PopupStock popupStocks = new PopupStock();

	public ExpertProducts(ExpertCOR n) {
		super(n);
		value = "Produits";
		listProducts = dao.listAll();
		initView();
		initEvents();
		update();
	}



	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Products> productName = new StringEditableColumn<Products>("Nom", "name", Products.class);
		StringEditableColumn<Products> productDesc = new StringEditableColumn<Products>("Description", "description", Products.class);
		
		TableColumn<Products, BigDecimal> productPriceDf = new TableColumn<Products, BigDecimal>("Prix - Hors taxes");
		TableColumn<Products, Vat> productVat = new TableColumn<Products, Vat>("TVA");
		TableColumn<Products, Boolean> productVisible = new TableColumn<Products, Boolean>("Visibilité");
		BooleanColumn<Products> productVisibleWeb = new BooleanColumn<Products>("Web", "referencedWeb",Products.class);
		BooleanColumn<Products> productVisibleSeller = new BooleanColumn<Products>("Vendeur", "referencedSellers", Products.class);
		TableColumn<Products, Categories> productCategory = new TableColumn<Products, Categories>("Catégorie");

		TableColumn<Products, Void> productStock = new TableColumn<Products, Void>("Stocks");

		
		productStock.setCellFactory(new Callback<TableColumn<Products,Void>, TableCell<Products,Void>>() {
			@Override
			public TableCell<Products, Void> call(TableColumn<Products, Void> param) {
				final TableCell<Products, Void> cell = new TableCell<Products, Void>(){
						private Button stockBtn;
					{
						setEditable(false);
						stockBtn = new Button("Stocks");
						stockBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								popupStocks.display(getTableView().getItems().get(getIndex()));
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(stockBtn);
						}
					}
				};
				return cell;
			}
		});
		
		productPriceDf.setCellValueFactory(new PropertyValueFactory<>("priceDf"));
		productVat.setCellValueFactory(new PropertyValueFactory<>("vat"));
		productCategory.setCellValueFactory(new PropertyValueFactory<>("categories"));

		productVisible.getColumns().setAll(productVisibleSeller, productVisibleWeb);

		productPriceDf.setCellFactory(TextFieldTableCell.<Products, BigDecimal>forTableColumn(new BigDecimalStringConverter()));
		productPriceDf.setOnEditCommit((TableColumn.CellEditEvent<Products, BigDecimal> t) -> {
			t.getRowValue().setPriceDf(t.getNewValue());
			dao.update(t.getRowValue());
		});

		
		table.getColumns().setAll(productName, productCategory, productDesc, productPriceDf, productVat, productVisible, productStock, new DeleteColumn<Products>(Products.class));
		table.setEditable(true);
		
		
		fieldName.setPromptText("Nom");
		fieldDesc.setPromptText("Description");
		fieldPrice.setPromptText("Prix");
		choixVAT.getItems().setAll(VatDAO.getInstance().listAll());

		HBox addProductZone = new HBox();
		addProductZone.getChildren().setAll(fieldName, fieldDesc, fieldPrice, choixVAT, addButton);

		view.getChildren().setAll(table, addProductZone);
	}
	
	public void initEvents() {
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Products p = new Products(fieldName.getText(), fieldDesc.getText(), new BigDecimal(fieldPrice.getText()), true, true, choixVAT.getValue(), currentCategory);
				dao.save(p);
				listProducts.add(p);
				table.getItems().add(p);
			}
		});
	}
	
	public ProductsDAO getDAO() {
		return dao;
	}
	
	public void update() {
		if (currentCategory == null) table.setItems(FXCollections.observableArrayList(listProducts));
		else table.setItems(FXCollections.observableArrayList(currentCategory.getProductses()));
	}

	@Override
	public VBox chargerMenu() {
		return view;
	}

	public Categories getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(Categories currentCategory) {
		this.currentCategory = currentCategory;
		update();
	}


	public TableView<Products> getTable() {
		return table;
	}
}
