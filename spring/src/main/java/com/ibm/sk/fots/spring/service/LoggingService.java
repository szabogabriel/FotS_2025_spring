package com.ibm.sk.fots.spring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.sk.fots.spring.entity.LogEntriesEntity;
import com.ibm.sk.fots.spring.repository.LogEntriesRepository;

@Service
public class LoggingService {
    private LogEntriesRepository logEntriesRepository;

    @Autowired
    LoggingService(LogEntriesRepository logEntriesRepository){
        this.logEntriesRepository=logEntriesRepository;
    }

    public void logMessage(String message){
        LogEntriesEntity entity = new LogEntriesEntity();
        entity.setMessage(message);
        entity.setTimestamp(LocalDateTime.now().toString());
        logEntriesRepository.save(entity);
        System.out.println("Created entity with id:" + entity.getId());
    }
    
}
