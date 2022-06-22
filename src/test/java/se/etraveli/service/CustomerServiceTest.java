package se.etraveli.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.etraveli.entity.Customer;
import se.etraveli.repository.CustomerRepository;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

public class CustomerServiceTest {
    private final CustomerRepository customerRepository = Mockito.spy(CustomerRepository.class);
    private final CustomerService customerService = new CustomerServiceImpl(customerRepository);

    @Test
    public void createCustomerTest() {
        var customer = new Customer(12L, "23");

        doReturn(customer).when(customerRepository).save(Mockito.any());
        Customer customerSave = customerService.createCustomer(customer);

        Assertions.assertEquals(customer, customerSave);
    }

    @Test
    public void findCustomerByNameTest() {
        var customer = Optional.of(new Customer(12L, "23"));

        doReturn(customer).when(customerRepository).findCustomerByName(Mockito.any());
        Customer customerSave = customerService.findCustomerByName("23");

        Assertions.assertEquals(customer.get(), customerSave);
    }
}
