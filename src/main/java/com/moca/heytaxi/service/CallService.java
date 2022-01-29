package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.dto.CallDTO;
import com.moca.heytaxi.repository.WaitingRedisRepository;
import com.moca.heytaxi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.PriorityQueue;

@Service
public class CallService {
    private final WaitingRedisRepository waitingRedisRepository;

    @Autowired
    public CallService(WaitingRedisRepository waitingRedisRepository) {
        this.waitingRedisRepository = waitingRedisRepository;
    }

    public Waiting callTaxi(CallDTO call) {
        PriorityQueue<Waiting> waitings = new PriorityQueue<>();
        waitingRedisRepository.findAll().forEach(waitings::add);
        Iterator<Waiting> it = waitings.iterator();
        while (it.hasNext()) {
            Waiting waiting = it.next();
            double dist = Utils.distance(call.getSrc(), waiting.getLocation(), "meter");
            if (dist < 500.0) {
                return waiting;
            }
        }
        return null;
    }

    public Waiting waitCall(Waiting waiting) {
        waitingRedisRepository.save(waiting);
        return waiting;
    }
}
