package com.movierentals;


public enum MovieCategory {
    REGULAR("regular"),
    CHILDRENS("childrens"),
    NEW("new");

    private String category;

    MovieCategory(String category) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }
}
