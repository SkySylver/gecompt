package database;


import java.util.HashMap;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import application.objects.*;
import database.dao.*;

public class HibernateUtil {

	private SessionFactory sessionFactory;

	private String username;

	private static HashMap<Class<? extends ObjectCOR>, DaoCOR<? extends ObjectCOR>> daos;
	static {
		daos = new HashMap<Class<? extends ObjectCOR>, DaoCOR<? extends ObjectCOR>>();
		daos.put(Addresses.class, AddressesDAO.getInstance());
		daos.put(Categories.class, CategoriesDAO.getInstance());
		daos.put(Customers.class, CustomersDAO.getInstance());
		daos.put(Sellers.class, SellersDAO.getInstance());
		daos.put(Products.class, ProductsDAO.getInstance());
		daos.put(ProductsTransactionsHistories.class, ProductsTransactionsHistoriesDAO.getInstance());
		daos.put(Stores.class, StoresDAO.getInstance());
		daos.put(Transactions.class, TransactionsDAO.getInstance());
	}
	
	public DaoCOR<? extends ObjectCOR> getDAO(Class<? extends ObjectCOR> typeParameterClass) {
		return daos.get(typeParameterClass);
	}
	
	public boolean connect(String address, String port, String dbname, String username,
			String password) {
		if (sessionFactory == null) {

			this.setUsername(username);
			Properties settings = new Properties();
			settings.put(Environment.URL, "jdbc:mysql://" + address + ":" + port + "/" + dbname + "?serverTimezone=UTC");
			settings.put(Environment.USER, username);
			settings.put(Environment.PASS, password);

			
			Configuration configuration = new Configuration().configure();
			configuration.addProperties(settings);
			configuration.addResource("Products.hbm.xml");
			configuration.addResource("Vat.hbm.xml");
			configuration.addResource("Addresses.hbm.xml");
			configuration.addResource("Customers.hbm.xml");
			configuration.addResource("Sellers.hbm.xml");
			configuration.addResource("Stores.hbm.xml");
			configuration.addResource("Categories.hbm.xml");
			configuration.addResource("Stock.hbm.xml");
			configuration.addResource("Transactions.hbm.xml");
			configuration.addResource("ProductsTransactionsHistories.hbm.xml");
			

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
		return (sessionFactory != null);

	}

    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	// Singleton
	
	private static HibernateUtil db = new HibernateUtil();

	private HibernateUtil() {
	}

	public static HibernateUtil getInstance() {
		return db;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



}
