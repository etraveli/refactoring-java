//TODO lombok
public class MovieType {
    private int id;
    private String type;
    private double defaultRentalPrice;
    private int maxRentalDays;
    private double rentalPriceMultiplier;
    private int frequentEnterPointsDays;
    private int frequentEnterPointsBonus;

    public MovieType(int id, String type, double defaultRentalPrice, int maxRentalDays, double rentalPriceMultiplier, int frequentEnterPointsDays, int frequentEnterPointsBonus) {
        this.id = id;
        this.type = type;
        this.defaultRentalPrice = defaultRentalPrice;
        this.maxRentalDays = maxRentalDays;
        this.rentalPriceMultiplier = rentalPriceMultiplier;
        this.frequentEnterPointsDays = frequentEnterPointsDays;
        this.frequentEnterPointsBonus = frequentEnterPointsBonus;
    }

    public double getDefaultRentalPrice() {
        return defaultRentalPrice;
    }


    public int getMaxRentalDays() {
        return maxRentalDays;
    }

    public double getRentalPriceMultiplier() {
        return rentalPriceMultiplier;
    }

    public int getFrequentEnterPointsDays() {
        return frequentEnterPointsDays;
    }

    public int getFrequentEnterPointsBonus() {
        return frequentEnterPointsBonus;
    }

}
