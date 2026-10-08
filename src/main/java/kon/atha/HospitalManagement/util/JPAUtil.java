package kon.atha.HospitalManagement.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospitalPU");

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
