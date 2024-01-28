package za.co.fynbos.dao;

import za.co.fynbos.model.Product;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductDAO extends GenericDAO<Product>{
    Product findProductEntityGraph(Long productId);
}
