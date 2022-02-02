package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.dto.WaitingDTO;
import com.moca.heytaxi.service.TaxiService;
import com.moca.heytaxi.service.WaitingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WaitingController {
    private final ModelMapper modelMapper;
    private final TaxiService taxiService;
    private final WaitingService waitingService;

    @Autowired
    public WaitingController(ModelMapper modelMapper, TaxiService taxiService, WaitingService waitingService) {
        this.modelMapper = modelMapper;
        this.taxiService = taxiService;
        this.waitingService = waitingService;
    }

    @MessageMapping("/wait")
    public void wait(@AuthenticationPrincipal User user, WaitingDTO.Request request) {
        Taxi taxi = taxiService.loadByUserId(user.getId());
        if (taxi == null) {
            return;
        }
        Waiting waiting = modelMapper.map(request, Waiting.class);
        waiting.setId(taxi.getUser().getId());
        waiting.setTimestamp(LocalDateTime.now());
        waiting = waitingService.wait(waiting);
    }

    @MessageMapping("/update-location")
    public void updateLocation(@AuthenticationPrincipal User user, WaitingDTO.Request request) throws Exception {
        waitingService.updateLocationById(user.getId(), request.getLocation());
    }
}
