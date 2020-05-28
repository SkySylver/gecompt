package database.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import application.objects.ProductsTransactionsHistories;

public class ProductsTransactionsHistoriesDAO extends DaoCOR<ProductsTransactionsHistories> {

	private static ProductsTransactionsHistoriesDAO instance = new ProductsTransactionsHistoriesDAO();

	public static ProductsTransactionsHistoriesDAO getInstance() {
		return instance;
	}
	
	private ProductsTransactionsHistoriesDAO() {
		super("from application.objects.ProductsTransactionsHistories", ProductsTransactionsHistories.class);
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<ProductsTransactionsHistories> root, Root<ProductsTransactionsHistories> myObj, String filter) {
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
