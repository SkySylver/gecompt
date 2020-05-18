package controller.menuResponsability;

import java.util.Date;
import java.util.List;

import application.objects.Transactions;
import controller.menuResponsability.element.BigDecimalColumn;
import controller.menuResponsability.element.CustomersColumn;
import controller.menuResponsability.element.DeleteColumn;
import controller.menuResponsability.element.DetailsTransactionColumn;
import controller.menuResponsability.element.SellersColumn;
import controller.menuResponsability.popup.PopupAddTransaction;
import database.dao.TransactionsDAO;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ExpertTransactions extends ExpertCOR {

	private TransactionsDAO DAO = TransactionsDAO.getInstance();
	private ExpertTransactions expp = this;
	
	private SplitPane view = new SplitPane();
	
	private TableView<Transactions> table = new TableView<Transactions>();
	private List<Transactions> listTransactions = DAO.listAll();
	private Button createTransaction = new Button("Créer facture");
	
	
	private VBox recap;
	
	
	public ExpertTransactions(ExpertCOR n) {
		super(n);
		value = "Factures";
		initView();
		initEvents();
		initCss();
		table.setItems(FXCollections.observableArrayList(listTransactions));
	}


	@SuppressWarnings("unchecked")
	protected void initView() {

		TableColumn<Transactions, Integer> transactionId = new TableColumn<Transactions, Integer>("Numéro facture");
		CustomersColumn<Transactions> transactionCustomer = new CustomersColumn<Transactions>("Client", "customers", Transactions.class);
		BigDecimalColumn<Transactions> transactionPayed = new BigDecimalColumn<Transactions>("Somme payée", "payedAmount", Transactions.class);
		SellersColumn<Transactions> transactionSeller = new SellersColumn<Transactions>("Vendeur", "seller", Transactions.class);
		
		TableColumn<Transactions, Date> transactionDate = new TableColumn<Transactions, Date>("Date");
		
		transactionId.setCellValueFactory(new PropertyValueFactory<>("id"));
		transactionDate.setCellValueFactory(new PropertyValueFactory<>("date"));


		table.getColumns().setAll(transactionId, transactionCustomer, transactionSeller, transactionDate, transactionPayed, new DetailsTransactionColumn(), new DeleteColumn<Transactions>(Transactions.class));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		recap = new VBox(table, createTransaction);

		resetView();
	}

	private void initCss() {
		table.getStyleClass().add("table");
		view.getStyleClass().add("view");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	protected void initEvents() {
		createTransaction.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				view.getItems().setAll(new PopupAddTransaction(expp).display());
			}
		});
	}

	@Override
	public SplitPane chargerMenu() {
		return view;
	}
	
	public void resetView() {
		view.getItems().setAll(recap);
	}
}