package com.ibm.sk.fots.spring.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@SpringBootTest
public class ControllerServiceMockTest {

  @Autowired
  private ControllerService controllerService;

  @MockitoBean
  private TaskRepository taskRepo;

  @Test
  void testFindTaskSuccess() {
    //  Setting up mock data
    Long testId = 1234L;
    String testTitle = "Test Title";
    String testDescription = "Test Description";
    String testDueDate = "2024-12-31T23:59:59";
    boolean testCompleted = false;
    PriorityEnum testPriority = PriorityEnum.MEDIUM;

    //  Mocking the repository response, since the TaskEntity does not have a AllArgsConstructor, we set the fields manually
    TaskEntity mockTaskEntity = new TaskEntity();
    mockTaskEntity.setTaskId(testId);
    mockTaskEntity.setTitle(testTitle);
    mockTaskEntity.setDescription(testDescription);
    mockTaskEntity.setDueDate(LocalDateTime.parse(testDueDate));
    mockTaskEntity.setCompleted(testCompleted);
    mockTaskEntity.setPriority(testPriority.toInteger());
    mockTaskEntity.setTags(List.of());
    mockTaskEntity.setOwner(1L);

    //  Mocking the repository method call
    when(taskRepo.findByTaskId(testId)).thenReturn(Optional.of(mockTaskEntity));

    //  Calling the service method
    Long resultId = controllerService.findTask(testId).getTaskId();

    //  Asserting the result
    assertEquals(testId, resultId);
  }

  @Test
  void testFindTaskFail() {
    Long testId = 5678L;

    //  Mocking the repository method call to return empty
    when(taskRepo.findByTaskId(anyLong())).thenReturn(Optional.empty());

    //  Asserting that the exception is thrown
    assertThrows(EntityNotFoundException.class, () -> {
      controllerService.findTask(testId);
    });
  }
}
