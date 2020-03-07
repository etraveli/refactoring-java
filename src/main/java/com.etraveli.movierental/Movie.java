package com.etraveli.movierental;

import com.etraveli.movierental.enumeration.CodeType;
import com.sun.istack.internal.NotNull;

public class Movie {
    @NotNull
    private String title;
    @NotNull
    private CodeType code;

    public Movie(String title, CodeType code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public CodeType getCode() {
        return code;
    }
}
