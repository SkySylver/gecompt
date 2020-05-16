package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.ObjectCOR;
import controller.AppController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class StringEditableColumn<T extends ObjectCOR> extends TableColumn<T, String> {

	public StringEditableColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);

		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		
		this.setOnEditCommit((TableColumn.CellEditEvent<T, String> t) ->{
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});
		this.setCellFactory(TextFieldTableCell.forTableColumn());

	}

}
