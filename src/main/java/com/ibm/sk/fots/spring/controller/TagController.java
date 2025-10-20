package com.ibm.sk.fots.spring.controller;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.service.ControllerService;
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
    Tag ret = service.findTagById(id);
    if (ret == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ret;
  }

  @PostMapping
  public ResponseEntity<Tag> add(@RequestBody @NotNull String tag) {
    Tag createdTag;
    try {
      createdTag = service.createTag(tag);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
  }

  @DeleteMapping("/{id}")
  public Tag delete(@PathVariable Long id) {
    try {
      return service.deleteTag(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}
