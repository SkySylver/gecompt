package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Products;
import database.HibernateUtil;


@SuppressWarnings("deprecation")
public class ProductsDAO extends DaoCOR{

	private static ProductsDAO instance = new ProductsDAO();
	public static ProductsDAO getInstance() {
		return instance;
	}
	
	private ProductsDAO() {
		super();
	}

	
	@SuppressWarnings("unchecked")
	public List<Products> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Products> q = session.createQuery("from application.objects.Products");
		List<Products> result = (List<Products>)q.list();

		tx.commit();
		session.close();

		return result;
	}
	
	/*
	public void persist(Products transientInstance) {

			HibernateUtil.getInstance().getSessionFactory().getCurrentSession().persist(transientInstance);

	}


	public void delete(Products persistentInstance) {
		try {
			HibernateUtil.getInstance().getSessionFactory().getCurrentSession().delete(persistentInstance);
			} catch (RuntimeException re) {
		}
	}


	public Products findById(java.lang.Integer id) {
		try {
			Products instance = (Products) HibernateUtil.getInstance().getSessionFactory().getCurrentSession().get("result.Products", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	*/
}
