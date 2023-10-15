package com.rentalmovies.utils;

import javax.persistence.EntityManager;

/**
 * The CloseableEntityManager class is a wrapper around the EntityManager that
 * implements AutoCloseable, ensuring that the EntityManager is properly closed
 * when it's no longer needed, helping to manage resources efficiently and
 * avoid potential memory leaks.
 *
 * <p>It provides a convenient way to use the EntityManager within a try-with-resources
 * statement, ensuring that the EntityManager is automatically closed at the end
 * of the statement.</p>
 *
 * @author Sajid Riaz
 */
public class CloseableEntityManager implements AutoCloseable
{

    private final EntityManager entityManager;

    public CloseableEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    @Override
    public void close()
    {
        if (entityManager.isOpen())
        {
            entityManager.close();
        }
    }
}
