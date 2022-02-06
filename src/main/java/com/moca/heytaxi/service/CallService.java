package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Call;
import com.moca.heytaxi.domain.Empty;
import com.moca.heytaxi.repository.CallRedisRepository;
import com.moca.heytaxi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.PriorityQueue;

@Service
public class CallService {
    private final CallRedisRepository callRedisRepository;

    @Autowired
    public CallService(CallRedisRepository callRedisRepository) {
        this.callRedisRepository = callRedisRepository;
    }

    public void enqueueCall(Call call) {
        callRedisRepository.save(call);
    }

    public void dequeueCall(Call call) {
        callRedisRepository.delete(call);
    }

    public Call loadCallById(Long id)  throws Exception {
        return callRedisRepository.findById(id).orElseThrow(() -> new Exception("콜을 요청하지 않았습니다."));
    }

    public Call tryReservation(Empty empty) {
        PriorityQueue<Call> calls = new PriorityQueue<>();
        callRedisRepository.findAll().forEach(calls::add);
        Iterator<Call> it = calls.iterator();
        while (it.hasNext()) {
            Call call = it.next();
            double dist = Utils.distance(call.getSrc(), empty.getLocation(), "kilometer");
            if (dist <= 1.0) {
                return call;
            }
        }
        return null;
    }
}
