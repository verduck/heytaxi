package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.GetIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetInRepository extends JpaRepository<GetIn, Long> {
}
