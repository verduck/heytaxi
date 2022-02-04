package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.domain.Empty;
import com.moca.heytaxi.dto.ErrorDTO;
import com.moca.heytaxi.dto.EmptyDTO;
import com.moca.heytaxi.service.TaxiService;
import com.moca.heytaxi.service.WaitingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller("/empty")
public class EmptyController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final TaxiService taxiService;
    private final WaitingService waitingService;

    @Autowired
    public EmptyController(SimpMessagingTemplate template, ModelMapper modelMapper, TaxiService taxiService, WaitingService waitingService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.taxiService = taxiService;
        this.waitingService = waitingService;
    }

    @MessageMapping
    public void empty(@AuthenticationPrincipal User user, EmptyDTO.Request request) {
        Taxi taxi = taxiService.loadByUserId(user.getId());
        if (taxi == null) {
            ErrorDTO error = new ErrorDTO("등록된 택시가 없습니다.");
            template.convertAndSendToUser(user.getUsername(), "/topic/error", error);
            return;
        }
        Empty empty = modelMapper.map(request, Empty.class);
        empty.setId(taxi.getUser().getId());
        empty.setTimestamp(LocalDateTime.now());
        empty = waitingService.wait(empty);
    }

    @MessageMapping("/update")
    public void updateLocation(@AuthenticationPrincipal User user, EmptyDTO.Request request) {
        try {
            waitingService.updateLocationById(user.getId(), request.getLocation());
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            template.convertAndSendToUser(user.getUsername(), "/topic/error", error);
        }
    }
}
