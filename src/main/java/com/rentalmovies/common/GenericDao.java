package com.rentalmovies.common;

import java.util.List;

/**
 * Generic DAO interface for CRUD operations.
 * By creating a generic DAO interface, you can reuse it across multiple entities, reducing the amount of boilerplate code.
 * It provides compile-time type safety, ensuring that the methods are used with the correct types of objects and IDs.
 * It ensures consistency across different DAO implementations, promoting a uniform design and making the codebase easier to maintain and understand.
 * It is flexible and can be extended or implemented for any entity class, promoting a clean code structure.
 * By defining a separate DAO for each entity, it adheres to the separation of concerns principle, making the code more modular and manageable.
 * It allows developers to change the underlying database or ORM framework with minimal impact on the code that uses this DAO.
 * @param <T>  Entity type
 * @param <ID> ID type
 */
public interface GenericDao<T, ID>
{
    /**
     * Saves an entity.
     *
     * @param entity Entity to save
     * @return Saved entity
     */
    T save(T entity);

    /**
     * Finds entity by ID.
     *
     * @param id Entity ID
     * @return Entity or null
     */
    T findById(ID id);

    /**
     * Finds all entities.
     *
     * @return List of entities
     */
    List<T> findAll();

    /**
     * Deletes an entity.
     *
     * @param entity Entity to delete
     */
    void delete(T entity);
}
