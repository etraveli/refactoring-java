package com.junjie.movie.rental.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity(name = "movie_rental")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "rent_days", nullable = false)
    private Integer rentDays;

    @NonNull
    @Column(name = "expense", nullable = false)
    private Double expense;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NonNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private MovieRentalOrder movieRentalOrder;
}
