package com.ibm.sk.fots.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.sk.fots.spring.entity.Task;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    

    @GetMapping("/{id}")
    public String getTasks(@PathVariable int id){
        return "Returning task with id "+id;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(task);
    }


}
