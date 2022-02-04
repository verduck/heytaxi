package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.Call;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRedisRepository extends CrudRepository<Call, Long> {
}
