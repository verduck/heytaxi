package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Reservation;
import com.moca.heytaxi.dto.ReservationDTO;
import com.moca.heytaxi.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
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

    @MessageMapping("/reservation/allow")
    public void allow(@Payload ReservationDTO request) {
        template.convertAndSendToUser(request.getUser().getUsername(), "/topic/reservation", request);
        template.convertAndSend("/topic/empty/remove", request.getTaxi());
    }

    @MessageMapping("/reservation/reject")
    public void reject(@Payload ReservationDTO request) {
        reservationService.reject(modelMapper.map(request, Reservation.class));
    }

    @MessageMapping("/reservation/update")
    public void update(@Payload ReservationDTO request) {
        template.convertAndSendToUser(request.getUser().getUsername(), "/topic/reserved-taxi", request.getTaxi());
    }
}
