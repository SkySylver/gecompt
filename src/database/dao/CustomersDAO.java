package database.dao;

import java.util.List;

import org.hibernate.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import application.objects.Customers;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class CustomersDAO extends DaoCOR {

	private static CustomersDAO instance = new CustomersDAO();

	public static CustomersDAO getInstance() {
		return instance;
	}

	private CustomersDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Customers> listAll() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Customers> q = session.createQuery("from application.objects.Customers");
		List<Customers> result = (List<Customers>) q.list();

		tx.commit();
		session.close();
		return result;
	}

	public List<Customers> list(String filter) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customers> root = builder.createQuery(Customers.class);
		Root<Customers> myObj = root.from(Customers.class);

		int temp;
		try {
			temp = Integer.parseInt(filter);
		}
		catch (Exception e) {
			temp = 0;
		}
		
		Predicate filterRestriction = builder.or(builder.equal(myObj.get("id"), temp), builder.like(myObj.get("surname"), filter+"%"), builder.like(myObj.get("firstName"), filter+"%"), builder.like(myObj.get("phone"), filter+"%"));
		root.select(myObj).where(filterRestriction);
	    Query<Customers> query = session.createQuery(root);
	    
	    List<Customers> result = query.getResultList();
		session.close();
	    return result;
	}

	public Customers getById(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Customers result = (Customers) session.get(Customers.class, id);
		session.close();
		return result;
	}
}
