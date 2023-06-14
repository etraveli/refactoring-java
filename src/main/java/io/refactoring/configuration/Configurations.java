package io.refactoring.configuration;

import io.refactoring.repository.IMoviesRentalRepository;
import io.refactoring.repository.impl.MoviesRentalRepository;
import io.refactoring.service.IRentalCalculator;
import io.refactoring.service.IRentalInfo;
import io.refactoring.service.impl.RentalCalculator;
import io.refactoring.service.impl.RentalInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Value("${filePath}")
    private String filePath;

    @Bean
    public IMoviesRentalRepository moviesRentalRepository(){
        return new MoviesRentalRepository(this.filePath);
    }

    @Bean
    public IRentalCalculator rentalCalculator(IMoviesRentalRepository moviesRentalRepository){
        return new RentalCalculator(moviesRentalRepository);
    }

    @Bean
    public IRentalInfo rentalInfo(IRentalCalculator rentalCalculator, IMoviesRentalRepository moviesRentalRepository){
        return new RentalInfo(rentalCalculator, moviesRentalRepository);
    }

}
