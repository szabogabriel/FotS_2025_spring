package com.ibm.sk.fots.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.service.ControllerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerWebMvcMockMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ControllerService service;

  @Test
  public void addTaskTest() throws Exception {
    TaskCreate dto = new TaskCreate();

    Mockito.when(service.add(dto)).thenReturn(new Task(1L, "My title", "desc", true, null, PriorityEnum.MEDIUM, null));

    ObjectMapper objectMapper = new ObjectMapper();

    this.mockMvc.perform(
            post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
        .andDo(print()).andExpect(status().isOk()).andExpect(content().string(
            "{\"taskId\":1,\"title\":\"My title\",\"description\":\"desc\",\"completed\":true,\"dueDate\":null,\"priority\":\"MEDIUM\",\"tags\":null}"));
  }

}
