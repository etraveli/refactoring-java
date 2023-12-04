package com.etraveli.assignments.refactoring.model;

import com.etraveli.assignments.refactoring.util.MovieCategory;

public record Movie(String movieId, String title, MovieCategory movieCategory){}
