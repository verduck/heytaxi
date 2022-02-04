package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Call;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.CallDTO;
import com.moca.heytaxi.dto.ErrorDTO;
import com.moca.heytaxi.service.CallService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller("/call")
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

    @MessageMapping("/request")
    public void request(@AuthenticationPrincipal User user, CallDTO request) {
        Call call = modelMapper.map(request, Call.class);
        call.setId(user.getId());
        call.setTimestamp(LocalDateTime.now());
        callService.enqueueCall(call);
    }

    @MessageMapping("/cancel")
    public void cancel(@AuthenticationPrincipal User user) {
        try {
            Call call = callService.loadCallById(user.getId());
            callService.dequeueCall(call);
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            template.convertAndSendToUser(user.getUsername(), "/topic/error", error);
        }
    }
}
