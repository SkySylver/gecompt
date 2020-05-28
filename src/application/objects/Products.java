package application.objects;

import java.math.BigDecimal;
import java.util.Set;

public class Products extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int productId = 0;
	private Categories categories;
	private Vat vat;
	private String name;
	private String description;
	private BigDecimal priceDf;
	private boolean referencedWeb;
	private boolean referencedSellers;
	double value;
	private Set<Stock> stocks;
	public Products() {
	}

	public Products(String name, String description, BigDecimal priceDf, boolean referencedWeb, boolean referencedSellers) {
		this.name = name;
		this.description = description;
		this.priceDf = priceDf;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
	}
	public Products(String name, String description, BigDecimal priceDf, boolean referencedWeb, boolean referencedSellers, Vat vat) {
		this.name = name;
		this.description = description;
		this.priceDf = priceDf;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
		this.vat = vat;
	}
	public Products(String name, String description, BigDecimal priceDf, boolean referencedWeb, boolean referencedSellers, Vat vat, Categories category) {
		this.name = name;
		this.description = description;
		this.priceDf = priceDf;
		this.categories = category;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
		this.vat = vat;
	}
	public Products(Categories categories, Vat vat, String name, String description, BigDecimal priceDf,
			boolean referencedWeb, boolean referencedSellers) {
		this.categories = categories;
		this.vat = vat;
		this.name = name;
		this.description = description;
		this.priceDf = priceDf;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(!(obj instanceof Products)) return false;
		Products prod = (Products) obj;
		if(this.productId != prod.productId) return false;
		if(!this.name.equals(prod.name)) return false;
		if(!this.description.equals(prod.description)) return false;
		if(!this.priceDf.equals(prod.priceDf)) return false;
		if(this.referencedWeb != prod.referencedWeb) return false;
		if(this.value != prod.value) return false;
		
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Products [id=" + productId + ", name=" + name + ", description=" + description + ", category :" + categories +"]";
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Vat getVat() {
		return this.vat;
	}

	public void setVat(Vat vat) {
		this.vat = vat;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPriceDf() {
		return this.priceDf;
	}

	public void setPriceDf(BigDecimal priceDf) {
		this.priceDf = priceDf;
	}

	public boolean isReferencedWeb() {
		return this.referencedWeb;
	}

	public void setReferencedWeb(boolean referencedWeb) {
		this.referencedWeb = referencedWeb;
	}

	public boolean isReferencedSellers() {
		return this.referencedSellers;
	}

	public void setReferencedSellers(boolean referencedSellers) {
		this.referencedSellers = referencedSellers;
	}

	public Set<Stock> getStocks() { 
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

}
