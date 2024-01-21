package za.co.fynbos.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Noxolo.Mkhungo
 */
public class JPAUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPAUtil.class.getName());
    private static final EntityManager entityManager = setEntityManager();
    private static EntityManager setEntityManager(){
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("commerce-persistence-jpa");
            EntityManager entityManager =  entityManagerFactory.createEntityManager();
            entityManager.unwrap(Session.class);
            return entityManager;
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static EntityManager getEntityManager(){
        return entityManager;
    }
    public static EntityTransaction getEntityTransaction(){
        return entityManager.getTransaction();
    }
}