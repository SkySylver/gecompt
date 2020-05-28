package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.ObjectCOR;
import application.objects.Sellers;
import controller.AppController;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class SellersColumn<T extends ObjectCOR> extends TableColumn<T, Sellers> {

	@SuppressWarnings("unchecked")
	public SellersColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);
		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		this.setOnEditCommit((TableColumn.CellEditEvent<T, Sellers> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});

		setCellFactory(new Callback<TableColumn<T, Sellers>, TableCell<T, Sellers>>() {
			@Override
			public TableCell<T, Sellers> call(TableColumn<T, Sellers> param) {
				return new TableCell<T, Sellers>() {
					{
						this.setEditable(true);
						this.setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					public void startEdit() {
						super.startEdit();
//						commitEdit(PopupCustomers.getInstance().display());
					}

					@Override
					protected void updateItem(Sellers item, boolean empty) {
						super.updateItem(item, empty);
						super.setGraphic(null);
						if (item == null)
							super.setText(null);
						else
							super.setText(item.toString());
					}
				};
			}
		});

	}
}