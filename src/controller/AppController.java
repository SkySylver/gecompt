package controller;

import application.objects.ObjectCOR;
import controller.menuResponsability.ExpertAddresses;
import controller.menuResponsability.ExpertCOR;
import controller.menuResponsability.ExpertCategoriesProducts;
import controller.menuResponsability.ExpertCustomers;
import controller.menuResponsability.ExpertSellers;
import controller.menuResponsability.ExpertStores;
import controller.menuResponsability.ExpertTransactions;
import database.HibernateUtil;
import database.dao.DaoCOR;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class AppController extends AbstractController{

	private ExpertCOR menuExpert;
	
	private Node midNode;
	private Node rightNode;
	
	@FXML
	private SplitPane sp;
	
	@FXML
	private ListView<String> menu;
	
	
	public void init() {
		stage.setTitle("Digicom - Comptabilité");

		initExperts();
		initActions();
		initCss();
		
		menu.getItems().setAll(menuExpert.getAllValues());
		menu.getStyleClass().add("menu");
		
		sp.getItems().setAll(menu);
		
	}
	
	private void initCss() {
		sp.getStyleClass().add("view");
		//table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	
	public void initExperts() {
		menuExpert = new ExpertTransactions(new ExpertCategoriesProducts(new ExpertSellers(new ExpertCustomers(new ExpertStores(new ExpertAddresses(null))))));
	}
	
	public void initActions() {
		menu.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				menuExpert.visite(menu.getSelectionModel().getSelectedItem());
			}
		});
	}
	
	private void updateView() {
		if(midNode == null) sp.getItems().setAll(menu);
		else if(rightNode == null) sp.getItems().setAll(menu, midNode);
		else sp.getItems().setAll(menu, midNode, rightNode);
	}

	
	
	
		
	// Singleton
	private static AppController instance = new AppController();

	private AppController() {
		super();
	}
	
	

	// Getters / Setters

	@SuppressWarnings("rawtypes")
	public DaoCOR getDAO(Class<? extends ObjectCOR> typeParameterClass) {
		return HibernateUtil.getInstance().getDAO(typeParameterClass);
	}
	
	public ListView<String> getMenu(){
		return menu;
	}

	
	public SplitPane getSplitPane() {
		return sp;
	}
	
	public static AppController getInstance() {
		return instance;
	}

	public Node getMidNode() {
		return midNode;
	}

	public void setMid(Node mid) {
		
		this.midNode = mid;
		updateView();
	}

	public Node getNodeRight() {
		return rightNode;
	}

	public void setRight(Pane right) {
		this.rightNode = right;
		updateView();
	}
}
