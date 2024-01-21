package za.co.fynbos.dao.impl;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.GlobalDAO;
import za.co.fynbos.model.Brand;

import java.util.List;
import java.util.Optional;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultBrandDAO extends AbstractDAO implements GlobalDAO<Brand> {
    //Need to work on this not done (NOTHING HAS BEEN TEST)!!!!!
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
        public List<Brand> findBrandList() {
            Query query = entityManager.createNativeQuery("select * from brand", Brand.class);
            return (List<Brand>) query.getResultList();
        }

    @Override
    public void save(Brand brand) {
        transaction.begin();
        entityManager.persist(brand);
        transaction.commit();
    }
    @Override
    public Optional<Brand> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Brand delete(Long id) {
        // create delete
        CriteriaDelete<Brand> delete = cb.createCriteriaDelete(Brand.class);
        // set the root class
        Root<Brand> brand = delete.from(Brand.class);
        // set where clause
        delete.where(cb.lessThanOrEqualTo(brand.get("brand_id"), id));
        // perform update
        entityManager.createQuery(delete).executeUpdate();
        return null;
    }
    @Override
    public Brand edit(Long id,Brand edit) {
        CriteriaUpdate<Brand> update = cb.createCriteriaUpdate(Brand.class);
        // set the root class
        Root<Brand> brand = update.from(Brand.class);
        // set update and where clause
        update.set("brand_name", edit.getBrandName());
        update.where(cb.greaterThanOrEqualTo(brand.get("brand_id"), id));
        // perform update
        entityManager.createQuery(update).executeUpdate();
        return null;
    }
}
