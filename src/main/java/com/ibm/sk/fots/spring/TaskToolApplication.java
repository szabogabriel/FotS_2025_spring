package com.ibm.sk.fots.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TaskToolApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskToolApplication.class, args);
  }

}
