DROP PROCEDURE IF EXISTS count_category_products;
DELIMITER $$
CREATE PROCEDURE count_category_products (IN cat_id BIGINT)
BEGIN
    DECLARE  cat_prod_count BIGINT;
    #SELECT COUNT(*) INTO cat_prod_count
    SELECT id,pid
    FROM (SELECT b.product_id AS id,
                  b.product_name AS pid #COUNT(*) INTO cat_prod_count
           FROM product AS b JOIN category_has_products AS f ON f.product_id = b.product_id
           WHERE f.category_id = cat_id
           ORDER BY category_id DESC
           LIMIT 0, 10) as ip
          UNION
          (SELECT category_name,c.category_id
           FROM category c, category_has_products cp
           WHERE cp.category_id=c.category_id);
    #SELECT p.product_name AS product_name,c.category_name AS category_name,COUNT(*) INTO cat_prod_count
    #FROM category_has_products cp,product p,category c
    /*LEFT JOIN category c
    ON p.product_id=cp.product_id AND c.category_id=cp.category_id*/
    #WHERE cp.category_id=cat_id AND p.product_id=cp.product_id AND c.category_id=cp.category_id;
END $$
DELIMITER ;
CALL count_category_products(1);
#Well, eventually trying to return the number of products in a category)
#still figuring that out...<category_name><product_count><>
#sale product procedure input parameter (discount percentage),
# 0original price - discount <sale price> 120 - 35% example
#saving discount in price vale
#still figuring that out...<category_name><product_count><>