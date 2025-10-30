package com.ibm.sk.fots.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerMocMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void addTaskTest() throws Exception {
    TaskCreate dto = new TaskCreate("My title", "desc", false, null, null, null);

    ObjectMapper objectMapper = new ObjectMapper();

    this.mockMvc.perform(
            post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
        .andDo(print()).andExpect(status().isOk()).andExpect(content().string(
            "{\"taskId\":1,\"title\":\"My title\",\"description\":\"desc\",\"completed\":false,\"dueDate\":null,\"priority\":\"MEDIUM\",\"tags\":null}"));
  }

}
