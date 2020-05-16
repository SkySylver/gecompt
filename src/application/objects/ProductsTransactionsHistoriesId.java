package application.objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductsTransactionsHistoriesId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idProduct;
	private int idTransaction;

	public ProductsTransactionsHistoriesId() {
	}

	public ProductsTransactionsHistoriesId(int idProduct, int idTransaction) {
		this.idProduct = idProduct;
		this.idTransaction = idTransaction;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdTransaction() {
		return this.idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProductsTransactionsHistoriesId))
			return false;
		ProductsTransactionsHistoriesId castOther = (ProductsTransactionsHistoriesId) other;

		return (this.getIdProduct() == castOther.getIdProduct())
				&& (this.getIdTransaction() == castOther.getIdTransaction());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdProduct();
		result = 37 * result + this.getIdTransaction();
		return result;
	}

}
