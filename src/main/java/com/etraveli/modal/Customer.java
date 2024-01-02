package com.etraveli.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long customerId;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "id_number", length = 25)
    private String IdNumber;

    @Column(name = "birth_year")
    private Integer birthYear;
}
