package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class TaxiService {
    private TaxiRepository taxiRepository;

    @Autowired
    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public Taxi loadByUserId(Long userId) {
        Optional<Taxi> optionalTaxi = taxiRepository.findByUserId(userId);
        if (optionalTaxi.isEmpty()) {
            return null;
        }
        return optionalTaxi.get();
    }

    public Taxi createTaxi(Taxi taxi) {
        return taxiRepository.save(taxi);
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
