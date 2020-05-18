package controller.menuResponsability.popup;

import application.objects.Products;
import application.objects.ProductsTransactionsHistories;
import application.objects.Transactions;
import controller.menuResponsability.element.IntegerEditableColumn;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PopupDetailsTransaction {
	private SplitPane view = new SplitPane();
	private Stage popup;

	private TableView<ProductsTransactionsHistories> table;

	public PopupDetailsTransaction() {
		table = new TableView<ProductsTransactionsHistories>();
		popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Détails commande ");

		initView();
	}

	@SuppressWarnings("unchecked")
	private void initView() {
		TableColumn<ProductsTransactionsHistories, Products> columnProduct = new TableColumn<ProductsTransactionsHistories, Products>(
				"Produit");
		


		IntegerEditableColumn<ProductsTransactionsHistories> columnAmount = new IntegerEditableColumn<ProductsTransactionsHistories>("Quantité", "amount", ProductsTransactionsHistories.class);
		
		columnProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
		columnProduct.setCellFactory(
				new Callback<TableColumn<ProductsTransactionsHistories, Products>, TableCell<ProductsTransactionsHistories, Products>>() {

					@Override
					public TableCell<ProductsTransactionsHistories, Products> call(
							TableColumn<ProductsTransactionsHistories, Products> param) {

						TableCell<ProductsTransactionsHistories, Products> cell = new TableCell<ProductsTransactionsHistories, Products>() {

							@Override
							protected void updateItem(Products item, boolean empty) {
								super.updateItem(item, empty);
								setGraphic(null);
								if (item != null)
									setText(item.getName());
									
								else
									setText(null);
							}
						};
						return cell;
					}
				});


		table.getColumns().setAll(columnProduct, columnAmount);
		view.getItems().setAll(table);
		popup.setScene(new Scene(view));
	}

	public void display(Transactions t) {

		popup.setTitle("Détail commande n°" + t.getId());
		table.setItems(FXCollections.observableArrayList(t.getProductsTransactionsHistorieses()));

		popup.showAndWait();

	}
}
