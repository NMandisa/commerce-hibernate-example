show schemas;
show tables;

select * from commerce.brand_sequence;
select * from commerce.category_sequence;
select * from commerce.product_sequence;
select * from commerce.category_has_products;
select * from commerce.category;
select * from commerce.product;
select * from commerce.customer;
select * from commerce.category_has_categories;

create table category
(
    category_id   bigint       not null
        primary key,
    category_name varchar(255) null
);

INSERT INTO db_commerce.category (category_id, category_name) VALUES (101, 'Men');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (102, 'Women');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (103, 'Kids');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (104, 'Toddlers');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (105, 'Formal');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (106, 'Grooming');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (107, 'Jackets');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (108, 'Bottom');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (109, 'Accessories');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (110, 'Beauty');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (111, 'Bottoms');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (112, 'Dresses');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (113, 'Jackets');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (114, 'Coats');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (115, 'Lingerie & Sleepwear');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (116, 'Sport');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (117, 'Swim Wear');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (118, 'Shoes');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (119, 'Tops');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (120, 'Girls');
INSERT INTO db_commerce.category (category_id, category_name) VALUES (121, 'Boys');

#Parent Category to Child Category Association
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (101, 105);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (101, 106);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (101, 107);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (101, 108);

INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 109);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 110);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 111);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 112);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 113);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 114);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 115);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 116);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 117);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 118);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (102, 119);

INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (103, 120);
INSERT INTO db_commerce.category_has_categories (parent_category_id, child_category_id) VALUES (103, 121);

# Product
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (1, '2024-01-27 22:38:11.763017', '2024-01-27 22:38:11.763017', 'Light Blue Birdseye  Contrast T-shirt Dress', 419.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (2, '2024-01-27 22:38:11.882518', '2024-01-27 22:38:11.882518', 'Pleated Maxi Dress With Corsage', 329.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (3, '2024-01-27 22:38:11.903143', '2024-01-27 22:38:11.903143', 'Black Body con Dress', 149.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (4, '2024-01-27 22:38:11.921856', '2024-01-27 22:38:11.921856', 'Slim T-Shirt Dress', 174.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (5, '2024-01-27 22:38:11.946317', '2024-01-27 22:38:11.946317', 'Volume Pants', 99.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (6, '2024-01-27 22:38:11.969274', '2024-01-27 22:38:11.969274', 'Utility Shorts', 79.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (7, '2024-01-27 22:38:11.997085', '2024-01-27 22:38:11.997085', 'Seamless Leggings', 129.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (8, '2024-01-27 22:38:12.027639', '2024-01-27 22:38:12.028651', 'Pink Golf T-Shirts', 349.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (9, '2024-01-27 22:38:12.057360', '2024-01-27 22:38:12.057360', 'Graphic White Vest', 49.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (10, '2024-01-27 22:38:12.082842', '2024-01-27 22:38:12.082842', 'Strappy Top', 19.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (11, '2024-01-27 22:38:12.107478', '2024-01-27 22:38:12.107478', 'Lycra V-Neck T-Shirt', 89.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (12, '2024-01-27 22:38:12.133471', '2024-01-27 22:38:12.133471', 'Mono-Bikini', 169.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (13, '2024-01-27 22:38:12.157958', '2024-01-27 22:38:12.157958', 'Black Polyester Boyleg Swimsuit', 139.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (14, '2024-01-27 22:38:12.186646', '2024-01-27 22:38:12.186646', 'Twist Front Swim Skirt', 99.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (15, '2024-01-27 22:38:12.221816', '2024-01-27 22:38:12.221816', 'Hair-On Leather Belt', 399.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (16, '2024-01-27 22:38:12.255080', '2024-01-27 22:38:12.255080', 'Socks 2-pack', 149.99);
INSERT INTO db_commerce.product (product_id, date_created, last_updated, product_name, product_price) VALUES (17, '2024-01-27 22:38:12.280839', '2024-01-27 22:38:12.280839', 'Slouchy Hobo Bag', 1499.99);



#Category to Product Association
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (109, 15);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (109, 16);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (109, 17);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (111, 5);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (112, 1);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (112, 2);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (112, 3);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (112, 4);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (117, 12);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (117, 13);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (117, 14);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (119, 8);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (119, 9);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (119, 10);
INSERT INTO db_commerce.category_has_products (category_id, product_id) VALUES (119, 11);

INSERT INTO db_commerce.brands_has_products (brand_id, product_id) VALUES (100, 10);
INSERT INTO db_commerce.brands_has_products (brand_id, product_id) VALUES (100, 1);