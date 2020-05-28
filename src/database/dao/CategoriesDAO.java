package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import application.objects.Categories;
import database.HibernateUtil;

public class CategoriesDAO extends DaoCOR<Categories>{
	private static CategoriesDAO instance = new CategoriesDAO();
	public static CategoriesDAO getInstance() {
		
		return instance;
		
	}
	
	private CategoriesDAO() {
		super("from application.objects.Categories");
	}

	@SuppressWarnings("unchecked")
	public List<Categories> list(Categories currentCat, String filter){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		List<Categories> result = session.createQuery("from application.objects.Categories as tab WHERE tab.name like" + filter).list();
		session.close();
		return result;
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Categories> root, Root<Categories> myObj, String filter) {

		return null;
	}
	
}
