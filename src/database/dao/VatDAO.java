package database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import application.objects.Vat;
import database.HibernateUtil;

@SuppressWarnings("deprecation")
public class VatDAO extends DaoCOR {
	private static VatDAO instance = new VatDAO();
	public static VatDAO getInstance() {
		return instance;
	}
	
	private VatDAO() {
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Vat> listAll(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query<Vat> q = session.createQuery("from application.objects.Vat");
		List<Vat> result = (List<Vat>)q.list();
		
		tx.commit();
		session.close();


		return result;
	}

}
