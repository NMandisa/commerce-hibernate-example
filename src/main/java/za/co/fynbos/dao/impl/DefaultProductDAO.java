package za.co.fynbos.dao.impl;

import jakarta.transaction.Transactional;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.ProductDAO;
import za.co.fynbos.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultProductDAO extends AbstractDAO implements ProductDAO {
    @Transactional
    @Override
    public void save(Product product) {
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
    }

    public Product findProductEntityGraph(Long productId) {
        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.loadgraph",entityManager.getEntityGraph("product_images_brand_entity_graph"));
        Product productImagesEntityGraph =   entityManager.find(Product.class, productId, props);
        System.out.println(productImagesEntityGraph.toString());
        return productImagesEntityGraph;
    }
    @Override
    public void saveAll(Set<Product> products) {}
    @Override
    public Product find(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll(Set<Product> products) {
        return false;
    }

    @Override
    public boolean edit(Long id, Product product) {
        return false;
    }
    @Override
    public boolean editAll(Set<Product> oldT, Set<Product> newTs) {
        return false;
    }
}
