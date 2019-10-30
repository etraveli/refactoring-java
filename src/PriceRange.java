public enum PriceRange {
    REGULAR(2) {
        @Override
        public double totalCost(int days) {
            return (days > 2) ? ((days - 2) * 1.5) + getPrice() : getPrice();
        }
    },
    NEW(3) {
        @Override
        public double totalCost(int days) {
            return (days * getPrice());
        }
    },
    CHILDREN(1.5) {
        @Override
        public double totalCost(int days) {
            return (days > 3) ? ((days - 3) * 1.5) + getPrice() : getPrice();
        }
    };

    private final double price;

    public abstract double totalCost(int days);

    private PriceRange(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
