package com.junjie.movie.rental.dto.converter;

import com.junjie.movie.rental.dto.CustomerDto;
import com.junjie.movie.rental.entity.Customer;

public class CustomerConverter {
    public static CustomerDto convertModel2Dto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getName(),
                customer.getFrequentEnterPoints());
    }

    public static Customer convertDto2Model(CustomerDto customerDto) {
        return new Customer(customerDto.getName());
    }
}
