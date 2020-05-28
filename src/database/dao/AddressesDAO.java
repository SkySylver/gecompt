package database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import application.objects.Addresses;
import database.HibernateUtil;

public class AddressesDAO extends DaoCOR<Addresses> {
	private static AddressesDAO instance = new AddressesDAO();

	public static AddressesDAO getInstance() {
		return instance;
	}
	
	private AddressesDAO() {
		super("from application.objects.Addresses");
	}



	public Addresses getById(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Addresses result = (Addresses) session.get(Addresses.class, id);
		session.close();
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> list(String filter){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		
		String querys = "from application.objects.Addresses as add WHERE concat(add.number, ' ', add.street, ' ', add.city) LIKE '" + filter +"'";
		List<Addresses> result = (List<Addresses>)session.createQuery(querys).list();
				
		session.close();
		return result;
}

	
	
	
	@Override
	protected Predicate getFilterRestriction(CriteriaBuilder builder, CriteriaQuery<Addresses> root,
			Root<Addresses> myObj, String filter) {
		// TODO Auto-generated method stub
		return null;
	}


}
