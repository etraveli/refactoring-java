package com.movierentals.domain;


public enum MovieCategory {
    REGULAR("regular"),
    CHILDREN("children"),
    NEW("new");

    private String category;

    MovieCategory(String category) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }
}
