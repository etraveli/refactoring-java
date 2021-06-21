package model;

public enum RentalCategory {
    CHILDRENS(1.5) {
        @Override
        public double getCost(int days) {
            return (days > CHILDRENS_DAYS_LIMIT) ? ((days - CHILDRENS_DAYS_LIMIT) * CHILDRENS_ADDITIONAL_COST) + getBasePrice() : getBasePrice();
        }
    },
    NEW(3) {
        @Override
        public double getCost(int days) {
            return (days * getBasePrice());
        }
    },
    REGULAR(2) {
        @Override
        public double getCost(int days) {
            return (days > REGULAR_DAYS_LIMIT) ? ((days - REGULAR_DAYS_LIMIT) * REGULAR_ADDITIONAL_COST) + getBasePrice() : getBasePrice();
        }
    };

    // avoiding magic numbers
    private static final int CHILDRENS_DAYS_LIMIT = 3;
    private static final double CHILDRENS_ADDITIONAL_COST = 1.5;

    private static final int REGULAR_DAYS_LIMIT = 2;
    private static final double REGULAR_ADDITIONAL_COST = 1.5;

    private final double basePrice;

    RentalCategory(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    // better than switch in case of adding a additional value
    public abstract double getCost(int days);
}
