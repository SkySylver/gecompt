package database.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import application.objects.Vat;

public class VatDAO extends DaoCOR<Vat> {
	private static VatDAO instance = new VatDAO();
	public static VatDAO getInstance() {
		return instance;
	}
	
	private VatDAO() {
		super("from application.objects.Vat", Vat.class);
	}

	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Vat> root, Root<Vat> myObj,
			String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
