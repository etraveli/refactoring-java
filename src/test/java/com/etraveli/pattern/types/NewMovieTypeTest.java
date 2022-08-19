package com.etraveli.pattern.types;

import com.etraveli.constants.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class NewMovieTypeTest {

    @InjectMocks
    private New newMovieType;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(newMovieType, "rentalPrice", 3);
    }

    @Test
    void test_new_movie_rental_price() {
        assertEquals(newMovieType.getRentalPrice(3), 9.0);
    }

    @Test
    void test_code() {
        assertEquals(MovieType.NEW.toString(), newMovieType.code());
    }
}