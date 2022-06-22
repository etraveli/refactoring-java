package se.etraveli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.etraveli.entity.CustomerMovie;
import se.etraveli.repository.CustomerMovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerMovieServiceImpl implements CustomerMovieService {

    private final CustomerMovieRepository customerMovieRepository;

    @Override
    public List<CustomerMovie> createCustomerMovieAll(List<CustomerMovie> customerMovie) {
        return customerMovieRepository.saveAll(customerMovie);
    }
}
