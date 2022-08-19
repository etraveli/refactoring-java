package com.etraveli.service;

import com.etraveli.model.Customer;
import com.etraveli.model.RentalInformation;

import java.util.List;

public interface RentalInformationService {

    List<RentalInformation> statement(Customer customer);

}
