package in.abc.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.model.InsurancePolicy;
import in.abc.util.HibernateUtil;

public class LockingAppTest2 {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		boolean flag = false;
		
		try {
			
			transaction = session.beginTransaction();
			InsurancePolicy policy = session.get(InsurancePolicy.class, 1L);
			System.out.println(policy);
			
			
			//modify some data with policy objet
			policy.setTenure(24);
			flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated...");
			} else {
				transaction.rollback();
				System.out.println("Object not updated...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
