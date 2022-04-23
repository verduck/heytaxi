package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxiService {
    private final TaxiRepository taxiRepository;

    @Autowired
    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public Taxi loadByUserId(Long userId) throws Exception {
        return taxiRepository.findByUserId(userId).orElseThrow(() -> new Exception("등록된 택시가 없습니다."));
    }

    public Taxi loadByCarNumber(String carNumber) throws Exception {
        return taxiRepository.findByCarNumber(carNumber).orElseThrow(() -> new Exception("해당 택시번호로 택시를 찾을 수 없습니다."));
    }

    public Taxi createTaxi(Taxi taxi) {
        try {
            taxi = loadByUserId(taxi.getUser().getId());
            taxi = loadByCarNumber(taxi.getCarNumber());
            return taxi;
        } catch (Exception e) {
            return taxiRepository.save(taxi);
        }
    }

    public Taxi updateTaxi(Taxi taxi) {
        return taxiRepository.save(taxi);
    }

    public void deleteTaxi(Taxi taxi) {
        taxiRepository.delete(taxi);
    }

    public void deleteTaxiByUserId(Long userId) {
        taxiRepository.deleteByUserId(userId);
    }
}
