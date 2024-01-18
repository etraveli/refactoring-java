package com.etraveli.service;

import com.etraveli.model.Customer;

public interface RentalService {
    /**
     * Returns Rental info for the requested customer.
     *
     * @param customer the person for which the Rental Info would be returned
     * @return the textual representation of Rental Information
     */
    String getRentalInfoForCustomer(Customer customer);
}
