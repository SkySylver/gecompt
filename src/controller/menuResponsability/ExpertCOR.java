package controller.menuResponsability;

import java.util.ArrayList;

import controller.AppController;
import javafx.scene.Node;

public abstract class ExpertCOR {
	protected ExpertCOR next;
	protected String value;
	
	public ExpertCOR(ExpertCOR n) {
		next = n;
	}
	
	public void visite(String element){
		if(value == element) {
			AppController.getInstance().setMid(chargerMenu());
		}else if(next != null) {
			next.visite(element);
		}
	}
	

	public ArrayList<String> getAllValues(){
		if(next !=null) {
			ArrayList<String> temp = next.getAllValues();
			temp.add(value);
			return temp;
		}
		else {
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(value);
			return arr;
		}
	}
	
	public abstract Node chargerMenu();
	
}
