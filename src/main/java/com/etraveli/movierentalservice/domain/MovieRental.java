package com.etraveli.movierentalservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MovieRental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int rentalDays;
    private LocalDate rentalDate;
    private boolean isAvailable = true;
    private Movie movie;
    private Customer customer;
}
