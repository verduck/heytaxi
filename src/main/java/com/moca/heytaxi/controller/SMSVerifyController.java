package com.moca.heytaxi.controller;

import com.moca.heytaxi.dto.SMSVerifyDTO;
import com.moca.heytaxi.service.SMSVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class SMSVerifyController {
    private SMSVerifyService smsVerifyService;

    @Autowired
    public SMSVerifyController(SMSVerifyService smsVerifyService) {
        this.smsVerifyService = smsVerifyService;
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ResponseEntity<String> request(@RequestBody SMSVerifyDTO.Request request) {
        String response;
        if (request == null || request.getPhone() == null) {
            return ResponseEntity.badRequest().body("전화번호를 입력하세요.");
        }
        response = smsVerifyService.request(request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> verify(@RequestBody SMSVerifyDTO.Request request) {
        String response;
        if (request == null) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        } else if (request.getPhone() == null) {
            return ResponseEntity.badRequest().body("전화번호를 입력하세요.");
        } else if (request.getClientSecret() == null) {
            return ResponseEntity.badRequest().body("클라이언트 비밀키를 입력하세요.");
        } else if (request.getMessage() == null) {
            return ResponseEntity.badRequest().body("인증번호를 입력하세요.");
        }
        response = smsVerifyService.verify(request);
        return ResponseEntity.ok(response);
    }
}
