package com.moca.heytaxi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController("/")
public class IndexController {
    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok(LocalTime.now().toString());
    }
}
