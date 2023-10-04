package test;

import pojo.Customer;
import pojo.MovieRental;
import service.statement.GenerateStatement;
import utils.Constants;

import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static String test() {

        try {
            // test 1 comparing the entire string
            test1();

            /* test 2 comparing the entire string with same user and all movie
             categories and satisfying all conditions */
            test2();

            //sample contains check;
            test3();

        } catch (Exception e) {
            System.out.println(e);
        }
        return Constants.SUCCESS;
    }

    private static void test1 () {
        String expectedTest1 = "Rental Record for C. U. Stomer\n"+
                "\tYou've Got Mail\t3.5\n"+
                "\tMatrix\t2.0\n"+
                "Amount owed is 5.5\n"+
                "You earned 2 frequent points\n";

        List<MovieRental> movieRentals  = Arrays.asList((new MovieRental("F001", 3)),
                (new MovieRental("F002", 1)));

        Customer objCustomer = new Customer("C. U. Stomer", movieRentals);

        String resultTest1 = new GenerateStatement().statement(objCustomer);

        validateResult(expectedTest1, resultTest1);
    }

    private static void test2 () {
        String expectedTest2 = "Rental Record for C. U. Stomer\n"+
                "\tYou've Got Mail\t6.5\n"+
                "\tCars\t9.0\n"+
                "\tFast & Furious X\t9.0\n"+
                "Amount owed is 24.5\n"+
                "You earned 4 frequent points\n";

        List<MovieRental> movieRentalsTest2  = Arrays.asList((new MovieRental("F001", 5)),
                (new MovieRental("F003", 8)),
                (new MovieRental("F004", 3)));

        Customer objCustomer = new Customer("C. U. Stomer", movieRentalsTest2);

        String resultTest2 = new GenerateStatement().statement(objCustomer);

        validateResult(expectedTest2, resultTest2);
    }

    private static void test3 () {

        String expectedTest3 = "Rental Record for C. U. Stomer\n"+
                "\tYou've Got Mail\t3.5\n"+
                "\tMatrix\t2.0\n"+
                "Amount owed is 5.5\n"+
                "You earned 2 frequent points\n";

        List<MovieRental> movieRentals  = Arrays.asList((new MovieRental("F001", 3)),
                (new MovieRental("F002", 1)));

        Customer objCustomer = new Customer("C. U. Stomer", movieRentals);

        String resultTest3 = new GenerateStatement().statement(objCustomer);

        if(!resultTest3.contains("\tYou've Got Mail\t3.5"))
            errorStatement(expectedTest3, resultTest3);
        if(! resultTest3.contains("Amount owed is 5.5"))
            errorStatement(expectedTest3, resultTest3);

        printResult(resultTest3);
    }

    private static  void validateResult (String expected, String result){
        if (expected.equals(result)) {
            printResult(result);
        } else {
            errorStatement(expected, result);
        }
    }
    private static void errorStatement (String expected, String result) {
        String error =  String.format("Expected:%n%s%n%nGot:%n%s%n", expected, result);
        throw  new AssertionError(error);
    }

    private static void printResult (String result) {
        System.out.println(result);
        System.out.println("----------------------------------------");
    }
}
