package com.moca.heytaxi.service;

import com.moca.heytaxi.properties.TwilioProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    private final TwilioProperties twilioProperties;

    @Autowired
    public TwilioService(TwilioProperties twilioProperties) {
        this.twilioProperties = twilioProperties;
    }


}
