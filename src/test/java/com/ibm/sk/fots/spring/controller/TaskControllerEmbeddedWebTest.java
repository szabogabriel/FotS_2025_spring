package com.ibm.sk.fots.spring.controller;

import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerEmbeddedWebTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testStartup() {
        System.out.println("Spring boot test random port is: " + port);
    }

    @Test
    public void testCreateTask() {
        TaskCreate taskCreate = new TaskCreate("My title", "desc", false, null, null, null);

        ResponseEntity<Task> taskResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/tasks", taskCreate, Task.class);

        Assertions.assertNotNull(taskResponseEntity);
        Assertions.assertEquals(HttpStatus.OK, taskResponseEntity.getStatusCode());

        Task taskAfterSave = taskResponseEntity.getBody();
        Assertions.assertNotNull(taskAfterSave);
        Assertions.assertEquals(taskCreate.getTitle(), taskAfterSave.getTitle());
    }
}
