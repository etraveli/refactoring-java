package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalInfo rentalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private int movieRentalDays;

    private double movieRentalAmount;
}
