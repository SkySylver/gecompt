package database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.ObjectCOR;
import application.objects.Sellers;
import database.HibernateUtil;

public abstract class DaoCOR<T extends ObjectCOR> {
	protected String listAllQueryString;
	private DaoCOR(){}
	
	protected DaoCOR(String listAllString) {
		listAllQueryString = listAllString;
	}
	
	public void save(T obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(obj);
		tx.commit();
		session.close();
	}

	public void update(T obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(obj);
		tx.commit();
		session.close();		
	}

	public void delete(T obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.delete(obj);
		tx.commit();
		session.close();
	}
	
	public void persist(T obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
		
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		List<T> result = session.createQuery(listAllQueryString).list();
				
		session.close();
		return result;
	}
	
}
