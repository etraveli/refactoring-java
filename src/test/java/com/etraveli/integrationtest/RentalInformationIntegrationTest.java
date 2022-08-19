package com.etraveli.integrationtest;

import com.etraveli.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.etraveli.testdata.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalInformationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_rental_information_when_customer_has_movie_rentals() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/rental-information", getCustomerWithRentals(),
                String.class)).isEqualTo(CUSTOMER_RENTAL_INFORMATION);
    }

    @Test()
    public void test_rental_information_when_customer_has_no_movie_rentals() throws Exception {

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/rental-information", getCustomerWithoutRentals(),
                String.class)).contains("Customer has No Active Movie Rentals");
    }

    @Test()
    public void test_rental_information_when_no_customer_name() throws Exception {

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/rental-information", new Customer(null, null),
                String.class)).contains("Customer is not provided to get Rental Information");
    }
}
