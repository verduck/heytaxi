package com.moca.heytaxi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController("/")
public class IndexController {
    @GetMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<LocalDateTime> get() {
        return ResponseEntity.ok(LocalDateTime.now());
    }
}
