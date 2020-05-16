package application.objects;

public class ProductsTransactionsHistories extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductsTransactionsHistoriesId id = new ProductsTransactionsHistoriesId();
	private Products product;
	private Transactions transaction;
	private int amount;

	public ProductsTransactionsHistories() {
	}
	
	public ProductsTransactionsHistories(Products product, int amount) {
		setProduct(product);
		setAmount(amount);
	}
	
	public ProductsTransactionsHistories(Transactions transaction, Products product, int amount) {
		setProduct(product);
		setTransaction(transaction);
		setAmount(amount);
	}
	
	@Override
	public String toString() {
		return product.toString() + " - " + amount;
	}


	public ProductsTransactionsHistoriesId getId() {
		return this.id;
	}

	public void setId(ProductsTransactionsHistoriesId id) {
		this.id = id;
	}

	public void setProduct(Products p) {
	//	System.out.println(p);
		this.product = p;
		setProductsHistories(p);
	}

	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
		setTransactionHistories(transaction);
	}
	
	public Products getProduct() {
		return this.product;
	}

	public void setProductsHistories(Products product) {
		id.setIdProduct(product.getProductId());
	}

	public void setTransactionHistories(Transactions t) {
		id.setIdTransaction(t.getId());
	}
	
	public Transactions getTransaction() {
		return this.transaction;
	}



	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
