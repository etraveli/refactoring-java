package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import com.etraveli.movie.rental.service.service.MovieRentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.etraveli.movie.rental.service.commons.CustomerCommon.createMovieRentalRequest;
import static com.etraveli.movie.rental.service.commons.CustomerCommon.createMovieRentalResponse;

class MovieRentalControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    MockMvc mockMvc;

    @Mock
    MovieRentalService movieRentalService;

    @InjectMocks
    MovieRentalController movieRentalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieRentalController).build();
    }

    @Test
    void testGetMovieRental() throws Exception {
        // Prepare
        Long customerId= 1L;
        String endPoint = "/rental-service/rental/details";

        RentalDetailsRequest request = createMovieRentalRequest(customerId);

        // Create a sample response
        RentalDetailsResponse response = createMovieRentalResponse();

        // Mock the behavior of movieRentalService.getMovieRentalResponse
        Mockito.when(movieRentalService.getCustomerRental(request))
                .thenReturn(response);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders
                        .post(endPoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequentEnterPoints").value(2));

        // Verify that movieRentalService.getMovieRentalResponse was called with the correct argument
        Mockito.verify(movieRentalService).getCustomerRental(request);
    }

    @Test
    void testGetMovieRental_nonExistsURL() throws Exception {
        // Prepare
        Long customerId= 1L;
        String nonExistsURL = "/rental-service/rental";
        RentalDetailsRequest request = createMovieRentalRequest(customerId);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders
                        .post(nonExistsURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
