package com.ibm.sk.fots.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.sk.fots.spring.entity.LogEntriesEntity;

public interface LogEntriesRepository extends JpaRepository<LogEntriesEntity, Long> {
    
}