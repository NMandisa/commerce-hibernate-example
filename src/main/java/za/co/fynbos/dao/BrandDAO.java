package za.co.fynbos.dao;

import za.co.fynbos.model.Brand;

/**
 * @author Noxolo.Mkhungo
 */
public interface BrandDAO extends GenericDAO<Brand>{
    Brand findBrandEntityGraph(Long brandId);
}
