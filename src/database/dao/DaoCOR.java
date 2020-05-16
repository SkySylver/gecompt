package database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.objects.ObjectCOR;
import database.HibernateUtil;

public abstract class DaoCOR {
	public void save(ObjectCOR obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(obj);
		tx.commit();
		session.close();
	}

	public void update(ObjectCOR obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(obj);
		tx.commit();
		session.close();		
	}

	public void delete(ObjectCOR obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.delete(obj);
		tx.commit();
		session.close();	
	}
	
	public void persist(ObjectCOR obj) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();

		session.persist(obj);
		tx.commit();
		
/*		EntityManager em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
			
		em.persist(obj);
		em.getTransaction().commit();
*/
		
		
		session.close();

	}
}
