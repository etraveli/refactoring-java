package movie.service.amount;

import movie.dto.MovieCodes;
import movie.service.amount.impl.MovieRentalChildrenAmountService;
import movie.service.amount.impl.MovieRentalNewAmountService;
import movie.service.amount.impl.MovieRentalRegularAmountService;
import movie.service.amount.impl.NotImplementedAmountService;

public class MovieRentalAmountFactory {

    private static MovieRentalNewAmountService movieRentalNewAmountService = new MovieRentalNewAmountService();
    private static MovieRentalChildrenAmountService movieRentalChildrenAmountService = new MovieRentalChildrenAmountService();
    private static MovieRentalRegularAmountService movieRentalRegularAmountService = new MovieRentalRegularAmountService();
    private static NotImplementedAmountService notImplementedAmountService = new NotImplementedAmountService();

    public static MovieRentalAmountService getServiceForMovieCode(String movieCode) {

        if (MovieCodes.REGULAR.key.equals(movieCode)) {
            return movieRentalRegularAmountService;
        }

        if (MovieCodes.NEW.key.equals(movieCode)) {
            return movieRentalNewAmountService;
        }

        if (MovieCodes.CHILDRENS.key.equals(movieCode)) {
            return movieRentalChildrenAmountService;
        }

        return notImplementedAmountService;
    }
}
