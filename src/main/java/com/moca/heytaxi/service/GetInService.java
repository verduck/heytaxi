package com.moca.heytaxi.service;

import com.moca.heytaxi.domain.GetIn;
import com.moca.heytaxi.repository.GetInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetInService {
    private final GetInRepository getInRepository;

    @Autowired
    public GetInService(GetInRepository getInRepository) {
        this.getInRepository = getInRepository;
    }

    public GetIn createGetIn(GetIn getIn) {
        return getInRepository.save(getIn);
    }
}
