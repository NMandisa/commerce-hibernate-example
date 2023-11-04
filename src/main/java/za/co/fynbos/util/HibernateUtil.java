/**
 * 
 */
package za.co.fynbos.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Noxolo.Mkhungo
 *
 */
public class HibernateUtil {
	private final static Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            LOGGER.log(Level.INFO, "Reading config setting from hibernate.cfg.xml ");

            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // for Hibernate 5.x users
            // Create the SessionFactory from hibernate.cfg.xml
            //StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
           StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            LOGGER.log(Level.SEVERE, "Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        LOGGER.log(Level.INFO, "Initial SessionFactory started." + (String) sessionFactory.toString());
        return sessionFactory;
    }
}
