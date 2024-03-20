package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private double totalAmount;

    private int totalFrequentPoints;

    @OneToMany(mappedBy = "rentalInfo", cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RentalDetail> rentalDetails;
}
