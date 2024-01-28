package za.co.fynbos.dao.impl;

import jakarta.transaction.Transactional;
import za.co.fynbos.dao.AbstractDAO;
import za.co.fynbos.dao.ProductDAO;
import za.co.fynbos.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Noxolo.Mkhungo
 */
@Transactional
public class DefaultProductDAO extends AbstractDAO implements ProductDAO {

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
    public void saveAll(List<Product> products) {
        for(Product product: products){save(product);}
    }
    @Override
    public Product find(Long productId) {
        return entityManager.find(Product.class,productId);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll(List<Product> products) {
        return false;
    }

    @Override
    public boolean edit(Long id, Product product) {
        return false;
    }
    @Override
    public boolean editAll(List<Product> oldT, List<Product> newTs) {
        return false;
    }
}
