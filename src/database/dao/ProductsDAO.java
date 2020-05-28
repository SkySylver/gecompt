package database.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import application.objects.Products;

public class ProductsDAO extends DaoCOR<Products>{
	private static ProductsDAO instance = new ProductsDAO();
	public static ProductsDAO getInstance() {
		return instance;
	}
	
	private ProductsDAO() {
		super("from application.objects.Products", Products.class);
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Products> root, Root<Products> myObj, String filter) {
		int temp;
		try {
			temp = Integer.parseInt(filter);
		} catch (Exception e) {
			temp = 0;
		}

		Predicate p = builder.or(builder.equal(myObj.get("productId"), temp),
				builder.like(myObj.get("name"), "%" + filter + "%"),
				builder.like(myObj.get("description"), "%" + filter + "%"),
				builder.equal(myObj.get("priceDf"), "%"+temp+"%"),
				builder.equal(myObj.get("amount"), "%"+temp+"%"));

		return p;
	}

	
	/*
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
