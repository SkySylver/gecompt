package controller.menuResponsability;

import application.objects.Products;
import javafx.scene.layout.VBox;

public class ExpertStocks extends ExpertCOR{
	private VBox view;
	
	private Products product;
	
	public ExpertStocks(ExpertCOR n) {
		super(n);

	}

	@Override
	public VBox chargerMenu() {
		return view;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

}
