package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.Empty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmptyRedisRepository extends CrudRepository<Empty, Long> {
}
