package com.ibm.sk.fots.spring.controller;

import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.dto.TaskUpdate;
import com.ibm.sk.fots.spring.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/tasks", produces = "application/json")
public class TaskController {

  @Autowired
  private ControllerService service;

  @GetMapping("/{id}")
  public Task find(@PathVariable Long id) {
    Task ret = null;
    try {
      ret = service.find(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ret;
  }

  @PostMapping
  public ResponseEntity<Task> add(@RequestBody TaskCreate createTask) {

    Task createdTaks = service.add(createTask);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTaks);
  }

  @PatchMapping("/{id}")
  public Task update(@PathVariable Long id, @RequestBody TaskUpdate todo) {
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

}