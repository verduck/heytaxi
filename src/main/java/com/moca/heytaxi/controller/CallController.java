package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.domain.Waiting;
import com.moca.heytaxi.dto.CallDTO;
import com.moca.heytaxi.dto.MatchingDTO;
import com.moca.heytaxi.dto.WaitingDTO;
import com.moca.heytaxi.service.CallService;
import com.moca.heytaxi.service.TaxiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
public class CallController {
    private SimpMessagingTemplate template;
    private ModelMapper modelMapper;
    private CallService callService;
    private TaxiService taxiService;

    @Autowired
    public CallController(SimpMessagingTemplate template, ModelMapper modelMapper, CallService callService, TaxiService taxiService) {
        this.template = template;
        this.modelMapper = modelMapper;
        this.callService = callService;
        this.taxiService = taxiService;
    }

    @MessageMapping("/call")
    public void call(@AuthenticationPrincipal User user, CallDTO request) {
        MatchingDTO.Response response = new MatchingDTO.Response();
        Waiting waiting = callService.callTaxi(request);
        if (waiting != null) {
            response.setSuccess(true);
            response.setMessage("택시를 호출했습니다.");
            response.setMatching(new MatchingDTO(request, modelMapper.map(waiting, WaitingDTO.class)));
            template.convertAndSendToUser(user.getUsername(), "/topic/match-taxi", response);
            response.setMessage("승객이 찾습니다.");
            template.convertAndSendToUser(waiting.getTaxi().getUser().getUsername(), "/topic/match-user", response);
        } else {
            response.setSuccess(false);
            response.setMessage("택시를 호출하지 못했습니다. 잠시 후 다시 시도해 주세요.");
        }
    }

    @MessageMapping("/wait")
    public void waitCall(@AuthenticationPrincipal User user, WaitingDTO.Request request) {
        Taxi taxi = taxiService.loadByUserId(user.getId());
        if (taxi == null) {
            return;
        }
        Waiting waiting = modelMapper.map(request, Waiting.class);
        waiting.setId(taxi.getUser().getUsername());
        waiting = callService.waitCall(waiting);
        template.convertAndSendToUser(user.getUsername(), "/topic/wait", modelMapper.map(waiting, WaitingDTO.class));
    }

    @MessageMapping("/update")
    public void updateTaxi(@AuthenticationPrincipal User user) {

    }
}
