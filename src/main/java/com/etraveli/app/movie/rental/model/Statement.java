package com.etraveli.app.movie.rental.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for a generated Statement about movie rentals
 * (b'cz it might need to include more details in future)
 */
public class Statement {

    private String customerName;
    private double totalPrice;
    private int frequentPoints;
    private List<StatementMovieItem> movieItems;
    // keep current date time when the statement generates, b'cz statement for the same customer can change over time
    private LocalDateTime currentTime;

    public Statement(String customerName) {
        this.customerName = customerName;
        this.movieItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.frequentPoints = 0;
        this.currentTime = LocalDateTime.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getFrequentPoints() {
        return frequentPoints;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public List<StatementMovieItem> getMovieItems() {
        return movieItems;
    }

    public void addMovieItem(StatementMovieItem movieItem) {
        this.movieItems.add(movieItem);
        this.totalPrice += movieItem.getPrice();
        this.frequentPoints += movieItem.getPoints();
    }
}
