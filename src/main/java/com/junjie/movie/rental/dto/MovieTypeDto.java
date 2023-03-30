package com.junjie.movie.rental.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MovieTypeDto {
    NEW("new"),
    REGULAR("regular"),
    CHILDREN("children");

    private String name;
}
