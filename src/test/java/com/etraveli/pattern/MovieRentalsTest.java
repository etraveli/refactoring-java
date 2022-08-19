package com.etraveli.pattern;

import com.etraveli.pattern.types.Children;
import com.etraveli.pattern.types.New;
import com.etraveli.pattern.types.Regular;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovieRentalsTest {

    @Mock
    private New newMovieType;

    @Mock
    private Children childrenMovieType;

    @Mock
    private Regular regularMovieType;

    @InjectMocks
    private MovieRentals movieRentalFactory;

    @Test
    public void test_movie_rental_factory() {
        assertEquals(movieRentalFactory.getAllMovieRentalTypes().size(), 3);
    }

}