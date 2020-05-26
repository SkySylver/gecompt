package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Addresses;
import application.objects.Stores;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class StoresDAO extends DaoCOR {

	private static StoresDAO instance = new StoresDAO();
	public static StoresDAO getInstance() {
		return instance;
	}
	
	private StoresDAO() {
	}

	
	@SuppressWarnings("unchecked")
	public List<Stores> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Stores> q = session.createQuery("from application.objects.Stores");
		List<Stores> result = (List<Stores>)q.list();
		
		tx.commit();
		session.close();

		return result;
	}
	

	@SuppressWarnings("unchecked")
	public List<Stores> list(String filter){
		
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		
		String querys = "from application.objects.Stores as str WHERE str.name like '%" + filter + "%' OR concat(str.addresses.number, ' ', str.addresses.street, ' ', str.addresses.city) LIKE '%" + filter + "%'";

		List<Stores> result = (List<Stores>) session.createQuery(querys).list();
		
		//AddressesDAO.getInstance().list(filter);
		session.close();

		return result;
	}
	
	
	public void save(Stores s) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(s);
		tx.commit();
		session.close();
	}

	public void update(Stores s) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(s);
		tx.commit();
		session.close();
	}
}
