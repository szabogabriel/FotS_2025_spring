package com.ibm.sk.fots.spring.service;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.dto.TaskUpdate;
import com.ibm.sk.fots.spring.mapper.TagMapper;
import com.ibm.sk.fots.spring.mapper.TaskMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ControllerService {

  private List<Task> storedTasks = new ArrayList<>();
  private List<Tag> storedTags = new ArrayList<>();

  public Task add(TaskCreate createDto) {
    Task createdTask = TaskMapper.toNew(createDto);

    OptionalLong lastId = storedTasks.stream().mapToLong(Task::getTaskId).max();
    createdTask.setTaskId(lastId.orElse(0) + 1);

    storedTasks.add(createdTask);

    return createdTask;
  }

  public Task find(Long id) {
    Optional<Task> foundTask = storedTasks.stream().filter(t -> t.getTaskId().equals(id)).findFirst();

    return foundTask.orElse(null);
  }

  public Task update(Long id, TaskUpdate updateDto) {
    Optional<Task> foundTask = storedTasks.stream().filter(t -> t.getTaskId().equals(id)).findFirst();

    if (!foundTask.isPresent()) {
      throw new IllegalArgumentException();
    }

    Task ret = TaskMapper.update(foundTask.get(),updateDto);
    return ret;
  }

  public Task delete(Long id) {
    Task ret = null;
    Optional<Task> foundTask = storedTasks.stream().filter(t -> t.getTaskId().equals(id)).findFirst();

    if (foundTask.isPresent()) {
      storedTasks.remove(foundTask.get());
      ret = foundTask.get();
    } else {
      throw new IllegalArgumentException();
    }
    return ret;
  }


  public Tag findTagById(Long id) {
    Optional<Tag> storedTag = storedTags.stream().filter(t -> t.getTagId().equals(id)).findFirst();
    return storedTag.orElse(null);
  }

  public Tag createTag(String tagName) {
    Optional<Tag> existing = storedTags.stream().filter(t -> t.getName().equals(tagName)).findFirst();
    if (existing.isPresent()) {
      throw new IllegalArgumentException("Tag already exists");
    }
    Tag createdTag = TagMapper.toNew(tagName);
    OptionalLong lastId = storedTags.stream().mapToLong(Tag::getTagId).max();
    createdTag.setTagId(lastId.orElse(0) + 1);

    storedTags.add(createdTag);

    return createdTag;
  }

  public Tag deleteTag(Long id) {
    Tag ret = null;
    Optional<Tag> storedTag = storedTags.stream().filter(t -> t.getTagId().equals(id)).findFirst();
    if (storedTag.isPresent()) {
      storedTags.remove(storedTag);
      ret = storedTag.get();
    } else {
      throw new IllegalArgumentException();
    }
    return ret;
  }
}
