package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.dto.LatLng;
import com.moca.heytaxi.repository.WaitingRedisRepository;
import org.springframework.stereotype.Service;

@Service
public class WaitingService {
    private final WaitingRedisRepository waitingRedisRepository;

    public WaitingService(WaitingRedisRepository waitingRedisRepository) {
        this.waitingRedisRepository = waitingRedisRepository;
    }

    public Waiting wait(Waiting waiting) {
        return waitingRedisRepository.save(waiting);
    }

    public Waiting updateLocationById(Long id, LatLng location) throws Exception {
        Waiting waiting = waitingRedisRepository.findById(id).orElseThrow(() -> new Exception("대기중이 아닙니다."));
        waiting.setLocation(location);
        return waitingRedisRepository.save(waiting);
    }

}
