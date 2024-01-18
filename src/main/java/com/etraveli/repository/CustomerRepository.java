package com.etraveli.repository;

import com.etraveli.model.Customer;

public interface CustomerRepository {
    /**
     * Gets Customer associated with provided customerId
     *
     * @param customerId textual identifier for which the Customer should be returned
     * @return customer associated with requested customer id
     */
    Customer getCustomerById(String customerId);
}
