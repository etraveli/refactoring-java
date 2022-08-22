package com.etraveli.pattern;

import com.etraveli.constants.MovieType;
import com.etraveli.pattern.types.Children;
import com.etraveli.pattern.types.New;
import com.etraveli.pattern.types.Regular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class MovieRentalsTest {

    @Mock
    private New newMovieType;

    @Mock
    private Children childrenMovieType;

    @Mock
    private Regular regularMovieType;

    @InjectMocks
    private MovieRentals movieRentals;

    @BeforeEach
    public void setUp() {
        lenient().when(childrenMovieType.code()).thenReturn(MovieType.CHILDREN.toString());
        lenient().when(newMovieType.code()).thenReturn(MovieType.NEW.toString());
        lenient().when(regularMovieType.code()).thenReturn(MovieType.REGULAR.toString());
    }

    @Test
    public void test_movie_rental_children() {
        assertEquals(movieRentals.getMovieRentalType(MovieType.CHILDREN.toString()).get().code(), MovieType.CHILDREN.toString());
    }

    @Test
    public void test_movie_rental_regular() {
        assertEquals(movieRentals.getMovieRentalType(MovieType.REGULAR.toString()).get().code(), MovieType.REGULAR.toString());
    }

    @Test
    public void test_movie_rental_new() {
        assertEquals(movieRentals.getMovieRentalType(MovieType.NEW.toString()).get().code(), MovieType.NEW.toString());
    }

}