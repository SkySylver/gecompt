package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.ObjectCOR;
import controller.AppController;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenderColumn <T extends ObjectCOR> extends TableColumn<T, Boolean> {

	@SuppressWarnings("unchecked")
	public GenderColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);
		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		this.setOnEditCommit((TableColumn.CellEditEvent<T, Boolean> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});

	setCellFactory(tc -> new TableCell<T, Boolean>(){
		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : item.booleanValue() ? "Monsieur" : "Madame");
		}
	});
}
}
