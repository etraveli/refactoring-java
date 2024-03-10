package com.mithwick93.refactoring.java.entity;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;
import com.mithwick93.refactoring.java.service.rentalstrategy.impl.ChildrenMovieRentalStrategy;
import com.mithwick93.refactoring.java.service.rentalstrategy.impl.NewReleaseMovieRentalStrategy;
import com.mithwick93.refactoring.java.service.rentalstrategy.impl.RegularMovieRentalStrategy;

import java.util.function.Supplier;

/**
 * Movie class to store the details of the movie.
 *
 * @param title name of the movie
 * @param code  code of the movie
 */
public record Movie(String title, MovieCode code) {
    /**
     * MovieCode enum represents the various types of movies available along
     * with their rental strategy.
     */
    public enum MovieCode {
        REGULAR(RegularMovieRentalStrategy::new),
        NEW_RELEASE(NewReleaseMovieRentalStrategy::new),
        CHILDREN(ChildrenMovieRentalStrategy::new);

        private final RentalStrategy rentalStrategy;

        MovieCode(final Supplier<RentalStrategy> rentalStrategySupplier) {
            this.rentalStrategy = rentalStrategySupplier.get();
        }

        /**
         * Get the rental strategy for the movie code.
         *
         * @return rental strategy for the movie code
         */
        public RentalStrategy rentalStrategy() {
            return rentalStrategy;
        }
    }
}
