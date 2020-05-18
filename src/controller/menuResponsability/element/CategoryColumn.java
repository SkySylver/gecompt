package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.Categories;
import application.objects.ObjectCOR;
import controller.AppController;
import controller.menuResponsability.popup.PopupCategories;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CategoryColumn<T extends ObjectCOR> extends TableColumn<T, Categories> {


	public CategoryColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);

		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		this.setOnEditCommit((TableColumn.CellEditEvent<T, Categories> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});

		setCellFactory(new Callback<TableColumn<T, Categories>, TableCell<T, Categories>>() {
			@Override
			public TableCell<T, Categories> call(TableColumn<T, Categories> param) {

				return new TableCell<T, Categories>() {
					{
						this.setEditable(true);
						this.setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					public void startEdit() {
						super.startEdit();
						commitEdit(PopupCategories.getInstance().display());
					}


					@Override
					protected void updateItem(Categories item, boolean empty) {
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
