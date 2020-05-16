package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.Categories;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class CategoriesDAO extends DaoCOR{
	private static CategoriesDAO instance = new CategoriesDAO();
	public static CategoriesDAO getInstance() {
		
		return instance;
		
	}
	
	private CategoriesDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categories> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Categories> q = session.createQuery("from application.objects.Categories");
		List<Categories> result = q.list();
		
		tx.commit();
		session.close();

		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Categories> list(String cat){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		//HibernateUtil.getInstance().getSessionFactory().getCurrentSession()
		Query<Categories> q = session.createQuery("from application.objects.Categories as tab WHERE tab.name like" + cat);
		List<Categories> result = q.list();
		
		tx.commit();
		session.close();
		return result;
	}
	
}
