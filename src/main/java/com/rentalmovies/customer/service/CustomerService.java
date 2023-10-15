package com.rentalmovies.customer.service;

import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.customer.model.CustomerResponseDTO;

import java.util.Optional;

/**
 * The CustomerService interface declares methods for handling business logic 
 * related to customers. It serves as a contract for implementing classes to 
 * provide detailed implementations for customer-related operations, such as 
 * saving, retrieving, updating, and deleting customer records.
 *
 * <p>This interface is crucial for ensuring separation of concerns, where 
 * business logic is ,kept separate from the data access layer and the
 * presentation layer. It enhances testability, modularity, and maintainability 
 * of the application.</p>
 *
 * I would prefer to add separate service for write operation in production code. It depends
 * on client requirements.
 *
 * @author Sajid Riaz
 */
public interface CustomerService
{
    /**
     * Saves the given customer and returns a response DTO containing the saved 
     * customer's details. Implementing classes should handle the underlying 
     * business logic and validations required to save a customer.
     *
     * Added default implementation for backward compatibility
     *
     * @param customer The customer entity to be saved.
     * @return A CustomerResponseDTO containing the details of the saved customer.
     */
    default CustomerResponseDTO saveCustomer(final Customer customer)
    {
        return null;
    };

    /**
     * Retrieves a customer by their ID and returns an Optional containing a 
     * response DTO with the customer's details if found. If the customer is 
     * not found, the returned Optional should be empty.
     *
     * Added default implementation for backward compatibility
     * @param id The ID of the customer to be retrieved.
     * @return An Optional containing a CustomerResponseDTO if the customer is 
     * found, empty otherwise.
     */
    default Optional<CustomerResponseDTO> getCustomerById(final String id)
    {
        return Optional.empty();
    };
}
