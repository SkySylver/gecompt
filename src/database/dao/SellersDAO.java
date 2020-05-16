package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import application.objects.Sellers;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class SellersDAO extends DaoCOR {

	private static SellersDAO instance = new SellersDAO();
	public static SellersDAO getInstance() {
		return instance;
	}
	
	private SellersDAO() {
		super();}
	
	@SuppressWarnings("unchecked")
	public List<Sellers> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Sellers> q = session.createQuery("from application.objects.Sellers");
		List<Sellers> result = (List<Sellers>)q.list();
				
		tx.commit();
		session.close();
		return result;
	}

	
	public Sellers loadCurrent() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		
//		Query query = session.createQuery("from Sellers as sel WHERE sel.login = 'root'");
//		query.setParameter("log", HibernateUtil.getInstance().getUsername());
//		Sellers seller = (Sellers) query.getSingleResult();

		Sellers seller = session.load(Sellers.class, new Integer(5));
		tx.commit();
		session.close();
		return seller;
	}
	
}
