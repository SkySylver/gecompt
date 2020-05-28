package database.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import application.objects.Transactions;


public class TransactionsDAO extends DaoCOR<Transactions> {

	private static TransactionsDAO instance = new TransactionsDAO();

	public static TransactionsDAO getInstance() {
		return instance;
	}

	private TransactionsDAO() {
		super("from application.objects.Transactions", Transactions.class);
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Transactions> root,
			Root<Transactions> myObj, String filter) {

		return null;
	}

}