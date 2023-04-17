package com.movie.rental.app.controller;

import com.movie.rental.app.exception.ResourceNotFoundException;
import com.movie.rental.app.model.Customer;
import com.movie.rental.app.service.RentalInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RentalInfoController.class)
class RentalInfoControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private RentalInfoService rentalInfoService;

  @Test
  void generateRentalInfo_test_success() throws Exception {
    // given
    String reqBody =
        "{\"name\": \"C. U. Stomer\",\"rentals\": [{\"movieId\": \"F001\",\"days\": 3}]}";

    String expectedResult =
        "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    when(rentalInfoService.generateRentalInfoStatement(any(Customer.class)))
        .thenReturn(expectedResult);

    // when
    ResultActions actions =
        mockMvc.perform(
            post("/rental-info").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

    // then
    actions
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("result").value(expectedResult));
  }

  @Test
  void generateRentalInfo_test_resource_not_found_exception() throws Exception {
    // given
    String reqBody =
        "{\"name\": \"C. U. Stomer\",\"rentals\": [{\"movieId\": \"F008\",\"days\": 3}]}";

    when(rentalInfoService.generateRentalInfoStatement(any(Customer.class)))
        .thenThrow(new ResourceNotFoundException("Cannot find movie with id : F008"));

    // when
    ResultActions actions =
        mockMvc.perform(
            post("/rental-info").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

    // then
    actions
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("errorMessage").value("Cannot find movie with id : F008"))
        .andExpect(jsonPath("returnCode").value(404));
  }

  @Test
  void generateRentalInfo_test_validation_movie_id_empty() throws Exception {
    // given
    String reqBody = "{\"name\": \"C. U. Stomer\",\"rentals\": [{\"movieId\": \"\",\"days\": 3}]}";

    // when
    ResultActions actions =
        mockMvc.perform(
            post("/rental-info").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

    // then
    actions
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("errorMessage").value("Movie id cannot be empty"))
        .andExpect(jsonPath("returnCode").value(400));

    verify(rentalInfoService, never()).generateRentalInfoStatement(any(Customer.class));
  }

  @Test
  void generateRentalInfo_test_validation_customer_name_null() throws Exception {
    // given
    String reqBody = "{\"rentals\": [{\"movieId\": \"F001\",\"days\": 3}]}";

    // when
    ResultActions actions =
        mockMvc.perform(
            post("/rental-info").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

    // then
    actions
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("errorMessage").isArray())
        .andExpect(jsonPath("returnCode").value(400));

    verify(rentalInfoService, never()).generateRentalInfoStatement(any(Customer.class));
  }
}
