package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import application.objects.Transactions;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class TransactionsDAO extends DaoCOR {

	private static TransactionsDAO instance = new TransactionsDAO();

	public static TransactionsDAO getInstance() {
		return instance;
	}

	private TransactionsDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Transactions> listAll() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Transactions> q = session.createQuery("from application.objects.Transactions");
		List<Transactions> result = (List<Transactions>) q.list();

		tx.commit();
		session.close();

		return result;
	}






}