package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.dto.CallDTO;
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

    public Waiting callTaxi(CallDTO call) {
        return null;
    }

    public Waiting waitCall(Waiting waiting) {
        waitingRepository.add(waiting);
        return waiting;
    }
}
