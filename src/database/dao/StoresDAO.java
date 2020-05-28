package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Stores;
import database.HibernateUtil;


public class StoresDAO extends DaoCOR<Stores> {

	private static StoresDAO instance = new StoresDAO();
	public static StoresDAO getInstance() {
		return instance;
	}
	
	private StoresDAO() {
		super("from application.objects.Stores", Stores.class);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<Stores> list(String filter){
		
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		String querys = "from application.objects.Stores as str WHERE str.name like '%" + filter + "%' OR concat(str.addresses.number, ' ', str.addresses.street, ' ', str.addresses.city) LIKE '%" + filter + "%'";

		List<Stores> result = (List<Stores>) session.createQuery(querys).list();
	
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

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Stores> root, Root<Stores> myObj, String filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
