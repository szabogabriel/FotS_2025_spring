package com.ibm.sk.fots.spring.repository;

import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private TestEntityManager tem;


  @Sql({ "/db/test_data_task.sql" })
  @Test
  void findTask(){
    Optional<TaskEntity> optionalTaskEntity= taskRepository.findByTaskId(10L);

    Assertions.assertNotNull(optionalTaskEntity);
    Assertions.assertTrue(optionalTaskEntity.isPresent());

    TaskEntity storedEntity = optionalTaskEntity.get();
    Assertions.assertEquals("title10", storedEntity.getTitle());
    Assertions.assertEquals("desc10", storedEntity.getDescription());
    Assertions.assertEquals(10, storedEntity.getId());
    Assertions.assertEquals(10, storedEntity.getTaskId());

    optionalTaskEntity= taskRepository.findByTaskId(2L);
    Assertions.assertNotNull(optionalTaskEntity);
    Assertions.assertFalse(optionalTaskEntity.isPresent());
  }

  @Test
  void createTask(){
    TaskEntity taskEntity = new TaskEntity();
    taskEntity.setTitle("My Test");
    taskEntity.setPriority(PriorityEnum.LOW.toInteger());

    taskRepository.save(taskEntity);

    List<TaskEntity>  storedList = taskRepository.findAll();
    Assertions.assertNotNull(storedList);
    Assertions.assertEquals(1,storedList.size());

    TypedQuery<TaskEntity> query = tem.getEntityManager().createQuery("SELECT t FROM TaskEntity t", TaskEntity.class);
    List<TaskEntity>  storedQueryList = query.getResultList();
    Assertions.assertNotNull(storedQueryList);
    Assertions.assertEquals(1,storedQueryList.size());
  }

}
