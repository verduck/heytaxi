package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.GetIn;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.GetInDTO;
import com.moca.heytaxi.service.GetInService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

@Controller
public class GetInController {
    private final SimpMessagingTemplate template;
    private final ModelMapper modelMapper;
    private final GetInService getInService;

    @Autowired
    public GetInController(SimpMessagingTemplate template, ModelMapper modelMapper, GetInService getInService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.getInService = getInService;
    }

    @MessageMapping("/get-in")
    public void getIn(@Payload GetInDTO request, StompHeaderAccessor headerAccessor) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();

        GetInDTO.Response response = new GetInDTO.Response();
        GetIn getIn = modelMapper.map(request, GetIn.class);
        getIn = getInService.createGetIn(getIn);
        response.setSuccess(true);
        response.setMessage("승차하였습니다.");
        response.setGetIn(modelMapper.map(getIn, GetInDTO.class));

        template.convertAndSendToUser(getIn.getUser().getUsername(), "/topic/get-in", response);
        template.convertAndSendToUser(user.getUsername(), "/topic/get-in", response);
    }
}
