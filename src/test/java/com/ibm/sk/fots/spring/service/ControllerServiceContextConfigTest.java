package com.ibm.sk.fots.spring.service;


import com.ibm.sk.fots.spring.config.TestRepositoryConfig;
import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


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
