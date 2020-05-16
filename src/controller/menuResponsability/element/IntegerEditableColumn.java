package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;


import application.objects.ObjectCOR;
import controller.AppController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class IntegerEditableColumn<T extends ObjectCOR> extends TableColumn<T, Integer>  {
	
	public IntegerEditableColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);

		this.setEditable(true);

		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		
		setOnEditCommit((TableColumn.CellEditEvent<T, Integer> t) ->{
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});
		
		setCellFactory(TextFieldTableCell.<T, Integer>forTableColumn(new IntegerStringConverter()));

	}


}
