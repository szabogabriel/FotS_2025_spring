package com.ibm.sk.fots.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.sk.fots.spring.entity.LogEntryEntity;

public interface LogEntryRepository extends JpaRepository<LogEntryEntity, String> {
    
}
