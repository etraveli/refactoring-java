package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;
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
import java.math.BigDecimal;
import static com.etraveli.movie.rental.service.util.CommonFixture.createMovieRentalRequest;
import static com.etraveli.movie.rental.service.util.CommonFixture.createMovieRentalResponse;

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
        String customerId= "1";
        String endPoint = "/api/rental";

        MovieRentalRequest request = createMovieRentalRequest(customerId);

        // Create a sample response
        MovieRentalResponse response = createMovieRentalResponse();

        // Mock the behavior of movieRentalService.getMovieRentalResponse
        Mockito.when(movieRentalService.getMovieRentalResponse(request))
                .thenReturn(response);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders
                        .post(endPoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name").value("C. U. Stomer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRental").value(BigDecimal.valueOf(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequentEnterPoints").value(4));

        // Verify that movieRentalService.getMovieRentalResponse was called with the correct argument
        Mockito.verify(movieRentalService).getMovieRentalResponse(request);
    }

    @Test
    void testGetMovieRental_nonExistsURL() throws Exception {
        // Prepare
        String customerId= "1";
        String nonExistsURL = "/rental";
        MovieRentalRequest request = createMovieRentalRequest(customerId);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders
                        .post(nonExistsURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetMovieRental_InvalidData() throws Exception {
        // Prepare
        String emptyCustomerId= "";
        String endPoint = "/api/rental";

        MovieRentalRequest request = createMovieRentalRequest(emptyCustomerId);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders
                        .post(endPoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}