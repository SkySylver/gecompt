package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.ProductsTransactionsHistories;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class ProductsTransactionsHistoriesDAO extends DaoCOR {

	private static ProductsTransactionsHistoriesDAO instance = new ProductsTransactionsHistoriesDAO();

	public static ProductsTransactionsHistoriesDAO getInstance() {
		return instance;
	}
	
	private ProductsTransactionsHistoriesDAO() {
		super();
	}

	
	@SuppressWarnings("unchecked")
	public List<ProductsTransactionsHistories> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<ProductsTransactionsHistories> q = session.createQuery("from application.objects.ProductsTransactionsHistories");
		List<ProductsTransactionsHistories> result = (List<ProductsTransactionsHistories>)q.list();

		tx.commit();
		session.close();

		return result;
	}

}
