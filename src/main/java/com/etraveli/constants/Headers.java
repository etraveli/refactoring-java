package com.etraveli.constants;

public enum Headers {

    RECORD_FOR("Rental Record for"),
    AMOUNT_OWED("Amount owed is"),
    YOU_EARNED("You earned"),
    FREQUENT_POINTS("frequent points");

    public final String label;

    Headers(String label) {
        this.label = label;
    }
}
