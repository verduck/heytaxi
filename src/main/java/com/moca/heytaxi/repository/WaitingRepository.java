package com.moca.heytaxi.repository;

import com.moca.heytaxi.domain.Waiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class WaitingRepository {
    private RedisTemplate<String, Object> redisTemplate;
    private ZSetOperations<String, Object> zSetOps;

    @Autowired
    public WaitingRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOps = redisTemplate.opsForZSet();
    }

    public boolean add(Waiting waiting) {
        return zSetOps.add(waiting.getId(), waiting, Timestamp.valueOf(waiting.getTimestamp()).toInstant().toEpochMilli());
    }
}
