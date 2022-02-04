package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Call;
import com.moca.heytaxi.domain.Empty;
import com.moca.heytaxi.dto.CallDTO;
import com.moca.heytaxi.dto.LatLng;
import com.moca.heytaxi.repository.CallRedisRepository;
import com.moca.heytaxi.repository.EmptyRedisRepository;
import com.moca.heytaxi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.PriorityQueue;

@Service
public class CallService {
    private final CallRedisRepository callRedisRepository;
    private final EmptyRedisRepository emptyRedisRepository;

    @Autowired
    public CallService(CallRedisRepository callRedisRepository, EmptyRedisRepository emptyRedisRepository) {
        this.callRedisRepository = callRedisRepository;
        this.emptyRedisRepository = emptyRedisRepository;
    }

    public void enqueueCall(Call call) {
        callRedisRepository.save(call);
    }

    public void dequeueCall(Call call) {
        callRedisRepository.delete(call);
    }

    public void enqueueEmpty(Empty empty) {
        emptyRedisRepository.save(empty);
    }

    public void dequeueEmpty(Empty empty) {
        emptyRedisRepository.save(empty);
    }

    public Call loadCallById(Long id)  throws Exception {
        return callRedisRepository.findById(id).orElseThrow(() -> new Exception("콜을 요청하지 않았습니다."));
    }

    public Empty callTaxi(CallDTO call) {
        PriorityQueue<Empty> empties = new PriorityQueue<>();
        emptyRedisRepository.findAll().forEach(empties::add);
        Iterator<Empty> it = empties.iterator();
        while (it.hasNext()) {
            Empty empty = it.next();
            double dist = Utils.distance(call.getSrc(), empty.getLocation(), "meter");
            if (dist < 500.0) {
                return empty;
            }
        }
        return null;
    }

    public Empty updateEmptyLocationById(Long id, LatLng location) throws Exception {
        Empty empty = emptyRedisRepository.findById(id).orElseThrow(() -> new Exception("빈 차가 아닙니다."));
        empty.setLocation(location);
        return emptyRedisRepository.save(empty);
    }
}
