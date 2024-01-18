package com.etraveli.util;

public class Constants {
    private Constants() {
    }

    public static final String RENTAL_RECORD_CUSTOMER = "Rental Record for %s\n";
    public static final String RENTAL_RECORD_ENTRY = "\t%s\t%.1f\n";
    public static final String RENTAL_RECORD_OWNED = "Amount owned is %.1f\n";
    public static final String RENTAL_RECORD_FREQUENT_POINTS = "You earned %d frequent points\n";
    public static final String RENTAL_RECORD_INVALID_CUSTOMER = "Invalid Customer, cannot be null";

    public static final String RENTAL_RECORD_LOGGING_SKIP = "Customer [{}] has invalid rental record for title [{}] with code [{}] and with [{}] days";
    public static final String RENTAL_RECORD_LOGGING_INVALID_CUSTOMER = RENTAL_RECORD_INVALID_CUSTOMER;

    public static final String CUSTOMER_REPO_MISSING_CUSTOMER = "Customer not found for id [{}]";
    public static final String MOVIE_REPO_MISSING_CUSTOMER = "Customer not found for id [{}]";
}
