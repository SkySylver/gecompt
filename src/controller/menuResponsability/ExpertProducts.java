package controller.menuResponsability;

import java.math.BigDecimal;
import java.util.List;

import application.objects.Categories;
import application.objects.Products;
import application.objects.Vat;
import controller.menuResponsability.element.BigDecimalColumn;
import controller.menuResponsability.element.BooleanColumn;
import controller.menuResponsability.element.CategoryColumn;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
		initAddZone();
		initCss();
		update();
	}

	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Products> productName = new StringEditableColumn<Products>("Nom", "name", Products.class);
		StringEditableColumn<Products> productDesc = new StringEditableColumn<Products>("Description", "description",
				Products.class);

		BigDecimalColumn<Products> productPriceDf = new BigDecimalColumn<Products>("Prix - Hors taxes", "priceDf",
				Products.class);
		TableColumn<Products, Vat> productVat = new TableColumn<Products, Vat>("TVA");
		TableColumn<Products, Boolean> productVisible = new TableColumn<Products, Boolean>("Visibilité");
		BooleanColumn<Products> productVisibleWeb = new BooleanColumn<Products>("Web", "referencedWeb", Products.class);
		BooleanColumn<Products> productVisibleSeller = new BooleanColumn<Products>("Vendeur", "referencedSellers",
				Products.class);
		CategoryColumn<Products> productCategory = new CategoryColumn<Products>("Catégorie", "categories", Products.class);

		TableColumn<Products, Void> productStock = new TableColumn<Products, Void>("Stocks");

		productStock.setCellFactory(new Callback<TableColumn<Products, Void>, TableCell<Products, Void>>() {
			@Override
			public TableCell<Products, Void> call(TableColumn<Products, Void> param) {
				final TableCell<Products, Void> cell = new TableCell<Products, Void>() {
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

		productVat.setCellValueFactory(new PropertyValueFactory<>("vat"));

		productVisible.getColumns().setAll(productVisibleSeller, productVisibleWeb);

		table.getColumns().setAll(productName, productCategory, productDesc, productPriceDf, productVat, productVisible, productStock, new DeleteColumn<Products>(Products.class));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);

		view.getChildren().setAll(table);
	}

	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void initAddZone() {
		fieldName.setPromptText("Nom");
		fieldDesc.setPromptText("Description");
		fieldPrice.setPromptText("Prix");
		choixVAT.getItems().setAll(VatDAO.getInstance().listAll());

		HBox addZone = new HBox();
		
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Products p = new Products(fieldName.getText(), fieldDesc.getText(),
						new BigDecimal(fieldPrice.getText()), true, true, choixVAT.getValue(), currentCategory);
				dao.save(p);
				listProducts.add(p);
				table.getItems().add(p);
			}
		});
		
		addZone.getChildren().setAll(fieldName, fieldDesc, fieldPrice, choixVAT, addButton);

		addZone.getStyleClass().add("addZone");
		
		view.getChildren().add(addZone);
		
	}

	public void initEvents() {

	}

	public ProductsDAO getDAO() {
		return dao;
	}

	public void update() {
		if (currentCategory == null)
			table.setItems(FXCollections.observableArrayList(listProducts));
		else
			table.setItems(FXCollections.observableArrayList(currentCategory.getProductses()));
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
