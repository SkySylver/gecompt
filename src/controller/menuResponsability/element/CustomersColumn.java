package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.Customers;
import application.objects.ObjectCOR;
import controller.AppController;
import controller.menuResponsability.popup.PopupCustomers;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CustomersColumn<T extends ObjectCOR> extends TableColumn<T, Customers> {

	@SuppressWarnings("unchecked")
	public CustomersColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);
		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		this.setOnEditCommit((TableColumn.CellEditEvent<T, Customers> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});

		setCellFactory(new Callback<TableColumn<T, Customers>, TableCell<T, Customers>>() {
			@Override
			public TableCell<T, Customers> call(TableColumn<T, Customers> param) {
				TableCell<T, Customers> cell = new TableCell<T, Customers>() {
					{
						this.setEditable(true);
						this.setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					public void startEdit() {
						super.startEdit();
						commitEdit(PopupCustomers.getInstance().display());
					}

					@Override
					protected void updateItem(Customers item, boolean empty) {
						super.updateItem(item, empty);
						super.setGraphic(null);
						if (item == null)
							super.setText(null);
						else
							super.setText(item.toString());
					}

				};
				return cell;
			}
		});

	}
}