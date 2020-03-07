package com.etraveli.movierental.builder;

import com.etraveli.movierental.Customer;
import com.etraveli.movierental.MovieRental;

import java.util.List;

public class CustomerBuilder {
    @FunctionalInterface
    public interface FromStep {
        ToStep name(String name);
    }

    @FunctionalInterface
    public interface ToStep {
        Build rentals(List<MovieRental> rentals);
    }

    @FunctionalInterface
    public interface Build {
        Customer build();
    }

    public static FromStep builder() {
        return new Steps();
    }

    public static class Steps implements Build, FromStep, ToStep {
        private String name;
        private List<MovieRental> rentals;


        @Override
        public Customer build() {
            return new Customer(name, rentals);
        }

        @Override
        public Build rentals(List<MovieRental> rentals) {
            this.rentals = rentals;
            return this;
        }

        @Override
        public ToStep name(String name) {
            this.name = name;
            return this;
        }
    }
}