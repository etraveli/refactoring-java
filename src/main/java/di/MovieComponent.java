package di;

import dagger.Component;
import handler.RentalStatementHandler;
import javax.inject.Singleton;
import service.RentalService;

@Singleton
@Component(modules = MovieModule.class)
public interface MovieComponent {
  RentalService buildRentalService();
  RentalStatementHandler buildRentalStatementHandler();
}
