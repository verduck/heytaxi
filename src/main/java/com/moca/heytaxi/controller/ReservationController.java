package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Reservation;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.ReservationDTO;
import com.moca.heytaxi.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller("/reservation")
public class ReservationController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(SimpMessagingTemplate template, ModelMapper modelMapper, ReservationService reservationService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    public void allow(@AuthenticationPrincipal User user, ReservationDTO request) {
        template.convertAndSendToUser(request.getUser().getUsername(), "/topic/reservation", request);
    }

    public void reject(@AuthenticationPrincipal User user, ReservationDTO request) {
        reservationService.reject(modelMapper.map(request, Reservation.class));
    }
}
