package com.ibm.sk.fots.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.sk.fots.spring.entity.LogEntryEntity;
import com.ibm.sk.fots.spring.repository.LogEntryRepository;

//@RestController
public class HelloController {

    @Autowired
    private LogEntryRepository logEntryRepository;

    @GetMapping("/hello")
    public String sayHello(@RequestHeader Map<String, String> headers) {
        logMessage("Hello endpoint was called with headers: " + headers.toString());
        logMessage("Returning greeting message");
        return "Hello, World!";
    }

    private void logMessage(String message) {
        LogEntryEntity logEntry = new LogEntryEntity();
        logEntry.setMessage(message);
        logEntry.setTimestamp(java.time.LocalDateTime.now());
        logEntry = logEntryRepository.save(logEntry);
        System.out.println("Logged message with ID " + logEntry.getId() + ": " + message);
    }

}
