package com.etravli.payslip.service;


import com.etravli.payslip.model.PaySlip;
import com.etravli.rent.domain.Customer;
import com.etravli.rent.domain.Movie;
import com.etravli.rent.domain.MovieRental;
import com.etravli.rent.enums.CodeEnum;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaySlipServiceTest {


    @Test
    void testBuildStatement(){
        Customer customerRent = createCustomerRent();
        PaySlipService srv= new PaySlipService();
        PaySlip paySlip= srv.buildStatement(customerRent);
        assertEquals(4, paySlip.getDetails().size());
        assertEquals(16, paySlip.getTotalAmount());
        assertEquals(5, paySlip.getTotalEarnedPoints());
  }


    @Test
    void testPrintStatement()  {
        Customer customerRent = createCustomerRent();
        PaySlipService srv= new PaySlipService();
        PaySlip paySlip= srv.buildStatement(customerRent);
        String paySlipAsString=srv.printStatement(paySlip);
        try {
            assertEquals(getExpectedFromFile(),paySlipAsString);
        } catch (URISyntaxException | IOException e) {
            fail();
        }


    }



    /**
     * Create Customer Rent for testing purpose
     * @return
     */
    private  Customer createCustomerRent() {
        Customer customerRent= new Customer("C. U. Stomer");
        customerRent.addRent(new MovieRental(new Movie("F001","You've Got Mail", CodeEnum.REGULAR),3));
        customerRent.addRent(new MovieRental(new Movie("F002","Matrix", CodeEnum.REGULAR),1));
        customerRent.addRent(new MovieRental(new Movie("F003", "Cars", CodeEnum.CHILDREN),2));
        customerRent.addRent(new MovieRental(new Movie("F004", "Fast & Furious X", CodeEnum.NEW),3));
        return customerRent;
    }



    /**
     * Get the expected print of payslip
     */
    private  String  getExpectedFromFile() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("sample.txt");
        Path file = Paths.get(resource.toURI());
        return Files.readString(file);
    }
}
