package com.ibm.sk.fots.spring.service;


import static org.junit.jupiter.api.Assertions.*;
import com.ibm.sk.fots.spring.config.TestRepositoryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = {TestRepositoryConfig.class})
public class ControllerServiceContextConfigTest {

  @Autowired
  private ControllerService controllerService;

  @Test
  void testFindTaskSuccess() {
    //  Setting up mock data
    Long testId = 1234L;
    //  Calling the service method
    Long resultId = controllerService.findTask(testId).getTaskId();

    //  Asserting the result
    assertEquals(testId, resultId);
  }

}
