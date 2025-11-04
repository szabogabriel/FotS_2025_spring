package com.ibm.sk.fots.spring.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.repository.TagRepository;
import com.ibm.sk.fots.spring.repository.TaskRepository;
import com.ibm.sk.fots.spring.service.ControllerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// String testTitle = "Test Title";
// String testDescription = "Test Description";
// String testDueDate = "2024-12-31T23:59:59";
// boolean testCompleted = false;
// PriorityEnum testPriority = PriorityEnum.MEDIUM;

@Configuration
public class TestRepositoryConfig {

  @Bean
  public TaskRepository getTaskRepository() {
    //  Setting up mock data
    Long testId = 1234L;
    String testTitle = "Test Title";
    String testDescription = "Test Description";
    String testDueDate = "2024-12-31T23:59:59";
    boolean testCompleted = false;
    PriorityEnum testPriority = PriorityEnum.MEDIUM;

    //  Mocking the repository response, since the TaskEntity does not have a AllArgsConstructor, we set the fields manually
    TaskEntity taskEntity = new TaskEntity();
    taskEntity.setTaskId(testId);
    taskEntity.setTitle(testTitle);
    taskEntity.setDescription(testDescription);
    taskEntity.setDueDate(LocalDateTime.parse(testDueDate));
    taskEntity.setCompleted(testCompleted);
    taskEntity.setPriority(testPriority.toInteger());
    taskEntity.setTags(List.of());
    taskEntity.setOwner(1L);

    Optional<TaskEntity> optionalTaskEntity = Optional.of(taskEntity);
    TaskRepository mockedRepository = Mockito.mock(TaskRepository.class);
    Mockito.when(mockedRepository.findByTaskId(1234L)).thenReturn(optionalTaskEntity);

    return mockedRepository;
  }

  @Bean
  public TagRepository tagRepository() {
    return Mockito.mock(TagRepository.class);
  }

  @Bean
  public ControllerService controllerService() {
    return new ControllerService();
  }
}
