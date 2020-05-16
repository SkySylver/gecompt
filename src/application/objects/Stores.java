package application.objects;

import java.util.HashSet;
import java.util.Set;

public class Stores extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Addresses addresses;
	private String name;
	private Set<Sellers> sellerses = new HashSet<Sellers>(0);

	public Stores() {	
	}
	
	public Stores(String name, Addresses addresses) {
		this.addresses = addresses;
		this.name = name;
	}

	public Stores(int id, Addresses addresses, String name) {
		this.id = id;
		this.addresses = addresses;
		this.name = name;
	}

	public Stores(int id, Addresses addresses, String name, Set<Sellers> sellerses) {
		this.id = id;
		this.addresses = addresses;
		this.name = name;
		this.sellerses = sellerses;
	}

	
	@Override
	public String toString() {
		return name;
	}
	
	
	// Getters / Setters
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Addresses getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Sellers> getSellerses() {
		return this.sellerses;
	}

	public void setSellerses(Set<Sellers> sellerses) {
		this.sellerses = sellerses;
	}

}
