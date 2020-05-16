package application.objects;


public class StockId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int idStore;
	private int idProduct;

	public StockId() {
	}

	public StockId(int idStore, int idProduct) {
		this.idStore = idStore;
		this.idProduct = idProduct;
	}

	public int getIdStore() {
		return this.idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StockId))
			return false;
		StockId castOther = (StockId) other;

		return (this.getIdStore() == castOther.getIdStore()) && (this.getIdProduct() == castOther.getIdProduct());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdStore();
		result = 37 * result + this.getIdProduct();
		return result;
	}

}
