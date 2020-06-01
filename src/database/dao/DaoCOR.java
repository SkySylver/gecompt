package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.ObjectCOR;
import database.HibernateUtil;

public abstract class DaoCOR<T extends ObjectCOR> {
	protected String listAllQueryString;
	protected Predicate filterRestriction;
	private Class<? extends ObjectCOR> typeParameterClass;
	
	@SuppressWarnings("unused")
	private DaoCOR(){}
	
	protected DaoCOR(String listAllString) {
		listAllQueryString = listAllString;
	}
	
	protected DaoCOR(String listAllString, Class<? extends ObjectCOR> typeParameterClass) {
		listAllQueryString = listAllString;
		this.typeParameterClass = typeParameterClass;
	}

	public void save(T obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(obj);
		tx.commit();
		session.close();
	}

	public void update(T t) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(t);
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
		List<T> result = (List<T>)session.createQuery(listAllQueryString).list();
				
		session.close();
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<T> list(String filter) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> root = (CriteriaQuery<T>) builder.createQuery(typeParameterClass);
		Root<T> myObj = (Root<T>) root.from(typeParameterClass);

		
		root.select(myObj).where(getFilterRestriction(builder, root, myObj, filter));

		List<T> result = (List<T>) session.createQuery(root).getResultList();
		session.close();
		return result;
	}
	
	protected abstract Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<T> root, Root<T> myObj, String filter);
	
}
