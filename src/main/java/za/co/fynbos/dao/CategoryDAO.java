package za.co.fynbos.dao;

import jakarta.persistence.TypedQuery;
import za.co.fynbos.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Noxolo.Mkhungo
 */
public class CategoryDAO extends AbstractDAO {
    public void findById(Long categoryId) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryId", Category.class)
                .setParameter("id", categoryId);
        List<Category> categories = typedQuery.getResultList();//Need to work on this later
    }

    public void findByCategoryName(String categoryName) {
        TypedQuery<Category> typedQuery = entityManager.createNamedQuery("findByCategoryName", Category.class)
                .setParameter("name", categoryName);
        List<Category> categories = typedQuery.getResultList();//Need to work on this later
    }

    public Category findCategoryEntityGraph(Long categoryId) {
    Map<String, Object> props = new HashMap<>();
    props.put("jakarta.persistence.loadgraph",entityManager.getEntityGraph("category_products_entity_graph"));
    return entityManager.find(Category.class, categoryId, props);
    }

    public Category findJPQLCategoryEntityGraph(Long categoryId) {
        return entityManager.createQuery("select c from Category c where c.id = :id", Category.class)
										.setParameter("id", categoryId)
										.setHint("jakarta.persistence.loadgraph", entityManager.getEntityGraph("category_products_entity_graph"))
										.getSingleResult();
    }
}