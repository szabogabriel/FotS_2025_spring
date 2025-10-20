package com.ibm.sk.fots.spring.controller;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.service.ControllerService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public Tag add(@RequestBody @NotNull  String tag) {
    return service.createTag(tag);
  }

  @DeleteMapping("/{id}")
  public Tag delete(@PathVariable Long id) {
    return service.deleteTag(id);
  }
}
