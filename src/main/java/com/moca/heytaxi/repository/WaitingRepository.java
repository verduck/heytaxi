package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.Waiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRepository extends CrudRepository<Waiting, Long> {
}
