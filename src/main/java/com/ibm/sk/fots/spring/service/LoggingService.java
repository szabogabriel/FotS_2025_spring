package com.ibm.sk.fots.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.sk.fots.spring.entity.LogEntriesEntity;
import com.ibm.sk.fots.spring.repository.LogEntriesRepository;

@Service
public class LoggingService {
    private LogEntriesRepository logEntRep;

    @Autowired
    public LoggingService(LogEntriesRepository logEntRep) {
        this.logEntRep = logEntRep;
    }

    public void Logging(String message, String timestamp) {
        LogEntriesEntity logEntriesEntity = new LogEntriesEntity();

        logEntriesEntity.setMessage(message);
        logEntriesEntity.setTimestamp(timestamp);

        logEntRep.save(logEntriesEntity);
        System.out.print(logEntriesEntity.getId());
    }
}
