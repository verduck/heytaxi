package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.repository.WaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    private WaitingRepository waitingRepository;

    @Autowired
    public CallService(WaitingRepository waitingRepository) {
        this.waitingRepository = waitingRepository;
    }

    public Waiting waitCall(Waiting waiting) {
        return waitingRepository.save(waiting);
    }
}
