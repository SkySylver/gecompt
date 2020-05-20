package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Customers;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class CustomersDAO extends DaoCOR{
	
	private static CustomersDAO instance = new CustomersDAO();
	public static CustomersDAO getInstance() {
		
		return instance;
		
	}
	
	private CustomersDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customers> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Customers> q = session.createQuery("from application.objects.Customers");
		List<Customers> result = (List<Customers>)q.list();
			
		tx.commit();
		session.close();
		return result;
	}
	
	public Customers getById(int id) {
		
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

		Customers result = (Customers) session.get(Customers.class, id);
			
		session.close();
		return result;
		
	}
}
