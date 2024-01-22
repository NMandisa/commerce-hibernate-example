package za.co.fynbos.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.GenericDAO;
import za.co.fynbos.model.Category;

import java.util.*;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultCategoryDAO extends AbstractDAO implements GenericDAO<Category> {
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCategoryDAO.class.getName());
    public void findById(Long categoryId) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryId", Category.class)
                .setParameter("id", categoryId);
        List<Category> categories = typedQuery.getResultList();//Need to work on this later
    }

    public List<Category> findByCategoryName(String categoryName) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryName", Category.class)
                .setParameter("name", categoryName);
        List<Category> categories = typedQuery.getResultList();
        for (Category category: categories){
            System.out.println(category.toString());
        }
        return categories;
    }

    public Category findCategoryEntityGraph(Long categoryId) {
    Map<String, Object> props = new HashMap<>();
    props.put("jakarta.persistence.loadgraph",entityManager.getEntityGraph("category_products_entity_graph"));
    Category category =   entityManager.find(Category.class, categoryId, props);
    System.out.println(category.toString());
    return category;
    }

    public Category findJPQLCategoryEntityGraph(Long categoryId) {
        return entityManager.createQuery("select c from Category c where c.id = :id", Category.class)
										.setParameter("id", categoryId)
										.setHint("jakarta.persistence.loadgraph", entityManager.getEntityGraph("category_products_entity_graph"))
										.getSingleResult();
    }

    @Override
    public void save(Category category) {
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        //return the saved category logic pending
    }

    @Override
    public void saveAll(Set<Category> categories) {
        for(Category category: categories){save(category);}
        //return the list of saved categories logic pending
    }

    @Override
    public Optional<Category> find(Long id) {//Need to work on this later not done
        return Optional.empty();
    }

    @Override
    public boolean delete(Long categoryId) {
        // create delete
        CriteriaDelete<Category> delete = cb.createCriteriaDelete(Category.class);
        // set the root class
        Root<Category> category = delete.from(Category.class);
        // set where clause
        delete.where(cb.lessThanOrEqualTo(category.get("category_id"), categoryId));
        // perform update
        entityManager.createQuery(delete).executeUpdate();
        return true;
    }

    @Override
    public boolean deleteAll(Set<Category> categories) {
        if(categories.isEmpty()){
            for(Category category: categories) {
                delete(category.getCategoryId());
            }
        }
        return true;//add validations pending...
    }

    @Override
    public boolean edit(Long categoryId, Category editCategory) {
        // create update
        CriteriaUpdate<Category> update = cb.createCriteriaUpdate(Category.class);
        // set the root class
        Root<Category> category = update.from(Category.class);
        // set update and where clause
        Predicate categoryIdPredicate = cb.equal(category.get("category_id"),categoryId);
        update.set("category_name", editCategory.getCategoryName());
        update.where(categoryIdPredicate);
        // perform update
        int rowUpdated = entityManager.createQuery(update).executeUpdate();
        return rowUpdated == 1;//the affected row is 1 than update was successful
    }

    @Override
    public boolean editAll(Set<Category> oldCategories,Set<Category> categories) {
        for(Category oldCategory : oldCategories)
        {
            Long id = oldCategory.getCategoryId();
            for(Category category : categories)
            {
                edit(id,category);//validations pending...
            }
        }
        return false;
    }
}