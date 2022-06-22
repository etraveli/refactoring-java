package se.etraveli.service;

import se.etraveli.dto.CustomerDto;

public interface RetailInfoService {
    String statement(CustomerDto customer);
}
