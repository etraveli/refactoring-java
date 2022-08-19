package com.etraveli.db.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<MovieRentalEntity> movieRentalEntities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<MovieRentalEntity> getMovieRentalEntities() {
        return movieRentalEntities;
    }

    public void setMovieRentalEntities(List<MovieRentalEntity> movieRentalEntities) {
        this.movieRentalEntities = movieRentalEntities;
    }
}
