package com.ibm.sk.fots.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ibm.sk.fots.spring.service.LoggingService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    private LoggingService loggingService;

    @Autowired
    public HelloController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/hello")
    public String hello() {
        loggingService.Logging("some message", LocalDateTime.now().toString());
        return "Hello World";   
    }
}
