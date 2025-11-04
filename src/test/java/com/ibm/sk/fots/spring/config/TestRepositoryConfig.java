package com.ibm.sk.fots.spring.config;

import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.repository.TagRepository;
import com.ibm.sk.fots.spring.repository.TaskRepository;
import com.ibm.sk.fots.spring.service.ControllerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

@Configuration
public class TestRepositoryConfig {

    @Bean
    public TaskRepository getTaskRepository() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskId(1234L);
        // TODO populate other fields of taskEntity so that mapper doesn't throw NPE

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
