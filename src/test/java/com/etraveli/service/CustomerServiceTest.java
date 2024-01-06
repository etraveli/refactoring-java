package com.etraveli.service;

import com.etraveli.modal.response.CustomerResponse;
import com.etraveli.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@WebFluxTest(CustomerServiceImpl.class)
public class CustomerServiceTest {

    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void testGetCustomerById() {
        Long customerId = 1L;

        CustomerResponse expectedResponse = new CustomerResponse();
        expectedResponse.setCustomerId(customerId);
        expectedResponse.setBirthYear(1989);
        expectedResponse.setIdNumber("19890101-1234");
        expectedResponse.setName("C. U. Stomer");

        when(customerServiceImpl.getCustomerById(customerId)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerServiceImpl.getCustomerById(customerId);

        assertThat("Actual Customer should not be null", actualResponse, notNullValue());
        assertThat("Customer ID should match", actualResponse.getCustomerId(), equalTo(customerId));
        assertThat("Customer ID Number should match", actualResponse.getIdNumber(), equalTo(expectedResponse.getIdNumber()));
        assertThat("Customer name should match", actualResponse.getName(), equalTo(expectedResponse.getName()));
    }
}
