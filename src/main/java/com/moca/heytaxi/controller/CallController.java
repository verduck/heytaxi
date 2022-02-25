package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Call;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.CallDTO;
import com.moca.heytaxi.dto.ErrorDTO;
import com.moca.heytaxi.service.CallService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class CallController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final CallService callService;

    @Autowired
    public CallController(SimpMessagingTemplate template, ModelMapper modelMapper, CallService callService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.callService = callService;
    }

    @MessageMapping("/call/request")
    public void request(@Payload CallDTO request, StompHeaderAccessor headerAccessor) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();

        Call call = modelMapper.map(request, Call.class);
        call.setId(user.getId());
        call.setTimestamp(LocalDateTime.now());
        callService.enqueueCall(call);
    }

    @MessageMapping("/call/cancel")
    public void cancel(StompHeaderAccessor headerAccessor) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();

        try {
            Call call = callService.loadCallById(user.getId());
            callService.dequeueCall(call);
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            template.convertAndSendToUser(user.getUsername(), "/topic/error", error);
        }
    }
}
