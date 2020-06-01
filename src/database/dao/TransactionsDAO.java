package database.dao;

import java.math.BigDecimal;

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
		int temp;
		BigDecimal tempprice;
		try {
			temp = Integer.parseInt(filter);
		} catch (Exception e) {
			temp = 0;
		}
		try {
			tempprice = new BigDecimal(filter);
		} catch (Exception e) {
			tempprice = new BigDecimal(0);
		}

		return builder.or(builder.equal(myObj.get("id"), temp),
				builder.equal(myObj.get("payedAmount"), tempprice));
	}

}