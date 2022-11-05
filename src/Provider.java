
//Any dependency injection library can be used
public class Provider {
    static RentalLedger rentalLedger;
    static BasicRentCalculator basicRentCalculator;
    static FrequentEnterPointsCalculator frequentEnterPointsCalculator;
    static MovieLibrary movieLibrary;

    //initialization should be carried taking the dependencies into account
    static {
        movieLibrary = new MovieLibrary();
        rentalLedger = new RentalLedger();
        basicRentCalculator = new BasicRentCalculator();
        frequentEnterPointsCalculator = new FrequentEnterPointsCalculator();
    }

    public static Provider getInstance(){
        return new Provider();
    }

    public RentalLedger getRentalLedger(){
        return rentalLedger;
    }

    public BasicRentCalculator getBasicRentCalculator(){
        return basicRentCalculator;
    }

    public FrequentEnterPointsCalculator getFrequentEnterPointsCalculator(){
        return frequentEnterPointsCalculator;
    }

    public MovieLibrary getMovieLibrary(){
        return movieLibrary;
    }

}
