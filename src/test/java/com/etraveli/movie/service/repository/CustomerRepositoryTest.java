package com.etraveli.movie.service.repository;

import com.etraveli.movie.service.BaseTest;
import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.Customer;
import java.util.Collections;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.etraveli.movie.service.util.TestData.getCustomerList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerRepositoryTest extends BaseTest {

    @InjectMocks
    private CustomerRepository customerRepository;
    @Mock
    private DataHolder dataHolder;

    @Before
    public void setUp() {
        customerRepository = new CustomerRepository(dataHolder);
    }

    @Test
    public void testFindCustomerByID() {

        Mockito.when(dataHolder.getCustomers()).thenReturn(getCustomerList());

        // Test finding a customer by ID
        Customer foundCustomer = customerRepository.findCustomerByID("001");
        assertNotNull(foundCustomer);
        assertEquals("001", foundCustomer.customerID());
        assertEquals("Customer 1", foundCustomer.customerName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindCustomerByIDNotFound() {

        Mockito.when(dataHolder.getCustomers()).thenReturn(Collections.EMPTY_LIST);

        //expecting an exception
        customerRepository.findCustomerByID("003");
    }
}