package com.ibm.sk.fots.spring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class PropertyTest {

  @Value("${spring.application.name}")
  private String appName;

  @Test
  void testAppNameProperty() {
    assertEquals("spring-test", appName);
  }
}
