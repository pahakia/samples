package pahakia.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PahakiaEntityManagerFactory {
    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("pahakia");
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
