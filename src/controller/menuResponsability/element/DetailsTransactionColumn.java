package controller.menuResponsability.element;


import application.objects.Transactions;
import controller.menuResponsability.popup.PopupDetailsTransaction;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class DetailsTransactionColumn extends TableColumn<Transactions, Void> {

	PopupDetailsTransaction popupDetails = new PopupDetailsTransaction();
	
	public DetailsTransactionColumn() {
		super("Détails");
		setCellFactory(new Callback<TableColumn<Transactions, Void>, TableCell<Transactions, Void>>() {
			@Override
			public TableCell<Transactions, Void> call(TableColumn<Transactions, Void> param) {
				final TableCell<Transactions, Void> cell = new TableCell<Transactions, Void>() {
					private Button detailsBtn;
					{
						setEditable(false);
						detailsBtn = new Button("+ Détails");
						detailsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								popupDetails.display(getTableView().getItems().get(getIndex()));
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(detailsBtn);
						}
					}
				};
				return cell;
			}
		});

	}
}
