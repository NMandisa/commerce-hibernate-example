package za.co.fynbos.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.GenericDAO;
import za.co.fynbos.model.Brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultBrandDAO extends AbstractDAO implements GenericDAO<Brand> {
    //Working in Progress... (Partially working)!
    public List<Brand> findBrands() {
        CriteriaQuery<Brand> criteria = cb.createQuery(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);
        criteria.select(root);
        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
    public List<Brand> findBrandByBrandName(String brandName){
        CriteriaQuery<Brand> criteriaQuery = cb.createQuery(Brand.class);
        Root<Brand> brand = criteriaQuery.from( Brand.class );
        Predicate brandNamePredicate = cb.like(brand.get("brand_name"), "%" + brandName + "%");
        criteriaQuery.where(brandNamePredicate);
        TypedQuery<Brand> query = entityManager.createQuery(criteriaQuery);
        List<Brand> brands = query.getResultList();
        for (Brand returnedbrand : brands){
            System.out.println(returnedbrand.toString());
        }
        return brands;
    }

    //Alternatively use Native Query
    /*public List<Brand> findBrandList() {
            Query query = entityManager.createNativeQuery("select * from brand", Brand.class);
            List<Brand> brands = query.getResultList();
            for (Brand returnedbrand : brands){
                System.out.println(returnedbrand.toString());
            }
            return brands;
      }*/

    @Override
    public void save(Brand brand) {
        transaction.begin();
        entityManager.persist(brand);
        transaction.commit();
    }
    @Override
    public void saveAll(Set<Brand> brands) {
        transaction.begin();
        for(Brand brand: brands){entityManager.persist(brand);}
        transaction.commit();
    }

    @Override
    public Optional<Brand> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Brand delete(Long brandId) {
        // create delete
        CriteriaDelete<Brand> delete = cb.createCriteriaDelete(Brand.class);
        // set the root class
        Root<Brand> brand = delete.from(Brand.class);
        // set where clause
        Predicate brandIdPredicate = cb.equal(brand.get("brand_id"),brandId);
        delete.where(brandIdPredicate);
        // perform update
        entityManager.createQuery(delete).executeUpdate();
        return null;
    }
    List<Brand> findBrandsByNameAndDescription(String brandName, String brandDescription) {
        CriteriaQuery<Brand> criteriaQuery = cb.createQuery(Brand.class);
        // set the root class
        Root<Brand> book = criteriaQuery.from(Brand.class);
        //set the conditions that need to be met
        Predicate brandNamePredicate = cb.equal(book.get("brand_name"), brandName);
        Predicate brandDescriptionPredicate = cb.like(book.get("brand_description"), "%" + brandDescription + "%");
        // set where clause
        criteriaQuery.where(brandNamePredicate, brandDescriptionPredicate);
        TypedQuery<Brand> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    @Override
    public Brand edit(Long brandId,Brand edit) {
        CriteriaUpdate<Brand> update = cb.createCriteriaUpdate(Brand.class);
        // set the root class
        Root<Brand> brand = update.from(Brand.class);
        // set update and where clause
        Predicate brandIdPredicate = cb.equal(brand.get("brand_id"),brandId);
        update.set("brand_name", edit.getBrandName());
        // set where clause
        update.where(brandIdPredicate);
        // perform update
        entityManager.createQuery(update).executeUpdate();
        return null;
    }
}
