package za.co.fynbos.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.fynbos.util.JPAUtil;


/**
 * @author Noxolo.Mkhungo
 */
public abstract class AbstractDAO {
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDAO.class.getName());
    @PersistenceContext
    protected final @NonNull EntityManager entityManager = JPAUtil.getEntityManager();
    protected final @NonNull CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    protected final @NonNull EntityTransaction transaction = JPAUtil.getEntityTransaction();
}