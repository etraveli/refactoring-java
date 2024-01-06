package com.etraveli.controller;

import com.etraveli.modal.response.CustomerResponse;
import com.etraveli.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebFluxTest(CustomerController.class)
public class CustomerControllerTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetCustomerByCorrectId() {
        logger.info("-- ENTER -- testGetCustomerByCorrectId --");

        Long customerId = 1L;
        CustomerResponse mockCustomerResponse = new CustomerResponse();
        mockCustomerResponse.setCustomerId(customerId);
        mockCustomerResponse.setBirthYear(1990);
        mockCustomerResponse.setName("Sahan Ekanayake");
        mockCustomerResponse.setIdNumber("19890102-1234");

        when(customerService.getCustomerById(eq(customerId))).thenReturn(mockCustomerResponse);
        webTestClient.get()
                .uri("/api/v1/customer/{customerId}", customerId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerResponse.class)
                .isEqualTo(mockCustomerResponse);
    }
}
