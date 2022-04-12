package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    Optional<Taxi> findByUserId(Long userId);
    Optional<Taxi> findByCarNumber(String carNumber);
    void deleteByUserId(Long userId);
}
