package com.rentalmovies.customer.repository;

import com.rentalmovies.common.GenericDaoImpl;
import com.rentalmovies.customer.model.Customer;

public final class CustomerDaoImpl extends GenericDaoImpl<Customer, String> implements CustomerDao
{
    public CustomerDaoImpl()
    {
        super(Customer.class);
    }
}
