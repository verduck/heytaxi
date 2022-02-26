package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.*;
import com.moca.heytaxi.dto.*;
import com.moca.heytaxi.service.CallService;
import com.moca.heytaxi.service.ReservationService;
import com.moca.heytaxi.service.TaxiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void updateLocation(@Payload EmptyDTO.Request request, StompHeaderAccessor headerAccessor) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();
        try {
            Taxi taxi = taxiService.loadByUserId(user.getId());
            Empty empty = modelMapper.map(request, Empty.class);
            empty.setTaxi(taxi);
            Call call = callService.tryReservation(empty);
            if (call != null) {
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