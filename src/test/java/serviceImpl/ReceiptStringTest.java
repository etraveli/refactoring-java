package serviceImpl;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.ReceiptDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import constant.RentalStatementConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceiptStringTest {


    private ReceiptString receiptString;

    @BeforeEach
    void setUp() {
        receiptString = new ReceiptString();
        receiptString.setReceiptDetail(mockReceiptDetail());
    }

    private ReceiptDetail mockReceiptDetail() {
        ReceiptDetail receiptDetail = mock(ReceiptDetail.class);
        Customer customer = mock(Customer.class);

        when(customer.getName()).thenReturn("John Doe");

        when(receiptDetail.getCustomer()).thenReturn(customer);

        when(receiptDetail.getTotalAmount()).thenReturn(25.0);
        when(receiptDetail.getFrequentEnterPoints()).thenReturn(2);
        return receiptDetail;
    }

    @Test
    void given_Generate_When_CustomerIsNull_Then_ReturnErrorMessageForNullCustomer() {
        ReceiptDetail receiptDetail = receiptString.getReceiptDetail();
        when(receiptDetail.getCustomer()).thenReturn(null);

        assertEquals(RentalStatementConstants.PLEASE_SELECT_CUSTOMER, receiptString.generate());
    }

    @Test
    void given_Generate_When_RentalsIsEmpty_Then_ReturnErrorMessageForEmptyRentals() {
        ReceiptDetail receiptDetail = receiptString.getReceiptDetail();
        when(receiptDetail.getCustomer().getRentals()).thenReturn(Collections.emptyList());

        assertEquals(
                RentalStatementConstants.RENTAL_RECORD_FOR + receiptDetail.getCustomer().getName() +
                        "\n" + RentalStatementConstants.IS_ZERO,
                receiptString.generate()
        );
    }

    @Test
    void given_Generate_When_RentalsIsNotEmpty_Then_ReturnMessageForRentals() {
        ReceiptDetail receiptDetail = receiptString.getReceiptDetail();
        List<MovieRental> rentalList = mockRentalList();
        when(receiptDetail.getCustomer().getRentals()).thenReturn(rentalList);


        assertEquals(
                RentalStatementConstants.RENTAL_RECORD_FOR + receiptDetail.getCustomer().getName() + "\n" +
                        "\tMovie1\t10.0\n" +
                        "\tMovie2\t15.0\n" +
                        RentalStatementConstants.AMOUNT_OWED_IS + receiptDetail.getTotalAmount() + "\n" +
                        RentalStatementConstants.YOU_EARNED + receiptDetail.getFrequentEnterPoints() +
                        RentalStatementConstants.FREQUENT_POINTS + "\n",
                receiptString.generate()
        );
    }


    private static Movie createMockMovie(String title) {
        Movie movie = mock(Movie.class);
        when(movie.getTitle()).thenReturn(title);
        return movie;
    }

    public static List<MovieRental> mockRentalList() {
        List<MovieRental> rentalList = new ArrayList<>();

        rentalList.add(createMovieRental( 3,"Movie1",10));
        rentalList.add(createMovieRental( 5,"Movie2",15));

        return rentalList;
    }

    private static MovieRental createMovieRental( int days,String movieTitle,double rentAmount) {
        MovieRental movieRental = mock(MovieRental.class);

        when(movieRental.getDays()).thenReturn(days);
        when(movieRental.getRentAmount()).thenReturn(rentAmount);

        Movie movie = createMockMovie(movieTitle);
        when(movieRental.getMovie()).thenReturn(movie);

        return movieRental;
    }
}
