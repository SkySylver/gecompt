package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import application.objects.Customers;
import database.HibernateUtil;

public class CustomersDAO extends DaoCOR<Customers> {
	private static CustomersDAO instance = new CustomersDAO();

	public static CustomersDAO getInstance() {
		return instance;
	}

	private CustomersDAO() {
		super("from application.objects.Customers", Customers.class);
	}

	public List<Customers> list(String filter) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customers> root = builder.createQuery(Customers.class);
		Root<Customers> myObj = root.from(Customers.class);

		root.select(myObj).where(getFilterRestriction(builder, root, myObj, filter));
		List<Customers> result = session.createQuery(root).getResultList();

		session.close();
		return result;
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Customers> root,
			Root<Customers> myObj, String filter) {
		int temp;
		try {
			temp = Integer.parseInt(filter);
		} catch (Exception e) {
			temp = 0;
		}
		Predicate p = builder.or(builder.equal(myObj.get("id"), temp), builder.like(myObj.get("surname"), filter + "%"),
				builder.like(myObj.get("firstName"), filter + "%"), builder.like(myObj.get("phone"), filter + "%"));

		return p;
	}

	public Customers getById(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Customers result = (Customers) session.get(Customers.class, id);
		session.close();
		return result;
	}

}
