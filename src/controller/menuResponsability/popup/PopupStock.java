package controller.menuResponsability.popup;

import application.objects.Products;
import application.objects.Stock;
import application.objects.Stores;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PopupStock {
	private Stage popup;

	private TableView<Stock> table;

	public PopupStock() {
		popup = new Stage();
//		popup.setScene(new Scene(expert.chargerMenu()));
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Stocks produit");

		initView();
	}

	@SuppressWarnings("unchecked")
	private void initView() {
		table = new TableView<Stock>();

		TableColumn<Stock, Stores> stockStore = new TableColumn<Stock, Stores>("Magasin");
		TableColumn<Stock, Integer> stockAmount = new TableColumn<Stock, Integer>("Quantité");


		stockStore.setCellFactory(new Callback<TableColumn<Stock, Stores>, TableCell<Stock, Stores>>() {

			@Override
			public TableCell<Stock, Stores> call(TableColumn<Stock, Stores> param) {

				final TableCell<Stock, Stores> cell = new TableCell<Stock, Stores>(){
					{
						this.setEditable(false);
					}

					@Override
					protected void updateItem(Stores item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty && item!=null) {
							this.setText(item.getName());
						}
					}
				};
				
				return cell;
			}
		});

		table.getColumns().setAll(stockStore, stockAmount);
		table.setEditable(true);
		popup.setScene(new Scene(table));
	}

	public void display(Products p) {
		table.setItems(FXCollections.observableArrayList(p.getStocks()));

		popup.showAndWait();

	}

}
