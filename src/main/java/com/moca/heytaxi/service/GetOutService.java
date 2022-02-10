package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.GetOut;
import com.moca.heytaxi.repository.GetOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOutService {
    private final GetOutRepository getOutRepository;

    @Autowired
    public GetOutService(GetOutRepository getOutRepository) {
        this.getOutRepository = getOutRepository;
    }

    public GetOut createGetOut(GetOut getOut) {
        return getOutRepository.save(getOut);
    }
}
