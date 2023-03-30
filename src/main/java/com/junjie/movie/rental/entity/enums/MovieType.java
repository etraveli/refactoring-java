package com.junjie.movie.rental.entity.enums;


public enum MovieType {
    NEW("new"),
    REGULAR("regular"),
    CHILDREN("children");

    private String name;

    private MovieType(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }

    public static MovieType valueof(String name) {
        for (MovieType type : MovieType.values()) {
            if (type.getValue().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MovieType: " + name);
    }

}
