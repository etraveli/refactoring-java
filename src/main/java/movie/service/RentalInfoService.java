package movie.service;

import movie.dto.Customer;

public interface RentalInfoService {

    String getStatementForCustomer(Customer customer);
}
