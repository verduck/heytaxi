package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Call;
import com.moca.heytaxi.domain.Empty;
import com.moca.heytaxi.domain.Reservation;
import com.moca.heytaxi.repository.CallRedisRepository;
import com.moca.heytaxi.repository.TaxiRepository;
import com.moca.heytaxi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final UserRepository userRepository;
    private final TaxiRepository taxiRepository;
    private final CallRedisRepository callRedisRepository;

    @Autowired
    public ReservationService(UserRepository userRepository, TaxiRepository taxiRepository, CallRedisRepository callRedisRepository) {
        this.userRepository = userRepository;
        this.taxiRepository = taxiRepository;
        this.callRedisRepository = callRedisRepository;
    }

    public Reservation create(Empty empty, Call call) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findById(call.getId()).orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다.")));
        reservation.setTaxi(empty);
        reservation.setCall(call);
        callRedisRepository.deleteById(call.getId());
        return reservation;
    }

    public void reject(Reservation reservation) {
        Call call = reservation.getCall();
        callRedisRepository.save(call);
    }
}
