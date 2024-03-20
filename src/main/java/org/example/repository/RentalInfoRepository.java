package org.example.repository;

import org.example.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {
}
