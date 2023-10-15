package com.rentalmovies.common;

import com.rentalmovies.utils.CloseableEntityManager;
import com.rentalmovies.utils.JPAEntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID>
{
    private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);
    protected Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity)
    {
        try (CloseableEntityManager closeableEntityManager = JPAEntityManagerFactory.getEntityManager())
        {
            EntityManager entityManager = closeableEntityManager.getEntityManager();
            entityManager.getTransaction().begin();

            if (entityManager.contains(entity))
            {
                entityManager.persist(entity);
            }
            else
            {
                entity = entityManager.merge(entity);
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            LOG.error("Entity did not save or error while saving entity", e);
            return null;
        }
        return entity;
    }

    @Override
    public T findById(ID id)
    {
        try (CloseableEntityManager entityManager = JPAEntityManagerFactory.getEntityManager())
        {
            T entity = entityManager.getEntityManager().find(entityClass, id);
            return entity;
        }
        catch (Exception e)
        {
            LOG.error("Entity does not exist or error while reading entity", e);
            return null;
        }
    }

    @Override
    public List<T> findAll()
    {
        try (CloseableEntityManager closeableEntityManager = JPAEntityManagerFactory.getEntityManager())
        {
            EntityManager entityManager = closeableEntityManager.getEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            cq.select(cq.from(entityClass));
            TypedQuery<T> query = entityManager.createQuery(cq);
            return query.getResultList();
        }
        catch (Exception e)
        {
            LOG.error("Error while reading entities", e);
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(T entity)
    {
        try (CloseableEntityManager closeableEntityManager = JPAEntityManagerFactory.getEntityManager())
        {
            EntityManager entityManager = closeableEntityManager.getEntityManager();
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
            entityManager.flush();
        }
        catch (Exception e)
        {
            LOG.error("Delete operation failed " + e.getMessage());
        }
    }
}
