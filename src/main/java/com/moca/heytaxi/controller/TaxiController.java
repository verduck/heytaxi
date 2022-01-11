package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.TaxiDTO;
import com.moca.heytaxi.service.TaxiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxi")
public class TaxiController {
    private ModelMapper modelMapper;
    private TaxiService taxiService;

    @Autowired
    public TaxiController(ModelMapper modelMapper, TaxiService taxiService) {
        this.modelMapper = modelMapper;
        this.taxiService = taxiService;
    }

    @GetMapping
    public ResponseEntity<TaxiDTO.Response> loadMyTaxi(@AuthenticationPrincipal User user) {
        TaxiDTO.Response response = new TaxiDTO.Response();
        Taxi taxi = taxiService.loadByUserId(user.getId());
        if (taxi == null) {
            response.setMessage("등록된 택시 정보가 존재하지 않습니다.");
        } else {
            response.setSuccess(true);
            response.setMessage("택시 정보를 성공적으로 불러왔습니다.");
            response.setTaxi(modelMapper.map(taxi, TaxiDTO.class));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaxiDTO.Response> registerMyTaxi(@AuthenticationPrincipal User user, @RequestBody TaxiDTO.Request request) {
        TaxiDTO.Response response = new TaxiDTO.Response();
        Taxi taxi = new Taxi(user, request.getName(), request.getCarNumber());
        taxi = taxiService.createTaxi(taxi);
        response.setSuccess(true);
        response.setMessage("택시 정보를 성공적으로 등록하였습니다.");
        response.setTaxi(modelMapper.map(taxi, TaxiDTO.class));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<TaxiDTO.Response> deleteMyTaxi(@AuthenticationPrincipal User user) {
        TaxiDTO.Response response = new TaxiDTO.Response();
        taxiService.deleteTaxiByUserId(user.getId());
        response.setSuccess(true);
        response.setMessage("택시 정보를 성공적으로 제거하였습니다.");
        return ResponseEntity.ok(response);
    }
}
