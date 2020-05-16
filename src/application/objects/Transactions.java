package application.objects;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Transactions extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Customers customers;
	private Sellers seller = null;
	private Date date;
	private BigDecimal payedAmount = new BigDecimal(0);
	private Set<ProductsTransactionsHistories> productsTransactionsHistorieses = new HashSet<ProductsTransactionsHistories>(0);
	
	public Transactions() {
	}

	public Transactions(Customers customers, Sellers seller, BigDecimal payedAmount) {
		this.customers = customers;
		this.seller = seller;
		this.payedAmount = payedAmount;
	}
	
	public Transactions(Customers customers, Sellers seller, Date date, BigDecimal payedAmount) {
		this.customers = customers;
		this.seller = seller;
		this.date = date;
		this.payedAmount = payedAmount;
	}
	
	public Transactions(Sellers seller, Date date, BigDecimal payedAmount) {
		this.seller = seller;
		this.date = date;
		this.payedAmount = payedAmount;
	}

	public Transactions(Sellers seller, Date d, BigDecimal payedAmount, Set<ProductsTransactionsHistories> productsTransactionsHistorieses) {
		this.seller = seller;
		this.date = d;
		this.payedAmount = payedAmount;
		this.productsTransactionsHistorieses = productsTransactionsHistorieses;
	}
	
	@Override
	public String toString() {
		String str ="Transaction [" + "Client : " + customers + " - Vendeur : "+ seller + " - Date : "+date + "]\nProduits : \n";
		for(ProductsTransactionsHistories item : productsTransactionsHistorieses){
			str += item.toString();
		}
		return str;
		}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public Sellers getSeller() {
		return this.seller;
	}

	public void setSeller(Sellers seller) {
		this.seller = seller;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPayedAmount() {
		return this.payedAmount;
	}

	public void setPayedAmount(BigDecimal payedAmount) {
		this.payedAmount = payedAmount;
	}

	public Set<ProductsTransactionsHistories> getProductsTransactionsHistorieses() {
		return this.productsTransactionsHistorieses;
	}

	public void setProductsTransactionsHistorieses(Set<ProductsTransactionsHistories> productsTransactionsHistorieses) {
		this.productsTransactionsHistorieses = productsTransactionsHistorieses;
		Iterator<ProductsTransactionsHistories> it = productsTransactionsHistorieses.iterator();
		while(it.hasNext()) {
			it.next().setTransaction(this);
			//	session.persist(it.next());				
		}
	}

}
