package controller.menuResponsability.element;

import application.objects.ObjectCOR;
import controller.AppController;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class DeleteColumn<T extends ObjectCOR> extends TableColumn<T, Void>{
	
	private Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory;

    public DeleteColumn(Class<? extends ObjectCOR> typeParameterClass) {
	    
    	super("");

		cellFactory = new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
			@SuppressWarnings("unchecked")
			@Override
			public TableCell<T, Void> call(TableColumn<T, Void> param) {
				final TableCell<T, Void> cell = new TableCell<T, Void>() {
					private final Button btn = new Button("Supprimer");
					{
						btn.setOnMouseClicked((MouseEvent t) -> {
							T addr = getTableView().getItems().get(getIndex());
							AppController.getInstance().getDAO(typeParameterClass).delete(addr);
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		
		setCellFactory(cellFactory);
	    
    }
    

	
	
	
}
