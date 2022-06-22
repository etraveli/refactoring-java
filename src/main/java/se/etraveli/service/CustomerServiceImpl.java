package se.etraveli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.etraveli.entity.Customer;
import se.etraveli.repository.CustomerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByName(String name) {
        Optional<Customer> customerByName = customerRepository.findCustomerByName(name);

        if (customerByName.isPresent()) {
            return customerByName.get();
        }
        var newCustomer = new Customer();
        newCustomer.setName(name);

        return createCustomer(newCustomer);
    }
}
