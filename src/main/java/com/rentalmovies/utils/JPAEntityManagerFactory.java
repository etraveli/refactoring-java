package com.rentalmovies.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * The JPAEntityManagerFactory class serves as a singleton factory for creating and managing
 * {@link CloseableEntityManager} instances. Being AutoCloseable, it ensures proper cleanup of resources,
 * including the closure of the EntityManagerFactory upon application termination.
 *
 * <p>It's initialized with specific JPA and Hibernate properties to interact with an H2 in-file database,
 * allowing for easy configuration and management of the underlying persistence layer.</p>
 *
 * The JPAEntityManagerFactory class is implemented as a singleton, ensuring a single instance throughout 
 * the application's lifecycle, promoting consistent entity management and efficient resource usage.
 * @author Sajid Riaz
 */
public class JPAEntityManagerFactory implements AutoCloseable
{
    private static final EntityManagerFactory entityManagerFactory;

    static
    {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        //properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        properties.put("javax.persistence.jdbc.user", "sa");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:file:jdbc:h2:file:./src/main/resources/movie-rental-db;DB_CLOSE_DELAY=-1");
        properties.put("hibernate.hbm2ddl.auto", "update");

        entityManagerFactory = Persistence.createEntityManagerFactory("myPU", properties);
    }

    private JPAEntityManagerFactory()
    {
        // private constructor to prevent instantiation
    }

    /**
     * Provides a CloseableEntityManager instance, facilitating resource management
     * and enabling the use of the try-with-resources statement.
     *
     * @return A new CloseableEntityManager instance.
     */
    public static CloseableEntityManager getEntityManager()
    {
        return new CloseableEntityManager(entityManagerFactory.createEntityManager());
    }

    /**
     * Closes the EntityManagerFactory when called, typically upon application termination
     * to release database connections and cleanup resources.
     */
    @Override
    public void close()
    {
        if (entityManagerFactory.isOpen())
        {
            entityManagerFactory.close();
        }
    }

    /**
     * Provides a singleton instance of JPAEntityManagerFactory, ensuring a single
     * EntityManagerFactory is used throughout the application's lifecycle.
     *
     * @return An instance of JPAEntityManagerFactory.
     */
    public static JPAEntityManagerFactory getInstance()
    {
        return new JPAEntityManagerFactory();
    }
}
