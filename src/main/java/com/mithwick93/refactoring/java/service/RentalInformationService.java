package com.mithwick93.refactoring.java.service;

import com.mithwick93.refactoring.java.entity.Customer;

/**
 * RentalInformationService class to generate the statement for the customer.
 */
public interface RentalInformationService {
    /**
     * Generate the statement for the customer.
     *
     * @param customer customer for which statement is to be generated
     * @return statement for the customer
     */
    String getStatement(Customer customer);
}
