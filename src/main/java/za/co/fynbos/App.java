package za.co.fynbos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.fynbos.model.Address;
import za.co.fynbos.model.Category;
import za.co.fynbos.model.Customer;
import za.co.fynbos.model.Product;
import za.co.fynbos.util.HibernateUtil;
import za.co.fynbos.util.JPAUtil;


/**
 * @author Noxolo.Mkhungo
 *
 */
public class App 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        //EntityTransaction entityTransaction =  new JPAUtil().getEntityTransaction();
       // System.out.println( "Hello World!" );
        //This code will make you dizzy, It made me dizzy just writing it... My Hibernate Practical Example
       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();

            Category category1 = new Category();
            Category category2 = new Category();
            category1.setCategoryName("Food");
            category2.setCategoryName("Apparel");
            Product product = new Product();
            Product product1 = new Product();
            Product product3 = new Product();
            product.setProductName("Doo Food");
            product1.setProductName("Cornflackes");
            product3.setProductName("Beetroot");
            category1.addProduct(product);
            category1.addProduct(product1);
            category2.addProduct(product3);

            Category parentCategory = new Category();
            parentCategory.setCategoryName("Men's Depo");
            Category childCategory = new Category();
            childCategory.setCategoryName("T-Shirt");
            Category parentCategory1 = new Category();
            parentCategory1.setCategoryName("Women's Depo");
            Category childCategory1 = new Category();
            childCategory1.setCategoryName("Summer Dress");
            parentCategory1.addCategory(childCategory1);//adding one category
            Set<Category> categories = new HashSet<>();
            categories.add(category1);
            categories.add(category2);
            categories.add(childCategory);
            parentCategory.addCategories(categories);//adding a list of categories



            Address address = new Address("251 Anthony Lembede", "Durban Central", "4031");
            Customer customer = new Customer();
            customer.setCustomerName("Noxolo");
            customer.setCustomerSurname("Mkhungo");
            customer.setHomeAddress(address);



            session.persist(customer);//testing address embedded
            session.persist(category1);//test products to categories
            session.persist(category2);
            session.persist(parentCategory);//test categories parent child relationship
            session.persist(parentCategory1);//test categories parent child relationship

            txn.commit();
        }	catch(Exception e) {
            txn.rollback();
            e.printStackTrace();
        }	finally {session.close(); }
        }
}
