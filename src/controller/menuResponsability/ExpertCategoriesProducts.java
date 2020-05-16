package controller.menuResponsability;

import application.objects.Categories;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ExpertCategoriesProducts extends ExpertCOR{
	private ExpertCategories categories;
	private ExpertProducts products;
	private SplitPane view = new SplitPane();
	
	Button homeBtn ;
	Button backBtn ;
	
	public ExpertCategoriesProducts(ExpertCOR next) {
		super(next);
		
		value = "Produits";
		categories = new ExpertCategories(null);
		products = new ExpertProducts(null);

		initView();
		initEvents();
	}
	
	
	private void initView() {
		homeBtn = new Button("", new ImageView(new Image("/view/homeIcon.png", 16, 16, true, false)));
		backBtn = new Button("", new ImageView(new Image("/view/BackArrowIcon.png", 16, 16, true, false)));

		categories.getView().getChildren().add(0, new HBox(homeBtn, backBtn));
		view.getItems().setAll(categories.chargerMenu(), products.chargerMenu());
	}

	
	private void initEvents() {
		homeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				products.setCurrentCategory(null);
				categories.setCurrentCategory(null);
			}
		});
		
		backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Categories temp = categories.getCurrentCategory();
				if(temp == null) {
					products.setCurrentCategory(null);
					categories.setCurrentCategory(null);
				}
				else {
					products.setCurrentCategory(temp);
					categories.setCurrentCategory(temp);					
				}				
			}
		});
		
		categories.getTable().setRowFactory(tr -> {
		    TableRow<Categories> row = new TableRow<Categories>();
		    row.setOnMouseClicked(event -> {
		        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) 
		        	products.setCurrentCategory(row.getItem());
		        	if(row.getItem()!=null) categories.setCurrentCategory(row.getItem().getCategories());
		    });
		    return row;
		});
	}

	
	@Override
	public Node chargerMenu() {
		return view;
	}
}
