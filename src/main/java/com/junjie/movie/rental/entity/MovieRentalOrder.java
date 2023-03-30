package com.junjie.movie.rental.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity(name = "movie_rental_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieRentalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<MovieRental> rentals;

    @Column(name = "order_frequent_points")
    private int orderFrequentPoints;

    @Column(name = "total_expense")
    private double totalExpense;
}
