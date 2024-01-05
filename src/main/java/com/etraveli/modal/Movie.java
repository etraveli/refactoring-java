package com.etraveli.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long movieId;

    @Column(name = "movie_code", length = 10)
    private String movieCode;

    @Column(name = "title", length = 250)
    private String title;

    @Column(name = "code", length = 25)
    private String code;

}
