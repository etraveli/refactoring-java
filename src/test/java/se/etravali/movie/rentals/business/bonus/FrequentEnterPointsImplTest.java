package se.etravali.movie.rentals.business.bonus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.model.MovieCode;
import se.etraveli.movie.rentals.business.bonus.FrequentEnterPoints;
import se.etraveli.movie.rentals.business.bonus.FrequentEnterPointsImpl;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class FrequentEnterPointsImplTest {

    FrequentEnterPoints frequentEnterPointsService = new FrequentEnterPointsImpl();

    @Test
    public void regularMovieBonus(){
        Assertions.assertEquals(1, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.REGULAR, 2));
        Assertions.assertEquals(1, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.REGULAR, 4));
    }

    @Test
    public void newMovieBonus(){
        Assertions.assertEquals(1, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.NEW, 2));
        Assertions.assertEquals(2, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.NEW, 4));
    }

    @Test
    public void childrensMovieBonus(){
        Assertions.assertEquals(1, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.CHILDRENS, 2));
        Assertions.assertEquals(1, frequentEnterPointsService.getFrequentEnterPoints(MovieCode.CHILDRENS, 4));
    }
}
