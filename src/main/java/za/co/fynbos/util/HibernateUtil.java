/**
 * 
 */
package za.co.fynbos.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Noxolo.Mkhungo
 *
 */
public class HibernateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class.getName());
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            LOGGER.debug("Reading config setting from hibernate.cfg.xml ");

           // Configuration configuration = new Configuration().configure("META-INF/hibernate.cfg.xml");

            // for Hibernate 5.x users
            // Create the SessionFactory from hibernate.cfg.xml
            //StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
           StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("META-INF/hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            LOGGER.debug( "Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        LOGGER.debug("Initial SessionFactory started." + (String) sessionFactory.toString());
        return sessionFactory;
    }
}
