package com.ibm.sk.fots.spring.controller;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.service.ControllerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/tags", produces = "application/json")
public class TagController {

  @Autowired
  private ControllerService service;

  @GetMapping("/{id}")
  public Tag get(@PathVariable Long id) {
    return service.findTagById(id);
  }

  @PostMapping
  public Tag add(@Valid @RequestBody @NotNull @Size(min = 5) String tag) {
    return service.createTag(tag);
  }

  @DeleteMapping("/{id}")
  public Tag delete(@PathVariable Long id) {
    return service.deleteTag(id);
  }
}
