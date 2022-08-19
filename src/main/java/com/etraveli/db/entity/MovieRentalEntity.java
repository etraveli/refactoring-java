package com.etraveli.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_RENTAL")
public class MovieRentalEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "movie_id", nullable = false)
    private String movieId;

    @Column(name = "days", nullable = false)
    private int code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
