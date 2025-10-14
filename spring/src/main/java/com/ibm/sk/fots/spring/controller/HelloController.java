package com.ibm.sk.fots.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.sk.fots.spring.service.LoggingService;

@RestController
public class HelloController {
    
    LoggingService loggingService;

    @Autowired
    HelloController(LoggingService loggingService){
        this.loggingService = loggingService;
    }
    @GetMapping("/hello")
    public String hello(){
        loggingService.logMessage("Created entity");
        return "Hello";

    }
}
