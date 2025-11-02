package com.ibm.sk.fots.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.dto.TaskUpdate;
import com.ibm.sk.fots.spring.service.ControllerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/tasks", produces = "application/json")
public class TaskController {

  @Autowired
  private ControllerService service;

  @GetMapping("/{id}")
  public Task find(@PathVariable Long id) {
    try {
      return service.findTask(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public Task add(@Valid @RequestBody TaskCreate todo) {
    return service.add(todo);
  }

  @PatchMapping("/{id}")
  public Task update(@PathVariable Long id, @Valid @RequestBody TaskUpdate todo) {
    Task ret = null;
    try {
      ret = service.update(id, todo);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ret;
  }

  @DeleteMapping("/{id}")
  public Task delete(@PathVariable Long id) {
    Task ret = null;
    try {
      service.delete(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ret;
  }

  @GetMapping
  public List<Task> findAll() {
    return service.findAllTasks();
  }
}