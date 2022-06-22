package se.etraveli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.etraveli.entity.CustomerMovie;

@Repository
public interface CustomerMovieRepository extends JpaRepository<CustomerMovie, Long> {
}
