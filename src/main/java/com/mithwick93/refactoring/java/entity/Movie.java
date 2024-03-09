package com.mithwick93.refactoring.java.entity;

/**
 * Movie class to store the details of the movie.
 *
 * @param title name of the movie
 * @param code  code of the movie
 */
public record Movie(String title, MovieCode code) {
    /**
     * MovieCode enum to hold the base rate, daily rate and
     * max base rate days for each movie code.
     */
    public enum MovieCode {
        // MOVIE_CODE(
        //      "Base Rate",
        //      "Daily Rate",
        //      "Max Base Rate Days",
        //      "Is Eligible For More Frequent Points",
        //      "Frequent Points",
        //      "Days Threshold For More Frequent Points",
        //      "Additional Frequent Points"
        //)
        REGULAR(2, 1.5, 2, false, 1, 0, 0),
        NEW_RELEASE(0, 3, 0, true, 1, 2, 1),
        CHILDREN(1.5, 1.5, 3, false, 1, 0, 0);

        /**
         * Base rate for the movie code.
         */
        private final double baseRate;
        /**
         * Daily rate for the movie code.
         */
        private final double dailyRate;
        /**
         * Max days to use base rate for the movie code.
         */
        private final int maxBaseRateDays;

        /**
         * Is the movie code eligible for more frequent points.
         */
        private final boolean isEligibleForMoreFrequentPoints;

        /**
         * Frequent points for the movie code.
         */
        private final int frequentPoints;

        /**
         * Days threshold to qualify for more frequent points.
         */
        private final int daysThresholdForMoreFrequentPoints;

        /**
         * Additional frequent points.
         */
        private final int additionalFrequentPoints;


        MovieCode(
                final double baseRate,
                final double dailyRate,
                final int maxBaseRateDays,
                final boolean isEligibleForMoreFrequentPoints,
                final int frequentPoints,
                final int daysThresholdForMoreFrequentPoints,
                final int additionalFrequentPoints
        ) {
            this.baseRate = baseRate;
            this.dailyRate = dailyRate;
            this.maxBaseRateDays = maxBaseRateDays;
            this.isEligibleForMoreFrequentPoints
                    = isEligibleForMoreFrequentPoints;
            this.frequentPoints = frequentPoints;
            this.daysThresholdForMoreFrequentPoints
                    = daysThresholdForMoreFrequentPoints;
            this.additionalFrequentPoints = additionalFrequentPoints;
        }

        /**
         * @return base rate for the movie code
         */
        public double getBaseRate() {
            return baseRate;
        }

        /**
         * @return daily rate for the movie code
         */
        public double getDailyRate() {
            return dailyRate;
        }

        /**
         * @return max days to use base rate for the movie code
         */
        public int getMaxBaseRateDays() {
            return maxBaseRateDays;
        }

        /**
         * @return is the movie code eligible for more frequent points
         */
        public boolean isEligibleForMoreFrequentPoints() {
            return isEligibleForMoreFrequentPoints;
        }

        /**
         * @return regular frequent points for the movie code
         */
        public int getFrequentPoints() {
            return frequentPoints;
        }

        /**
         * @return days threshold to qualify for more frequent points
         */
        public int getDaysThresholdForMoreFrequentPoints() {
            return daysThresholdForMoreFrequentPoints;
        }

        /**
         * @return additional frequent points for the movie code
         */
        public int getAdditionalFrequentPoints() {
            return additionalFrequentPoints;
        }
    }
}
