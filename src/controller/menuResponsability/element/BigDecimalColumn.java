package controller.menuResponsability.element;

import java.math.BigDecimal;
import org.apache.commons.beanutils.PropertyUtils;

import application.objects.ObjectCOR;
import controller.AppController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;

public class BigDecimalColumn<T extends ObjectCOR> extends TableColumn<T, BigDecimal> {

	@SuppressWarnings("unchecked")
	public BigDecimalColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);
		this.setEditable(true);

		setCellFactory(TextFieldTableCell.<T, BigDecimal>forTableColumn(new BigDecimalStringConverter()));
		
		this.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		
		
		this.setOnEditCommit((TableColumn.CellEditEvent<T, BigDecimal> t) -> {
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});
	}
}