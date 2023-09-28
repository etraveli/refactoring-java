package com.etraveli.movie.rental.service.repository;

import com.etraveli.movie.rental.service.dto.Customer;
import com.etraveli.movie.rental.service.util.SampleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.NoSuchElementException;
import static com.etraveli.movie.rental.service.util.CustomerFixture.createCustomerList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerRepositoryTest {

    @InjectMocks
    CustomerRepository customerRepository;

    @Mock
    SampleData sampleData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCustomerById() {
        // Prepare
        String customerId = "1";

        Mockito.when(sampleData.getCustomerData())
                .thenReturn(createCustomerList());

        // Mock repository behaviour
        Customer result =  customerRepository.findCustomerById(customerId);

        // Assertions
        assertNotNull(result);
        assertEquals(customerId, result.customerId());
        assertEquals("C. U. Stomer", result.name());
    }

    @Test
    void testFindCustomerById_NotFound() {
        // Prepare
        String nonExistsCustomerId = "789";

        Mockito.when(sampleData.getCustomerData())
                .thenReturn(Collections.EMPTY_LIST);

        // Mock repository behaviour
        NoSuchElementException result = assertThrows(NoSuchElementException.class, () -> customerRepository.findCustomerById(nonExistsCustomerId));

        // Assertions
        assertEquals("Customer not found with customer id: 789", result.getMessage());
    }
}