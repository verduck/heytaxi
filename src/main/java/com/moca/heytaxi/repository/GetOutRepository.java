package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.GetOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetOutRepository extends JpaRepository<GetOut, Long> {
}
