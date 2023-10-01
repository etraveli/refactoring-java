package com.etraveli.movie.rental.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
        @Id
        private Long id;
        private String name;
        private int loyaltyPoints;
}
