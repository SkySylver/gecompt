package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.Addresses;
import application.objects.ObjectCOR;
import controller.AppController;
import controller.menuResponsability.popup.PopupAddress;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AddressColumn<T extends ObjectCOR> extends TableColumn<T, Addresses> {

	@SuppressWarnings("unchecked")
	public AddressColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);

		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		this.setOnEditCommit((TableColumn.CellEditEvent<T, Addresses> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});

		setCellFactory(new Callback<TableColumn<T, Addresses>, TableCell<T, Addresses>>() {
			@Override
			public TableCell<T, Addresses> call(TableColumn<T, Addresses> param) {

				return new TableCell<T, Addresses>() {
					{
						this.setEditable(true);
						this.setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					public void startEdit() {
						super.startEdit();
						commitEdit(PopupAddress.getInstance().display());
					}


					@Override
					protected void updateItem(Addresses item, boolean empty) {
						super.updateItem(item, empty);
						super.setGraphic(null);
						if (item == null) super.setText(null);
						else super.setText(item.toString());
					}
				};
			}
		});
	}

}
