package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import application.objects.Sellers;
import database.HibernateUtil;

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
		List<Sellers> result = session.createQuery("from application.objects.Sellers").list();
				
		session.close();
		return result;
	}

	
	public List<Sellers> list(String filter){
		int temp;
			Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Sellers> root = builder.createQuery(Sellers.class);
			Root<Sellers> myObj = root.from(Sellers.class);

			try {
				temp = Integer.parseInt(filter);
			}
			catch (Exception e) {
				temp = 0;
			}
			Predicate filterRestriction = builder.or(builder.equal(myObj.get("id"), temp), builder.like(myObj.get("surname"), "%"+filter+"%"), builder.like(myObj.get("firstName"), "%"+filter+"%"), builder.like(myObj.get("phone"), "%"+filter+"%"));
			root.select(myObj).where(filterRestriction);
		    
		    List<Sellers> result = session.createQuery(root).getResultList();
			session.close();
		    return result;
	}
	
	public Sellers loadCurrent() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Sellers seller = session.load(Sellers.class, new Integer(1));
		session.close();
		return seller;
	}
	
}
