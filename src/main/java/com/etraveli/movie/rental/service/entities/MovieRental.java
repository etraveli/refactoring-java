package com.etraveli.movie.rental.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movierental")
public class MovieRental {
        @Id
        private Long id;
        private BigDecimal rentalAmount;
        private Integer noOfDays;
        @ManyToOne
        private Customer customer;
        @ManyToOne
        private Movie movie;
}
