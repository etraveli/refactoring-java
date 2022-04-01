package di;

import dagger.Module;
import dagger.Provides;
import dao.MovieRepo;
import javax.inject.Singleton;
import service.BonusPointsCalculator;
import service.PriceCalculator;

@Module
public class MovieModule {

  @Provides
  @Singleton
  public MovieRepo provideMovieRepo() {
    return new MovieRepo();
  }

  @Provides
  @Singleton
  public BonusPointsCalculator provideBonusPointsCalculator(MovieRepo movieRepo) {
    return new BonusPointsCalculator(movieRepo);
  }

  @Provides
  @Singleton
  public PriceCalculator providePriceCalculator(MovieRepo movieRepo) {
    return new PriceCalculator(movieRepo);
  }
}
