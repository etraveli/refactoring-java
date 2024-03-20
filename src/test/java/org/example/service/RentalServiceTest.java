package org.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.example.entity.*;
import org.example.repository.CustomerRepository;
import org.example.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testStatement() {
        Customer testCustomer = customerRepository.findByName("C. U. Stomer");
        Movie testMovie1 = movieRepository.findByCode("F001");
        Movie testMovie2 = movieRepository.findByCode("F002");

        List<RentalDetail> rentalDetails = new ArrayList<>();

        RentalDetail testRentalDetail1 = new RentalDetail(null,null,testMovie1,3,0);
        rentalDetails.add(testRentalDetail1);

        RentalDetail testRentalDetail2 = new RentalDetail(null,null,testMovie2,1,0);
        rentalDetails.add(testRentalDetail2);

        RentalInfo rentalInfo = new RentalInfo(null, testCustomer, 0, 0, rentalDetails);

        //save rental info for future retrieval
        rentalInfo = rentalService.saveRentalInfo(rentalInfo);

        //print statement from saved rentalInfo
        String result = rentalService.statement(rentalInfo);

        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        assertEquals(expected, result);
    }
}