package application.objects;

import java.util.HashSet;
import java.util.Set;

public class Addresses extends ObjectCOR implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int number;
	private String street;
	private String city;
	private Set<Stores> storeses = new HashSet<Stores>(0);
	private Set<Customers> customers= new HashSet<Customers>(0);
	
	@Override
	public String toString() {
		return "" + number + " " + street + " " + city;
	}
	
	public Addresses() {
	}

	public Addresses(int number, String street, String city) {
		this.number = number;
		this.street = street;
		this.city = city;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Stores> getStoreses() {
		return storeses;
	}

	public void setStoreses(Set<Stores> storeses) {
		this.storeses = storeses;
	}

	public Set<Customers> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customers> customers) {
		this.customers = customers;
	}

}
