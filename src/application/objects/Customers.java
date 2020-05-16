package application.objects;

import java.util.HashSet;
import java.util.Set;

public class Customers extends ObjectCOR implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private Addresses addresses;
	private String surname;
	private String firstName;
	private Boolean gender;
	private String phone;
	private String societyName;
	private Set<Transactions> transactionses = new HashSet<Transactions>(0);

	public Customers() {
	}

	public Customers(int id, String surname, String firstName) {
		this.id = id;
		this.surname = surname;
		this.firstName = firstName;
	}

	public Customers(int id, Addresses addresses, String surname, String firstName,String societyName, Boolean gender, String phone, Set<Transactions> transactionses) {
		this.id = id;
		this.addresses = addresses;
		this.surname = surname;
		this.firstName = firstName;
		this.societyName = societyName;
		this.gender = gender;
		this.phone = phone;
		this.transactionses = transactionses;
	}
	public Customers(Addresses addresses, String surname, String firstName,String societyName, Boolean gender, String phone) {
		this.addresses = addresses;
		this.surname = surname;
		this.firstName = firstName;
		this.societyName = societyName;
		this.gender = gender;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return  surname+" "+ firstName;
	}

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

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Transactions> getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set<Transactions> transactionses) {
		this.transactionses = transactionses;
	}
	public String getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
}
