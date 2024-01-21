package za.co.fynbos.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Noxolo.Mkhungo
 */
public class JPAUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPAUtil.class.getName());
    private static final EntityTransaction entityTransaction = entityTransaction();
    private static EntityTransaction entityTransaction(){
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("commerce-persistence-jpa");
            EntityManager entityManager =  entityManagerFactory.createEntityManager();
            return entityManager.getTransaction();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

    }

    public EntityTransaction getEntityTransaction(){
        return entityTransaction;
    }
}