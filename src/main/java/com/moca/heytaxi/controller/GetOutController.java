package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.GetOut;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.GetOutDTO;
import com.moca.heytaxi.service.GetOutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
public class GetOutController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final GetOutService getOutService;

    @Autowired
    public GetOutController(SimpMessagingTemplate template, ModelMapper modelMapper, GetOutService getOutService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.getOutService = getOutService;
    }

    @MessageMapping("/get-out")
    public void getOut(@AuthenticationPrincipal User user, GetOutDTO request) {
        GetOutDTO.Response response = new GetOutDTO.Response();
        GetOut getOut = modelMapper.map(request, GetOut.class);
        getOut = getOutService.createGetOut(getOut);
        response.setSuccess(true);
        response.setMessage("하차하였습니다.");
        response.setGetOut(modelMapper.map(getOut, GetOutDTO.class));

        template.convertAndSendToUser(getOut.getUser().getUsername(), "/topic/get-out", response);
        template.convertAndSendToUser(user.getUsername(), "/topic/get-out", response);
    }
}
