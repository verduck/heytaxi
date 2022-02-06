package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.*;
import com.moca.heytaxi.dto.*;
import com.moca.heytaxi.service.CallService;
import com.moca.heytaxi.service.ReservationService;
import com.moca.heytaxi.service.TaxiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class EmptyController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final TaxiService taxiService;
    private final CallService callService;
    private final ReservationService reservationService;

    @Autowired
    public EmptyController(SimpMessagingTemplate template, ModelMapper modelMapper, TaxiService taxiService, CallService callService, ReservationService reservationService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.taxiService = taxiService;
        this.callService = callService;
        this.reservationService = reservationService;
    }

    @MessageMapping("/empty/update")
    public void updateLocation(@AuthenticationPrincipal User user, EmptyDTO.Request request) {
        try {
            Taxi taxi = taxiService.loadByUserId(user.getId());
            Empty empty = modelMapper.map(request, Empty.class);
            Call call = callService.tryReservation(empty);
            if (call != null) {
                empty.setTaxi(taxi);
                ReservationDTO reservation = modelMapper.map(reservationService.create(empty, call), ReservationDTO.class);
                template.convertAndSendToUser(reservation.getUser().getUsername(), "/topic/reservation", reservation);
            }
            template.convertAndSend("/topic/empty", modelMapper.map(empty, EmptyDTO.class));
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            template.convertAndSendToUser(user.getUsername(), "/topic/error", error);
        }
    }
}
