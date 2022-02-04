package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Empty;
import com.moca.heytaxi.dto.LatLng;
import com.moca.heytaxi.repository.EmptyRedisRepository;
import org.springframework.stereotype.Service;

@Service
public class WaitingService {
    private final EmptyRedisRepository emptyRedisRepository;

    public WaitingService(EmptyRedisRepository emptyRedisRepository) {
        this.emptyRedisRepository = emptyRedisRepository;
    }

    public Empty wait(Empty empty) {
        return emptyRedisRepository.save(empty);
    }

    public Empty updateLocationById(Long id, LatLng location) throws Exception {
        Empty empty = emptyRedisRepository.findById(id).orElseThrow(() -> new Exception("대기중이 아닙니다."));
        empty.setLocation(location);
        return emptyRedisRepository.save(empty);
    }

}
