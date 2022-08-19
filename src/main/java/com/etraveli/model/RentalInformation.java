package com.etraveli.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class RentalInformation implements Serializable {

    private final String name;
    private final String value;
    @JsonIgnore
    private final int position;

    public RentalInformation(String name, String value, int position) {
        this.name = name;
        this.value = value;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "RentalInformation{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

