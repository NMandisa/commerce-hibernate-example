package za.co.fynbos.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.GlobalDAO;
import za.co.fynbos.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultCategoryDAO extends AbstractDAO implements GlobalDAO<Category> {
    public void findById(Long categoryId) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryId", Category.class)
                .setParameter("id", categoryId);
        List<Category> categories = typedQuery.getResultList();//Need to work on this later
    }

    public List<Category> findByCategoryName(String categoryName) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryName", Category.class)
                .setParameter("name", categoryName);
        List<Category> categories = typedQuery.getResultList();
        for (Category category: categories){System.out.println(category.toString());}
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
    }

    @Override
    public Optional<Category> find(Long id) {//Need to work on this later not done
        return Optional.empty();
    }

    @Override
    public Category delete(Long categoryId) {//Need to work on this later not done
        // create delete
        CriteriaDelete<Category> delete = cb.createCriteriaDelete(Category.class);
        // set the root class
        Root<Category> category = delete.from(Category.class);
        // set where clause
        delete.where(cb.lessThanOrEqualTo(category.get("category_id"), categoryId));
        // perform update
        entityManager.createQuery(delete).executeUpdate();
        return null;
    }

    @Override
    public Category edit(Long id, Category editCategory) {//Need to work on this later not done
        // create update
        CriteriaUpdate<Category> update = cb.createCriteriaUpdate(Category.class);
        // set the root class
        Root<Category> category = update.from(Category.class);
        // set update and where clause
        update.set("category_name", editCategory.getCategoryName());
        update.where(cb.greaterThanOrEqualTo(category.get("category_id"), id));
        // perform update
        entityManager.createQuery(update).executeUpdate();
        return null;
    }
}