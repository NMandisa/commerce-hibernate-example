package za.co.fynbos;

import za.co.fynbos.dao.BrandDAO;
import za.co.fynbos.dao.CategoryDAO;
import za.co.fynbos.dao.ProductDAO;
import za.co.fynbos.dao.impl.DefaultBrandDAO;
import za.co.fynbos.dao.impl.DefaultCategoryDAO;
import za.co.fynbos.dao.impl.DefaultProductDAO;
import za.co.fynbos.model.Brand;
import za.co.fynbos.model.Category;
import za.co.fynbos.model.Product;
import za.co.fynbos.model.ProductImage;


/**
 * @author Noxolo.Mkhungo
 */
public class SetupData {
    BrandDAO brandDAO = new DefaultBrandDAO();
    CategoryDAO categoryDAO = new DefaultCategoryDAO();
    ProductDAO productDAO = new DefaultProductDAO();
    public void setCategoryData(){
        Category menCategory = new Category("Men");
        Category womenCategory = new Category("Women");
        Category kidsCategory = new Category("Kids");
        Category toddlersCategory = new Category("Toddlers");

        Category mensTop=new Category("Top");
        Category mensFormal= new Category("Formal");
        Category mensGrooming= new Category("Grooming");
        Category mensJackets= new Category("Jackets");
        Category mensBottom=new Category("Bottom");
        menCategory.addCategory(mensTop);
        menCategory.addCategory(mensFormal);
        menCategory.addCategory(mensGrooming);
        menCategory.addCategory(mensJackets);
        menCategory.addCategory(mensBottom);



        Category womenAccessories= new Category("Accessories");
        Category womenBeauty=new Category("Beauty");
        Category womenBottoms= new Category("Bottoms");
        Category womenDresses= new Category("Dresses");
        Category womenJackets=new Category("Jackets");
        Category womenCoats= new Category("Coats");
        Category womenLingerieSleepwear=new Category("Lingerie & Sleepwear");
        Category womenSport=new Category("Sport");
        Category womenSwimwear=new Category("Swim Wear");
        Category womenShoes=new Category("Shoes");
        Category womenTops=new Category("Tops");
        womenCategory.addCategory(womenAccessories);
        womenCategory.addCategory(womenBeauty);
        womenCategory.addCategory(womenBottoms);
        womenCategory.addCategory(womenDresses);
        womenCategory.addCategory(womenJackets);
        womenCategory.addCategory(womenCoats);
        womenCategory.addCategory(womenLingerieSleepwear);
        womenCategory.addCategory(womenSport);
        womenCategory.addCategory(womenSwimwear);
        womenCategory.addCategory(womenShoes);
        womenCategory.addCategory(womenTops);

        Category kidsBoysCategory =new Category("Boys");
        Category kidsGirlsCategory =new Category("Girls");
        kidsCategory.addCategory(kidsGirlsCategory);
        kidsCategory.addCategory(kidsBoysCategory);

        categoryDAO.save(menCategory);
        categoryDAO.save(womenCategory);
        categoryDAO.save(kidsCategory);
        categoryDAO.save(toddlersCategory);

        //categoryDAO.saveAll(Set.of(menCategory,womenCategory,kidsCategory,toddlersCategory));
    }
    public void setProductData(){
        Product womenDressProduct = new Product("Light Blue Birdseye  Contrast T-shirt Dress");
        womenDressProduct.setProductPrice(419.99);
        womenDressProduct.setCategory(categoryDAO.find(112L));
        productDAO.save(womenDressProduct);
        Product womenDressProduct2 = new Product("Pleated Maxi Dress With Corsage");
        womenDressProduct2.setProductPrice(329.99);
        womenDressProduct2.setCategory(categoryDAO.find(112L));
        productDAO.save(womenDressProduct2);
        Product womenDressProduct3 = new Product("Black Body con Dress");
        womenDressProduct3.setProductPrice(149.99);
        womenDressProduct3.setCategory(categoryDAO.find(112L));
        productDAO.save(womenDressProduct3);
        Product womenDressProduct4 = new Product("Slim T-Shirt Dress");
        womenDressProduct4.setProductPrice(174.99);
        womenDressProduct4.setCategory(categoryDAO.find(112L));
        productDAO.save(womenDressProduct4);
        Product womenBottomsProduct = new Product("Volume Pants");
        womenBottomsProduct.setCategory(categoryDAO.find(111L));
        womenBottomsProduct.setProductPrice(99.99D);
        productDAO.save(womenBottomsProduct);
        Product womenBottomsProduct2 = new Product("Utility Shorts");
        womenBottomsProduct.setCategory(categoryDAO.find(111L));
        womenBottomsProduct2.setProductPrice(79.99D);
        productDAO.save(womenBottomsProduct2);
        Product womenBottomsProduct3 = new Product("Seamless Leggings");
        womenBottomsProduct.setCategory(categoryDAO.find(111L));
        womenBottomsProduct3.setProductPrice(129.99D);
        productDAO.save(womenBottomsProduct3);
        Product womenTopsProduct = new Product("Pink Golf T-Shirts");
        womenTopsProduct.setCategory(categoryDAO.find(119L));
        womenTopsProduct.setProductPrice(349.99D);
        productDAO.save(womenTopsProduct);
        Product womenTopsProduct2 = new Product("Graphic White Vest");
        womenTopsProduct2.setCategory(categoryDAO.find(119L));
        womenTopsProduct2.setProductPrice(49.99D);
        productDAO.save(womenTopsProduct2);
        Product womenTopsProduct3 = new Product("Strappy Top");
        womenTopsProduct3.setCategory(categoryDAO.find(119L));
        womenTopsProduct3.setProductPrice(19.99D);
        productDAO.save(womenTopsProduct3);
        Product womenTopsProduct4 = new Product("Lycra V-Neck T-Shirt");
        womenTopsProduct4.setCategory(categoryDAO.find(119L));
        womenTopsProduct4.setProductPrice(89.99D);
        productDAO.save(womenTopsProduct4);
        Product womenSwimwearProduct = new Product("Mono-Bikini");
        womenSwimwearProduct.setCategory(categoryDAO.find(117L));
        womenSwimwearProduct.setProductPrice(169.99D);
        productDAO.save(womenSwimwearProduct);
        Product womenSwimwearProduct2 = new Product("Black Polyester Boyleg Swimsuit");
        womenSwimwearProduct2.setCategory(categoryDAO.find(117L));
        womenSwimwearProduct2.setProductPrice(139.99D);
        productDAO.save(womenSwimwearProduct2);
        Product womenSwimwearProduct3 = new Product("Twist Front Swim Skirt");
        womenSwimwearProduct3.setProductPrice(99.99D);
        womenSwimwearProduct3.setCategory(categoryDAO.find(117L));
        productDAO.save(womenSwimwearProduct3);
        Product womenAccessoriesProduct = new Product("Hair-On Leather Belt");
        womenAccessoriesProduct.setCategory(categoryDAO.find(109L));
        womenAccessoriesProduct.setProductPrice(399.99D);
        productDAO.save(womenAccessoriesProduct);
        Product womenAccessoriesProduct2 = new Product("Socks 2-pack");
        womenAccessoriesProduct2.setProductPrice(149.99);
        womenAccessoriesProduct2.setCategory(categoryDAO.find(109L));
        productDAO.save(womenAccessoriesProduct2);
        Product womenAccessoriesProduct3 = new Product("Slouchy Hobo Bag");
        womenAccessoriesProduct3.setProductPrice(1499.99);
        womenAccessoriesProduct3.addBrand(new Brand("xNMx"));
        womenAccessoriesProduct3.setCategory(categoryDAO.find(109L));
        productDAO.save(womenAccessoriesProduct3);
    }
    public void setBrandData(){
        Brand brand =  new Brand("Rich Durban");
        brand.setBrandDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris n");
        Product brandProduct =new Product("Slim Fit Cotton Stretch T-shirt");
        brandProduct.setCategory(categoryDAO.find(119L));
        brandProduct.setProductPrice(349.99D);
        brand.addProduct(brandProduct);
        brandDAO.save(brand);
        Brand brand2 =  new Brand("GreenBat");
        brand.setBrandDescription("Sport wear and basics");
        Product brand2Product =new Product("Denim Shorts");
        brand2Product.setCategory(categoryDAO.find(108L));
        brand2Product.setProductPrice(649.99D);
        ProductImage brand2ProductImage=new ProductImage("https://images/products/greenbat/1400/denim-shorts.jpg");
        ProductImage brand2ProductImage2=new ProductImage("https://images/products/greenbat/550/denim-shorts.jpg");
        ProductImage brand2ProductImage3=new ProductImage("https://images/products/greenbat/320/denim-shorts.jpg");
        ProductImage brand2ProductImage4=new ProductImage("https://images/products/greenbat/75/denim-shorts.jpg");
        brand2Product.addImage(brand2ProductImage);
        brand2Product.addImage(brand2ProductImage2);
        brand2Product.addImage(brand2ProductImage3);
        brand2Product.addImage(brand2ProductImage4);
        brand2.addProduct(brand2Product);
        brandDAO.save(brand2);
        Brand brand3 =  new Brand("Southern Face");
        brandDAO.save(brand3);
        Brand brand4 =  new Brand("Wool-Who");
        brand4.setBrandDescription("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris n");
        brandDAO.save(brand4);
        Brand brand5 = new Brand("Godly Apparels");
        //brand5.setBrandDescription("");
        brandDAO.save(brand5);
        Brand brand6 = new Brand("Lamontville's Most Wanted");
        //brand6.setBrandDescription("");
        brandDAO.save(brand6);
        Brand brand7 = new Brand("Durban Poison");
       // brand7.setBrandDescription("");
        brandDAO.save(brand7);
        Brand brand8 = new Brand("Dando Apparels");
        //brand8.setBrandDescription("");
        brandDAO.save(brand8);
    }

    public void setBrandProduct(){
        Product product = new Product("Tank Top");
        Brand productBrand = new Brand("Ghetto.FRESH(R)");
        product.setBrand(productBrand);
        product.setCategory(categoryDAO.find(119L));

        ProductImage image=new ProductImage("https://images/products/ghetto.fresh/1400/tank-top.jpg");
        ProductImage image2=new ProductImage("https://images/products/ghetto.fresh/550/tank-top.jpg");
        ProductImage image3=new ProductImage("https://images/products/ghetto.fresh/320/tank-top.jpg");
        ProductImage image4=new ProductImage("https://images/products/ghetto.fresh/75/tank-top.jpg");
        product.addImage(image);
        product.addImage(image2);
        product.addImage(image3);
        product.addImage(image4);

        Product product2 = new Product("Truck Pants");
        Brand product2Brand = new Brand("Ghetto.FABULOUS(R)");
        product2.setBrand(product2Brand);
        product2.setCategory(categoryDAO.find(108L));

        ProductImage product2image=new ProductImage("https://images/products/ghetto-fabulous/1400/truck-pant.jpg");
        ProductImage product2image2=new ProductImage("https://images/products/ghetto-fabulous/550/truck-pant.jpg");
        ProductImage product2image3=new ProductImage("https://image/products/ghetto-fabulous/340/truck-pant.jpg");
        ProductImage product2image4=new ProductImage("https://image/products/ghetto-fabulous/75/truck-pant.jpg");
        product2.addImage(product2image);
        product2.addImage(product2image2);
        product2.addImage(product2image3);
        product2.addImage(product2image4);

        Product product3 = new Product("Chino Short Pants");
        Brand product3Brand = new Brand("Ndisto (R)");
        product3.setBrand(product3Brand);
        product3.setCategory(categoryDAO.find(108L));


        ProductImage product3image=new ProductImage("https://images/products/ndisto/1400/chino-short-pants.jpg");
        ProductImage product3image2=new ProductImage("https://images/products/ndisto/550/tchino-short-pants.jpg");
        ProductImage product3image3=new ProductImage("https://images/products/ndisto/340/chino-short-pants.jpg");
        ProductImage product3image4=new ProductImage("https://images/products/ndisto/75/chino-short-pants.jpg");

        product3.addImage(product3image);
        product3.addImage(product3image2);
        product3.addImage(product3image3);
        product3.addImage(product3image4);
        productDAO.save(product);
        productDAO.save(product2);
        productDAO.save(product3);

       /* List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        productDAO.saveAll(products);*/
    }

}
