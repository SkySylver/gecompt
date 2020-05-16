package application.objects;

import java.util.HashSet;
import java.util.Set;


public class Sellers extends ObjectCOR implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Stores stores;
	private String surname;
	private String firstName;
	private String phone;
	private String login;
	private String password;
	private Set<Transactions> transactionses = new HashSet<Transactions>(0);

	public Sellers() {
	}

	public Sellers(int id, String surname, String firstName, String login, String password) {
		this.id = id;
		this.surname = surname;
		this.firstName = firstName;
		this.login = login;
		this.password = password;
	}
	
	public Sellers(String surname, String firstName, String phone, Stores stores) {

		this.surname = surname;
		this.firstName = firstName;
		this.stores = stores;
		this.phone = phone;
	}
	
	
	public Sellers(int id, Stores stores, String surname, String firstName, String login, String password,
			Set<Transactions> transactionses) {
		this.id = id;
		this.stores = stores;
		this.surname = surname;
		this.firstName = firstName;
		this.login = login;
		this.password = password;
		this.transactionses = transactionses;
	}

	@Override
	public String toString() {
		return surname + " " + surname;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stores getStores() {
		return this.stores;
	}

	public void setStores(Stores stores) {
		this.stores = stores;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Transactions> getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set<Transactions> transactionses) {
		this.transactionses = transactionses;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
