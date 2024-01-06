package com.etraveli.controller;

import com.etraveli.constant.CodesEnum;
import com.etraveli.modal.response.MovieResponse;
import com.etraveli.service.MovieService;
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

@WebFluxTest(MovieController.class)
public class MovieControllerTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieService movieService;

    @Test
    public void testGetMovieByCorrectId() {
        logger.info("-- ENTER -- testGetMovieByCorrectId --");

        String movieCode = "F005";
        MovieResponse mockMovieResponse = new MovieResponse();
        mockMovieResponse.setMovieId(1L);
        mockMovieResponse.setMovieCode(movieCode);
        mockMovieResponse.setCode(CodesEnum.REGULAR.getValue());
        mockMovieResponse.setTitle("Tangled");

        when(movieService.getMovieByMovieCode(eq(movieCode))).thenReturn(mockMovieResponse);
        webTestClient.get()
                .uri("/api/v1/movie/{movieCode}", movieCode)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MovieResponse.class)
                .isEqualTo(mockMovieResponse);
    }
}
