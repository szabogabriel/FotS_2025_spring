package com.ibm.sk.fots.spring.service;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.dto.TaskUpdate;
import com.ibm.sk.fots.spring.entity.TagEntity;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.mapper.TagMapper;
import com.ibm.sk.fots.spring.mapper.TaskMapper;
import com.ibm.sk.fots.spring.repository.TagRepository;
import com.ibm.sk.fots.spring.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ControllerService {

  @Autowired
  private TagRepository tagRepo;

  @Autowired
  private TaskRepository taskRepo;

  public Task findTask(Long id) {
    Optional<TaskEntity> task = taskRepo.findByTaskId(id);
    if (task.isPresent()) {
      return TaskMapper.toDto(task.get());
    } else {
      throw new EntityNotFoundException();
    }
  }

  public List<Task> findFiltered(Boolean done, LocalDate dueBefore) {
    if (done != null && dueBefore != null) {
      return TaskMapper.toDtoList(taskRepo.findByCompletedAndDueDateBefore(done, dueBefore));
    } else if (done != null) {
      return TaskMapper.toDtoList(taskRepo.findByCompleted(done));
    } else if (dueBefore != null) {
      return TaskMapper.toDtoList(taskRepo.findByDueDateBefore(dueBefore));
    }

    return new ArrayList<>();
  }

  public Task add(TaskCreate todo) {
    TaskEntity entity = TaskMapper.toNewEntity(todo);
    if (todo.getTags() != null) {
      List<String> tags = todo.getTags();

      List<TagEntity> tagEntities = new ArrayList<>();
      for (String tag : tags) {
        Optional<TagEntity> existing = tagRepo.findByName(tag);
        if (existing.isEmpty()) {
          TagEntity tmpTag = TagMapper.toNewEntity(tag);
          tmpTag = tagRepo.save(tmpTag);
          tmpTag.setTagId(tmpTag.getId());
          tagRepo.save(tmpTag);
          tagEntities.add(tmpTag);
        } else {
          tagEntities.add(existing.get());
        }
      }
      entity.setTags(tagEntities);
    }

    entity = taskRepo.save(entity);
    entity.setTaskId(entity.getId());
    entity = taskRepo.save(entity);

    Task ret = TaskMapper.toDto(entity);
    return ret;
  }

  public Task update(Long id, TaskUpdate todo) {
    Optional<TaskEntity> taskToUpdate = taskRepo.findByTaskId(id);

    if (taskToUpdate.isEmpty()) {
      throw new IllegalArgumentException();
    }

    TaskEntity updatedEntity = TaskMapper.updateEntity(taskToUpdate.get(), todo);
    updatedEntity = taskRepo.save(updatedEntity);

    Task ret = TaskMapper.toDto(updatedEntity);
    return ret;
  }

  public Task delete(Long id) {
    Task ret = null;
    Optional<TaskEntity> taskToDelete = taskRepo.findByTaskId(id);
    if (taskToDelete.isPresent()) {
      taskRepo.delete(taskToDelete.get());
      ret = TaskMapper.toDto(taskToDelete.get());
    } else {
      throw new IllegalArgumentException();
    }
    return ret;
  }

  public List<Tag> findTags(Boolean active, String name, String pattern) {
    Pattern compiledPattern = Pattern.compile(pattern == null ? ".*" : pattern, Pattern.CASE_INSENSITIVE);
    ;

    List<TagEntity> entities = tagRepo.findAll();

    List<Tag> ret = entities.stream().filter(e -> active != null ? e.isActive() == active : true)
        .filter(e -> name != null ? e.getName().equalsIgnoreCase(name) : true)
        .filter(e -> pattern != null ? compiledPattern.matcher(e.getName()).matches() : true).map(TagMapper::toDto)
        .collect(Collectors.toList());

    return ret;
  }

  public Tag findTagById(Long id) {
    Optional<TagEntity> tag = tagRepo.findByTagId(id);
    if (tag.isPresent()) {
      return TagMapper.toDto(tag.get());
    } else {
      throw new EntityNotFoundException();
    }
  }

  public Tag createTag(String tag) {
    Optional<TagEntity> existing = tagRepo.findByName(tag);
    if (existing.isPresent()) {
      throw new IllegalArgumentException("Tag already exists");
    }
    TagEntity entity = TagMapper.toNewEntity(tag);
    entity = tagRepo.save(entity);
    entity.setTagId(entity.getId());
    entity = tagRepo.save(entity);
    return TagMapper.toDto(entity);
  }

  public Tag deleteTag(Long id) {
    Tag ret = null;
    Optional<TagEntity> tagToDelete = tagRepo.findByTagId(id);
    if (tagToDelete.isPresent()) {
      TagEntity entity = tagToDelete.get();
      entity.setActive(false);
      tagRepo.save(entity);
      ret = TagMapper.toDto(entity);
    } else {
      throw new IllegalArgumentException();
    }
    return ret;
  }
}
