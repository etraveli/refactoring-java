package com.example.rentalapi.constants;
public class RentalConstants {
    public static final String CUSTOMER_NULL_MESSAGE = "Customer cannot be null";
    public static final String CUSTOMER_NAME_NULL_MESSAGE = "Customer name cannot be null";
    public static final String CUSTOMER_RENTALS_NULL_MESSAGE = "Customer rentals cannot be null";

    public static final String RENTAL_NULL_MESSAGE = "Rental cannot be null";
    public static final String RENTAL_MOVIE_ID_NULL_MESSAGE = "Rental movieId cannot be null";

    public static final String MOVIE_NULL_MESSAGE = "Movie cannot be null";
    public static final String MOVIE_TITLE_NULL_MESSAGE = "Movie title cannot be null";
    public static final String MOVIE_CODE_NULL_MESSAGE = "Movie code cannot be null";

    public static final String INVALID_CUSTOMER_EXCEPTION_MESSAGE = "Invalid customer information.";
    public static final String INVALID_MOVIE_EXCEPTION_MESSAGE = "Invalid movie information.";
    public static final String INVALID_MOVIE_CODE_EXCEPTION_MESSAGE = "Invalid movie code.";
    public static final String UNEXPECTED_EXCEPTION_MESSAGE = "An unexpected error occurred: ";
    public static final String ERROR_TEXT = "Error: ";
    
    public static final String RENTAL_RECORD_INTRO = "Rental Record for ";
    public static final String RENTAL_FREQUENT_POINTS = " frequent points";
    public static final String RENTAL_YOU_EARNED = "You earned ";
    public static final String RENTAL_AMOUNT_OWED_IS = "Amount owed is ";
    
    public static final String MOVIE_CODE_REGULAR = "regular";
    public static final String MOVIE_CODE_NEW = "new";
    public static final String MOVIE_CODE_CHILDRENS = "childrens";
    
    
    public static final String TEST_CASE_DISPLAYNAME_VALID_CUSTOMER = "Generate Rental Statement with Valid Customer";
    public static final String TEST_CASE_DISPLAYNAME_NULL_CUSTOMER = "Generate Rental Statement with Null Customer - Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_INVALID_CUSTOMER_NAME = "Generate Rental Statement with Invalid Customer Name - Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_NULL_CUSTOMER_RENTAL = "Generate Rental Statement with Null Customer Rental - Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_INVALID_MOVIE = "Generate Rental Statement with Invalid Movie - Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_GET_ALL_MOVIE = "Get All Movies - Should Return Correct Map";
    public static final String TEST_CASE_DISPLAYNAME_INITIALIZE_DEFAULT_MOVIES = "Initialize Default Movies Test";
    public static final String TEST_CASE_DISPLAYNAME_SAVE_MOVIES = "Save Movie Test";
    public static final String TEST_CASE_DISPLAYNAME_GET_ALL_MOVIES = "Get All Movies Test";
    public static final String TEST_CASE_DISPLAYNAME_SAVE_MOVIE_WITH_INVALID_CODE = "Save Movie with Invalid Code Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_GET_ALL_MOVIES_EMPTY = "Get All Movies with Empty Database";
    public static final String TEST_CASE_DISPLAYNAME_SAVE_MOVIES_WITH_INVALID_CODE = "Save Movie with Invalid Code Should Throw Exception";
    public static final String TEST_CASE_DISPLAYNAME_INVALID_CUSTOMER_EXCEPTION = "Handle Invalid Customer Exception - Should Return Bad Request";
    public static final String TEST_CASE_DISPLAYNAME_INVALID_MOVIE_EXCEPTION = "Handle Invalid Movie Exception - Should Return Bad Request";
    public static final String TEST_CASE_DISPLAYNAME_UNKNOWN_EXCEPTION = "Handle Unknown Exception - Should Return Internal Server Error";
    public static final String TEST_CASE_DISPLAYNAME_INVALID_MOVIE_CODE_EXCEPTION = "Handle Invalid Movie Code Exception - Bad Request";
    

        
    public static final String PROCESSED_RENTAL_STATEMENT = "\t%s\t%f";
    public static final String RENTAL_RESULT_FORMAT = "%s%s%n%s%n%s%.2f%n%s%d%s";
    
}
