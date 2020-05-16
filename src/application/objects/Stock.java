package application.objects;


public class Stock extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private StockId id;
	private int amount;
	private Products product;
	private Stores store;
	
	
	public Stock() {
	}

	public Stock(StockId id, int amount) {
		this.id = id;
		this.amount = amount;
	}
	
	public Stock(Products product, Stores store, int amount) {
		this.setProduct(product);
		this.setStore(store);
		this.amount = amount;
	}

	public StockId getId() {
		return this.id;
	}

	public void setId(StockId id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

}
