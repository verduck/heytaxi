package com.moca.heytaxi.controller;

import com.moca.heytaxi.dto.VerifyDTO;
import com.moca.heytaxi.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    private VerifyService smsVerifyService;

    @Autowired
    public VerifyController(VerifyService smsVerifyService) {
        this.smsVerifyService = smsVerifyService;
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ResponseEntity<VerifyDTO.Response> request(@RequestBody VerifyDTO.Request request) {
        VerifyDTO.Response response = new VerifyDTO.Response();
        if (request == null || request.getPhone() == null) {
            response.setMessage("전화번호를 입력하세요.");
            return ResponseEntity.badRequest().body(response);
        }
        response = smsVerifyService.request(request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<VerifyDTO.Response> verify(@RequestBody VerifyDTO.Request request) {
        VerifyDTO.Response response = new VerifyDTO.Response();
        if (request == null) {
            response.setMessage("잘못된 요청입니다.");
            return ResponseEntity.badRequest().body(response);
        } else if (request.getPhone() == null) {
            response.setMessage("전화번호를 입력하세요.");
            return ResponseEntity.badRequest().body(response);
        } else if (request.getClientSecret() == null) {
            response.setMessage("클라이언트 비밀키를 입력하세요.");
            return ResponseEntity.badRequest().body(response);
        } else if (request.getCode() == null) {
            response.setMessage("인증번호를 입력하세요.");
            return ResponseEntity.badRequest().body(response);
        }
        response = smsVerifyService.verify(request);
        return ResponseEntity.ok(response);
    }
}
