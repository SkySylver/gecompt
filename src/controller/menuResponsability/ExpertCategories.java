package controller.menuResponsability;

import java.util.List;

import application.objects.Categories;
import controller.menuResponsability.element.BooleanColumn;
import controller.menuResponsability.element.CategoryColumn;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.StringEditableColumn;
import controller.menuResponsability.popup.PopupGetCategory;
import database.dao.CategoriesDAO;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExpertCategories extends ExpertCOR {

	private VBox view = new VBox();

	private List<Categories> listCat;

	private CategoriesDAO dao = CategoriesDAO.getInstance();
	private TableView<Categories> table = new TableView<Categories>();

	private TextField categoryNameField = new TextField();
	private Button addCategory = new Button("Ajouter");
	private CheckBox checkReferencedWeb = new CheckBox("Visible web");
	private CheckBox checkReferencedSellers = new CheckBox("Visible vendeurs");
	private Button choiceSuperCategory = new Button("Choisir super catégorie");

	private Categories selectedCat;

	private HBox addZone = new HBox();

	private Categories currentCategory = null;

	public ExpertCategories(ExpertCOR n) {

		super(n);
		value = "Cats";

		initView();
		initEvents();
		initCss();
		
		initAddZone();
		
		table.setItems(FXCollections.observableArrayList(dao.listAll()));

		view.getChildren().setAll(table, addZone);
	}

	@SuppressWarnings("unchecked")
	protected void initView() {
		StringEditableColumn<Categories> categoriesName = new StringEditableColumn<Categories>("Nom", "name",Categories.class);
		BooleanColumn<Categories> categoriesReferenced = new BooleanColumn<Categories>("Visible", "", Categories.class);
		BooleanColumn<Categories> categoriesReferencedWeb = new BooleanColumn<Categories>("Web", "referencedWeb", Categories.class);
		BooleanColumn<Categories> categoriesReferencedSellers = new BooleanColumn<Categories>("Vendeur", "referencedSellers", Categories.class);

		categoriesReferenced.getColumns().addAll(categoriesReferencedSellers, categoriesReferencedWeb);

		CategoryColumn<Categories> categoriesSuper = new CategoryColumn<Categories>("Super catégorie", "categories", Categories.class);
		table.getColumns().addAll(categoriesName, categoriesSuper, categoriesReferenced, new DeleteColumn<Categories>(Categories.class));
		table.setEditable(true);
	}
	
	private void initCss() {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		addZone.getStyleClass().add("addZone");
	}
	
	private void initAddZone() {

		addZone.getChildren().setAll(categoryNameField, choiceSuperCategory, checkReferencedWeb, checkReferencedSellers, addCategory);
	}
	
	private void initEvents() {
		addCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Categories tempcat = new Categories(categoryNameField.getText(), selectedCat,
						checkReferencedWeb.isSelected(), checkReferencedSellers.isSelected());
				selectedCat.getCategorieses().add(tempcat);
				dao.save(tempcat);
			}
		});

		choiceSuperCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectedCat = PopupGetCategory.getInstance(new ExpertCategories(null)).display();
			}
		});
	}

	public TableView<Categories> getTable() {
		return table;
	}

	@Override
	public VBox chargerMenu() {
		return view;
	}

	public Categories getCurrentCategory() {
		return currentCategory;
	}

	private void update() {
		if (currentCategory == null)
			table.setItems(FXCollections.observableArrayList(listCat));
		else {
			table.setItems(FXCollections.observableArrayList(currentCategory.getCategorieses()));
		}
	}

	public void setCurrentCategory(Categories currentCategory) {
		try {
			this.currentCategory = currentCategory;
			update();
		} catch (Exception e) {
			System.err.println("Impossible de mettre à jour la catégorie actuelle");
		}
	}
	

	public VBox getView() {
		return view;
	}
}
