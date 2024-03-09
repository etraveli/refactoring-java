package com.mithwick93.refactoring.java.entity;

/**
 * Movie class to store the details of the movie
 *
 * @param title name of the movie
 * @param code  code of the movie
 */
public record Movie(String title, MovieCode code) {
}
