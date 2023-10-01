package com.etraveli.movie.rental.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
        @Id
        private Long id;
        private String title;
        private String genre;
        private BigDecimal rentalPrice;
}
