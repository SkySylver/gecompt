package controller.menuResponsability.element;

import org.apache.commons.beanutils.PropertyUtils;

import application.objects.ObjectCOR;
import controller.AppController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BooleanColumn<T extends ObjectCOR> extends TableColumn<T, Boolean> {
	private Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>> cellFactory;

    public BooleanColumn(String displayName, String propertyName, Class<? extends ObjectCOR> typeParameterClass) {
		super(displayName);

		setCellValueFactory(new PropertyValueFactory<>(propertyName));

		setOnEditCommit((TableColumn.CellEditEvent<T, Boolean> t) ->{
			try {
				PropertyUtils.setProperty(t.getRowValue(), propertyName, t.getNewValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			AppController.getInstance().getDAO(typeParameterClass).update(t.getRowValue());
		});
		
		
		cellFactory = new Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>>() {
			@Override
			public TableCell<T, Boolean> call(TableColumn<T, Boolean> param) {
				final TableCell<T, Boolean> cell = new TableCell<T, Boolean>() {
					private CheckBox checkBox;
					{
						checkBox = new CheckBox();
						checkBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
							@Override
			                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			                    if(isEditing())
			                        commitEdit(newValue == null ? false : newValue);
			                }
			            });
						checkBox.setDisable(true);
						
						this.setGraphic(checkBox);
						this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						this.setEditable(true);
					}

					@Override
					public void startEdit() {
						super.startEdit();
						if (isEmpty()) {
							return;
						}
						checkBox.setDisable(false);
						checkBox.requestFocus();
					}

					@Override
					public void cancelEdit() {
						super.cancelEdit();
						checkBox.setDisable(true);
					}

			        public void commitEdit(Boolean value) {
			            super.commitEdit(value);
			            checkBox.setDisable(true);
			        }
					
					@Override
					public void updateItem(Boolean item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							checkBox.setSelected(item);
						}
						else setGraphic(null);
					}
				};
				return cell;
			}
		};

	setCellFactory(cellFactory);
	}
}
