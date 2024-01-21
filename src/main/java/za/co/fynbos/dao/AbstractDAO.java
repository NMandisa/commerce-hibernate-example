package za.co.fynbos.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import za.co.fynbos.util.JPAUtil;


/**
 * @author Noxolo.Mkhungo
 */
public abstract class AbstractDAO{
    @PersistenceContext
    protected EntityManager entityManager = JPAUtil.getEntityManager();
    protected CriteriaBuilder cb = entityManager.getCriteriaBuilder();
}