package com.etraveli.service;

import com.etraveli.constant.CodesEnum;
import com.etraveli.modal.response.MovieResponse;
import com.etraveli.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@WebFluxTest(MovieServiceImpl.class)
public class MovieServiceTest {

    @MockBean
    private MovieServiceImpl movieServiceImpl;

    @Test
    void testGetMovieByMovieCode() {
        String movieCode = "F001";

        MovieResponse expectedResponse = new MovieResponse();
        expectedResponse.setMovieId(1L);
        expectedResponse.setMovieCode(movieCode);
        expectedResponse.setCode(CodesEnum.REGULAR.getValue());
        expectedResponse.setTitle("You've Got Mail");

        when(movieServiceImpl.getMovieByMovieCode(movieCode)).thenReturn(expectedResponse);

        MovieResponse actualResponse = movieServiceImpl.getMovieByMovieCode(movieCode);

        assertThat("Actual movie should not be null", actualResponse, notNullValue());
        assertThat("Code should match", actualResponse.getCode(), equalTo(expectedResponse.getCode()));
        assertThat("Movie title should match", actualResponse.getTitle(), equalTo(expectedResponse.getTitle()));
        assertThat("Movie Code should match", actualResponse.getMovieCode(), equalTo(expectedResponse.getMovieCode()));
    }
}
