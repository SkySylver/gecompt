package database.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import application.objects.Sellers;
import database.HibernateUtil;

public class SellersDAO extends DaoCOR<Sellers> {

	private static SellersDAO instance = new SellersDAO();

	public static SellersDAO getInstance() {
		return instance;
	}

	private SellersDAO() {
		super("from application.objects.Sellers", Sellers.class);
	}

	public Sellers loadCurrent() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Sellers seller = session.load(Sellers.class, new Integer(1));
		session.close();
		return seller;
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Sellers> root, Root<Sellers> myObj,
			String filter) {

		int temp;
		try {
			temp = Integer.parseInt(filter);
		} catch (Exception e) {
			temp = 0;
		}

		Predicate p = builder.or(builder.equal(myObj.get("id"), temp),
				builder.like(myObj.get("surname"), "%" + filter + "%"),
				builder.like(myObj.get("firstName"), "%" + filter + "%"),
				builder.like(myObj.get("phone"), "%" + filter + "%"));
		return p;
	}

}
