package za.co.fynbos.dao;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import za.co.fynbos.model.Brand;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public class BrandDAO extends AbstractDAO{
    public List<Brand> findByAllBrands() {
        CriteriaQuery<Brand> query = cb.createQuery(Brand.class);
        Root<Brand> root = query.from(Brand.class);
        query.select(root).where();
        return entityManager.createQuery(query).getResultList();
    }
    public List<Brand> findBrands() {
        CriteriaQuery<Brand> criteria = cb.createQuery(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);
        criteria.select(root);
        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
    public List<String> findBrandByBrandName(){
        CriteriaQuery<String> criteria = cb.createQuery(String.class);
        Root<Brand> root = criteria.from( Brand.class );
        Path<String> name = root.get("brandName");
        criteria.select(name);
        TypedQuery<String> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
        public List<Brand> find() {
            Query query = entityManager.createNativeQuery("select * from brand", Brand.class);
            return query.getResultList();
        }
}
