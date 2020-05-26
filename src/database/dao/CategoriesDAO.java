package database.dao;

import java.util.List;

import org.hibernate.Session;

import application.objects.Categories;
import database.HibernateUtil;

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

		List<Categories> result = (List<Categories>)session.createQuery("from application.objects.Categories").list();
		
		session.close();
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Categories> list(Categories currentCat, String filter){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		List<Categories> result = session.createQuery("from application.objects.Categories as tab WHERE tab.name like" + filter).list();
		session.close();
		return result;
	}
	
}
