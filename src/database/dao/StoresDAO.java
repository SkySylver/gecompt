package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		
//		List<Products> result = (List<Products>)session.createSQLQuery("SELECT id, name, description, price_df, referenced_web, referenced_sellers from Products").addEntity(Products.class).list();
		
		tx.commit();
		session.close();
		System.out.println(result);


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
