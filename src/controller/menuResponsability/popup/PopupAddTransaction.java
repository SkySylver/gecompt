package controller.menuResponsability.popup;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import application.objects.Customers;
import application.objects.Products;
import application.objects.ProductsTransactionsHistories;
import application.objects.Transactions;
import controller.menuResponsability.ExpertProducts;
import controller.menuResponsability.ExpertTransactions;
import database.HibernateUtil;
import database.dao.SellersDAO;
import database.dao.TransactionsDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class PopupAddTransaction {

	private Label cust = new Label("");
	private Customers customer;
	private Button choiceCustomer = new Button("Choisir");
	private Button addProduct = new Button("Ajouter un produit");
	private Button validate = new Button("Valider la commande");

	private ExpertTransactions expert;
	private Transactions transactions = new Transactions();
	private VBox view = new VBox();

	private TableView<ProductsTransactionsHistories> table = new TableView<ProductsTransactionsHistories>();

	public PopupAddTransaction(ExpertTransactions exp) {
		expert = exp;
		initTable();
		initEvents();
	}

	@SuppressWarnings("unchecked")
	private void initTable() {
		TableColumn<ProductsTransactionsHistories, String> productName = new TableColumn<ProductsTransactionsHistories, String>(
				"Produit");
		TableColumn<ProductsTransactionsHistories, String> productDesc = new TableColumn<ProductsTransactionsHistories, String>(
				"Description");
		TableColumn<ProductsTransactionsHistories, BigDecimal> productPrice = new TableColumn<ProductsTransactionsHistories, BigDecimal>(
				"Prix");
		TableColumn<ProductsTransactionsHistories, Integer> productQuantity = new TableColumn<ProductsTransactionsHistories, Integer>(
				"Quantité");

		productName.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ProductsTransactionsHistories, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ProductsTransactionsHistories, String> param) {
						String temp = new String("");
						if (param.getValue().getProduct() != null)
							temp = param.getValue().getProduct().getName();
						return new SimpleObjectProperty<String>(temp);
					}
				});

		productDesc.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ProductsTransactionsHistories, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ProductsTransactionsHistories, String> param) {
						String temp = new String("");
						if (param.getValue().getProduct() != null)
							temp = param.getValue().getProduct().getDescription();
						return new SimpleObjectProperty<String>(temp);
					}
				});

		productPrice.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ProductsTransactionsHistories, BigDecimal>, ObservableValue<BigDecimal>>() {
					@Override
					public ObservableValue<BigDecimal> call(
							CellDataFeatures<ProductsTransactionsHistories, BigDecimal> param) {
						BigDecimal temp = new BigDecimal("0.00");
						if (param.getValue().getProduct() != null)
							temp = param.getValue().getProduct().getPriceDf();
						return new SimpleObjectProperty<BigDecimal>(temp);
					}
				});


		productQuantity.setCellFactory(
				new Callback<TableColumn<ProductsTransactionsHistories, Integer>, TableCell<ProductsTransactionsHistories, Integer>>() {
					@Override
					public TableCell<ProductsTransactionsHistories, Integer> call(
							TableColumn<ProductsTransactionsHistories, Integer> param) {
						return new TextFieldTableCell<ProductsTransactionsHistories, Integer>();
					}
				});

		productQuantity.setCellFactory(TextFieldTableCell.<ProductsTransactionsHistories, Integer>forTableColumn(new IntegerStringConverter()));

		table.setEditable(true);
		productQuantity.setEditable(true);

		table.getColumns().setAll(productName, productDesc, productPrice, productQuantity);
		view.getChildren().setAll(
				new HBox(new Label("Vendeur : "), new Label(HibernateUtil.getInstance().getUsername())),
				new HBox(new Label("Client : "), cust, choiceCustomer), addProduct, table, validate);

	}

	private void initEvents() {

		addProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Boolean cancel = false;

				Products temp = new PopupAddProduct(new ExpertProducts(null)).display();
				if (temp != null) {
					for (int i = 0; i < table.getItems().size(); i++) {
						if (table.getItems().get(i).getProduct().equals(temp)) {
							cancel = true;
							break;
						}
					}
				} else
					cancel = true;

				if (!cancel)
					table.getItems().add(new ProductsTransactionsHistories(temp, 1));
			}
		});

		choiceCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				customer = PopupCustomers.getInstance().display();
				cust.setText(customer.toString());
				transactions.setCustomers(customer);
			}
		});

		validate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				transactions.setSeller(SellersDAO.getInstance().loadCurrent());
				transactions.setDate(new Date());
				transactions.setPayedAmount(new BigDecimal(0));
				
				TransactionsDAO.getInstance().save(transactions);

				Set<ProductsTransactionsHistories> temp = new HashSet<ProductsTransactionsHistories>();
				table.getItems().forEach((elem) -> {
					if (elem.getProduct() != null)
						temp.add(elem);
				});

				try {
					transactions.setProductsTransactionsHistorieses(temp);
					TransactionsDAO.getInstance().update(transactions);
				} catch (Exception e) {
					System.out.println("Impossible d'ajouter la facture");
					TransactionsDAO.getInstance().delete(transactions);
				}
				expert.resetView();

			}
		});
	}

	public VBox display() {
		return view;
	}
}
