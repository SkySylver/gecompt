package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Addresses;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class AddressesDAO extends DaoCOR {

	private static AddressesDAO instance = new AddressesDAO();

	public static AddressesDAO getInstance() {
		return instance;
	}
	
	private AddressesDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Addresses> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Addresses> q = session.createQuery("from application.objects.Addresses");
		List<Addresses> result = (List<Addresses>)q.list();
				
		tx.commit();
		session.close();

		return result;
}

	public Addresses getById(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Addresses result = (Addresses) session.get(Addresses.class, id);
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Addresses> list(String filter){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		String querys = "from application.objects.Addresses as add WHERE concat(add.number, ' ', add.street, ' ', add.city) LIKE '" + filter +"'";

		Query<Addresses> q = session.createQuery(querys);
		List<Addresses> result = (List<Addresses>)q.list();
				
		tx.commit();
		session.close();

		return result;
}


}
