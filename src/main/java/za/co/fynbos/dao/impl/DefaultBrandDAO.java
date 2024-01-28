package za.co.fynbos.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.BrandDAO;
import za.co.fynbos.model.Brand;
import za.co.fynbos.model.Product;

import java.util.*;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultBrandDAO extends AbstractDAO implements BrandDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBrandDAO.class.getName());
    //Working in Progress... (Partially working)!
    public List<Brand> findBrands() {
        CriteriaQuery<Brand> criteria = cb.createQuery(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);
        criteria.select(root);
        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
    public List<Brand> findBrandByName(String brandName){
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

    public Brand findBrandEntityGraph(Long brandId) {
        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.loadgraph",entityManager.getEntityGraph("brand_products_entity_graph"));
        Brand brand =   entityManager.find(Brand.class, brandId, props);
        System.out.println(brand.toString());
        for (Product product: brand.getProducts()){
            System.out.println(brand.getBrandName()+" Brand Product :"+product.toString());
        }
        return brand;
    }

    @Override
    public void save(Brand brand) {
        transaction.begin();
        entityManager.persist(brand);
        transaction.commit();
        //return the saved brand logic pending
    }
    @Override
    public void saveAll(Set<Brand> brands) {
        for(Brand brand: brands){save(brand);}
        //return the List of all saved brand logic pending
    }

    @Override
    public Brand find(Long id) {return entityManager.find(Brand.class,id);}
    @Override
    public boolean delete(Long brandId) {
        // create delete
        CriteriaDelete<Brand> delete = cb.createCriteriaDelete(Brand.class);
        // set the root class
        Root<Brand> brand = delete.from(Brand.class);
        // set where clause
        Predicate brandIdPredicate = cb.equal(brand.get("brand_id"),brandId);
        delete.where(brandIdPredicate);
        // perform update
        entityManager.createQuery(delete).executeUpdate();
        return false;
    }

    @Override
    public boolean deleteAll(Set<Brand> brands) {
        for (Brand deleteBrand : brands)
        {delete(deleteBrand.getBrandId());}
        return false;//pending...
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
    public boolean edit(Long brandId,Brand edit) {
        CriteriaUpdate<Brand> update = cb.createCriteriaUpdate(Brand.class);
        // set the root class
        Root<Brand> brand = update.from(Brand.class);
        // set update and where clause
        Predicate brandIdPredicate = cb.equal(brand.get("brand_id"),brandId);
        update.set("brand_name", edit.getBrandName());
        update.set("brand_description", edit.getBrandDescription());
        // set where clause
        update.where(brandIdPredicate);
        // perform update
        entityManager.createQuery(update).executeUpdate();
        return true;
    }

    @Override
    public boolean editAll(Set<Brand> oldBrands,Set<Brand> newBrands) {
        for (Brand oldBrand : oldBrands){
            Long brandId = oldBrand.getBrandId();
            for (Brand newBrand : newBrands){
                edit(brandId,newBrand);//Validations pending...
            }
        }
        return true;
    }
}
